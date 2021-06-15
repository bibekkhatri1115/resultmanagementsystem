package com.virinchi.rms.controller.core;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

public abstract class CRUDController<T> extends SecuredController {

    @Autowired
    protected JpaRepository<T, Integer> repository;
    protected String uri;
    protected boolean isDescending = false;

    @ModelAttribute(value = "pageURI")
    public String getPageURI() {
        return uri;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("records", repository.findAll(Sort.by(Sort.Direction.DESC, "id")));
        isDescending = true;
        return viewPath + "/index";
    }

    @GetMapping(value = "/create")
    public String create(Model model, HttpServletRequest request) {
        return viewPath + "/create";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("record", repository.findById(id).get());
        return viewPath + "/edit";
    }

    @GetMapping(value = "/table")
    public String table(Model model) {
        model.addAttribute("records", repository.findAll());
        return viewPath + "/table";
    }

    @GetMapping(value = "/json")
    @ResponseBody
    public List<T> json(@RequestHeader("user-agent") String language) {
        System.out.println(language);
        return repository.findAll();
    }

    @GetMapping(value = "/json/{id}")
    @ResponseBody
    public T detailJson(@PathVariable("id") int id) {
        return repository.findById(id).get();
    }

    @PostMapping()
    @Transactional
    public String save(T model) {
        repository.save(model);
        return "redirect:/" + uri + "?success";
    }

    @PostMapping(value = "/save-json")
    @Transactional
    @ResponseBody
    public boolean saveJson(T model) {
        repository.save(model);
        return true;
    }

    public String generateMD5(String text) throws NoSuchAlgorithmException {
        String token[] = text.split("\\.");
        String extension = token[token.length - 1];
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(text.getBytes(), 0, text.length());
        return new BigInteger(1, m.digest()).toString(16) + '.' + extension;
    }

    public float roundOff(float x) {
        return (float) (Math.floor(x * 100) / 100);
    }

    public Workbook getWorkbook(MultipartFile file) {
        Workbook workbook = null;
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            if (extension.equalsIgnoreCase("xlsx")) {
                workbook = new XSSFWorkbook(file.getInputStream());
            } else if (extension.equalsIgnoreCase("xls")) {
                workbook = new HSSFWorkbook(file.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
