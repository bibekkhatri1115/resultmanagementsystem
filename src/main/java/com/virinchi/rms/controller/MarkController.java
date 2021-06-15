package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.Mark;
import com.virinchi.rms.entity.MarkDetail;
import com.virinchi.rms.repository.MarkDetailRepository;
import com.virinchi.rms.repository.MarkRepository;
import com.virinchi.rms.repository.StudentRepository;
import com.virinchi.rms.repository.SubjectRepository;
import com.virinchi.rms.util.UploadUtil;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/mark")
public class MarkController extends CRUDController<Mark> {

    protected static final String prefix = "/src/main/resources/static";
    protected static final String suffix = "/uploads/item/temp";
    protected static final String TEMP_IMAGE_PATH = System.getProperty("user.dir") + prefix + suffix;
    protected static String fileName = "";
    private final UploadUtil uploadUtil;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private MarkDetailRepository markDetailRepository;

    public MarkController(UploadUtil uploadUtil) {
        this.uploadUtil = uploadUtil;
        viewPath = "mark";
        uri = "mark";
        pageTitle = "Mark";
        activeMenu = "mark";
    }

    @Override
    public String create(Model model, HttpServletRequest request) {
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return super.create(model, request);
    }

    @Override
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("subjects", subjectRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        return super.edit(id, model); // To change body of generated methods, choose Tools | Templates.
    }

    @PostMapping(value = "/upload")
    @Transactional
    public String upload(@RequestParam("files") MultipartFile file) throws Exception {
        if (!file.isEmpty()) {
            Path tempDir = Files.createTempDirectory("");
            File tempFile = tempDir.resolve(file.getOriginalFilename()).toFile();
            file.transferTo(tempFile);
            Workbook workbook = WorkbookFactory.create(tempFile);
            Sheet sheet = workbook.getSheetAt(0);
            Supplier<Stream<Row>> rowStreamSupplier = uploadUtil.getRowStreamSupplier(sheet);
            Row headerRow = rowStreamSupplier.get().findFirst().get();
            DataFormatter formatter = new DataFormatter();
            List<String> headerCells = StreamSupport.stream(headerRow.spliterator(), false)
                    .map(Cell::getStringCellValue)
                    .collect(Collectors.toList());
            int colCount = headerCells.size();

//            System.out.println(headerCells);
            rowStreamSupplier.get().forEach(row -> {
                List<String> cellList = StreamSupport.stream(row.spliterator(), false)
                        .map(Cell::getStringCellValue)
                        .collect(Collectors.toList());

                Map<String, String> cellMap = IntStream.range(0, colCount)
                        .boxed()
                        .collect(Collectors.toMap(
                                        index -> headerCells.get(index),
                                        index -> cellList.get(index)));
                System.out.println(cellMap);
            });
        }
    
    return "redirect:/" + uri + "?success";
    }

    @PostMapping(value = "/store")
    @Transactional
    public String store(Mark model) {

        markRepository.save(model);

        List<MarkDetail> oldDetails = markDetailRepository.getByStudentMarkId(model.getId());
        if (oldDetails != null) {
            for (MarkDetail oldDetail : oldDetails) {
                markDetailRepository.delete(oldDetail);
            }
        }

        // insert new details
        for (MarkDetail detail : model.getDetails()) {
            detail.setStudentMark(model);
            markDetailRepository.save(detail);
        }

        return "redirect:/" + uri + "?success";
    }

    @GetMapping(value = "/details/{id}")
    @ResponseBody
    public List<MarkDetail> getDetails(@PathVariable("id") int id) {
        return markDetailRepository.getByStudentMarkId(id);
    }

}
