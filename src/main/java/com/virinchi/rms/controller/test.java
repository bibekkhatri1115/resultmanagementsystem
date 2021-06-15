//package com.virinchi.rms.controller;
//
//import com.virinchi.rms.controller.core.CRUDController;
//import com.virinchi.rms.entity.QuickMark;
//import com.virinchi.rms.entity.Subject;
//import com.virinchi.rms.repository.QuickMarkCopyRepository;
//import com.virinchi.rms.repository.QuickMarkRepository;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//
//@Controller
//@RequestMapping(value = "/quickmark")
//public class QuickMarkController extends CRUDController<QuickMark> {
//
//    @Autowired
//    QuickMarkCopyRepository copyRepository;
//
//    @Autowired
//    QuickMarkRepository repository;
//
//    public QuickMarkController() {
//        viewPath = "quickmark";
//        uri = "quickmark";
//        pageTitle = "QuickMark";
//        activeMenu = "quickmark";
//    }
//
//    @PostMapping(value = "/upload")
//    @Transactional
//    public String upload(@RequestParam("files") MultipartFile file) throws Exception {
//        repository.deleteAll();
//        Workbook workbook = getWorkbook(file);
//        List<Subject> subjects = new ArrayList<>();
//        DataFormatter formatter = new DataFormatter();
//        Sheet sheet = workbook.getSheetAt(0);
//        Iterator<Row> rows = sheet.iterator();
//        Row header = rows.next();
//        String program = formatter.formatCellValue(header.getCell(6));
//        String semester = formatter.formatCellValue(header.getCell(8));
//        int counter = 1;
//        while (rows.hasNext()) {
//            if (counter <= 5) {
//                Row row = rows.next();
//                Subject sub = new Subject();
//                sub.setId(counter);
//                sub.setName(formatter.formatCellValue(row.getCell(1)));
//                sub.setSubjectCode(formatter.formatCellValue(row.getCell(2)));
//                sub.setCreditHour(Integer.parseInt(formatter.formatCellValue(row.getCell(3))));
//                sub.setFullMarks(Integer.parseInt(formatter.formatCellValue(row.getCell(4))));
//                subjects.add(sub);
//            } else if (counter == 6) {
//                rows.next();
//            } else if (counter > 6) {
//                Row row = rows.next();
//                if (row.getCell(1).getCellType() == Cell.CELL_TYPE_BLANK) {
//                    break;
//                }
//                QuickMark mark = new QuickMark();
//                mark.setMatricNo(formatter.formatCellValue(row.getCell(0)));
//                mark.setStudentName(formatter.formatCellValue(row.getCell(1)));
//                mark.setProgram(program);
//                mark.setSemester(semester);
//                mark.setSubject1Name(subjects.get(0).getName());
//                mark.setSubject1Code(subjects.get(0).getSubjectCode());
//                mark.setSubject1CreditHour(subjects.get(0).getCreditHour());
//                mark.setSubject1FullMark(subjects.get(0).getFullMarks());
//                mark.setSubject2Name(subjects.get(1).getName());
//                mark.setSubject2Code(subjects.get(1).getSubjectCode());
//                mark.setSubject2FullMark(subjects.get(1).getFullMarks());
//                mark.setSubject2CreditHour(subjects.get(1).getCreditHour());
//                mark.setSubject3Name(subjects.get(2).getName());
//                mark.setSubject3Code(subjects.get(2).getSubjectCode());
//                mark.setSubject3CreditHour(subjects.get(2).getCreditHour());
//                mark.setSubject3FullMark(subjects.get(2).getFullMarks());
//                mark.setSubject4Name(subjects.get(3).getName());
//                mark.setSubject4Code(subjects.get(3).getSubjectCode());
//                mark.setSubject4CreditHour(subjects.get(3).getCreditHour());
//                mark.setSubject4FullMark(subjects.get(3).getFullMarks());
//                mark.setSubject5Name(subjects.get(4).getName());
//                mark.setSubject5Code(subjects.get(4).getSubjectCode());
//                mark.setSubject5CreditHour(subjects.get(4).getCreditHour());
//                mark.setSubject5FullMark(subjects.get(4).getFullMarks());
//                mark.setSubject1Mark(Float.parseFloat(formatter.formatCellValue(row.getCell(2))));
//                mark.setSubject2Mark(Float.parseFloat(formatter.formatCellValue(row.getCell(3))));
//                mark.setSubject3Mark(Float.parseFloat(formatter.formatCellValue(row.getCell(4))));
//                mark.setSubject4Mark(Float.parseFloat(formatter.formatCellValue(row.getCell(5))));
//                mark.setSubject5Mark(Float.parseFloat(formatter.formatCellValue(row.getCell(6))));
//                float subject1Gpa = getGpa(mark.getSubject1Mark(), mark.getSubject1FullMark());
//                float subject2Gpa = getGpa(mark.getSubject2Mark(), mark.getSubject2FullMark());
//                float subject3Gpa = getGpa(mark.getSubject3Mark(), mark.getSubject3FullMark());
//                float subject4Gpa = getGpa(mark.getSubject4Mark(), mark.getSubject4FullMark());
//                float subject5Gpa = getGpa(mark.getSubject5Mark(), mark.getSubject5FullMark());
//                float gpa = subject1Gpa+subject2Gpa+subject3Gpa+subject4Gpa+subject5Gpa;
//                mark.setGpa(gpa/5);
//                repository.save(mark);
//            }
//            if (!rows.hasNext()) {
//                break;
//            }
//            counter++;
//        }
//        repository.copyQuickMark();
//        return "redirect:/" + uri + "?success";
//    }
//
//    private float getGpa(float mark,int totalMark){
//            float point = 0.00f;
//            float percentage = (mark / totalMark) * 100;
//            if (percentage <= 20) {
//                point = 0.8f;
//            } else if (percentage <= 40) {
//                point = 1.6f;
//            } else if (percentage <= 50) {
//                point = 2.0f;
//            } else if (percentage <= 60) {
//                point = 2.4f;
//            } else if (percentage <= 70) {
//                point = 2.8f;
//            } else if (percentage <= 80) {
//                point = 3.2f;
//            } else if (percentage <= 90) {
//                point = 3.6f;
//            } else if (percentage <= 100) {
//                point = 4.0f;
//            }
//            return point;
//        }
//
//        private Workbook getWorkbook(MultipartFile file) {
//        Workbook workbook = null;
//        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
//        try {
//            if (extension.equalsIgnoreCase("xlsx")) {
//                workbook = new XSSFWorkbook(file.getInputStream());
//            } else if (extension.equalsIgnoreCase("xls")) {
//                workbook = new HSSFWorkbook(file.getInputStream());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return workbook;
//    }
//
//    @Override
//    public boolean saveJson(QuickMark model) {
//        float subject1Gpa = getGpa(model.getSubject1Mark(), model.getSubject1FullMark());
//                float subject2Gpa = getGpa(model.getSubject2Mark(), model.getSubject2FullMark());
//                float subject3Gpa = getGpa(model.getSubject3Mark(), model.getSubject3FullMark());
//                float subject4Gpa = getGpa(model.getSubject4Mark(), model.getSubject4FullMark());
//                float subject5Gpa = getGpa(model.getSubject5Mark(), model.getSubject5FullMark());
//        float gpa = subject1Gpa+subject2Gpa+subject3Gpa+subject4Gpa+subject5Gpa;
//        model.setGpa(gpa/5);
//        return super.saveJson(model);
//    }
//
//}
