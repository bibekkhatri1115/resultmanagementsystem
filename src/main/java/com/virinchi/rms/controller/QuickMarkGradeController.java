package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.QuickMarkGrade;
import com.virinchi.rms.entity.Subject;
import com.virinchi.rms.repository.QuickMarkGradeRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/mark/quick/grade")
public class QuickMarkGradeController extends CRUDController<QuickMarkGrade> {

    public QuickMarkGradeController() {
        viewPath = "quickmark/grade";
        uri = "mark/quick/grade";
        pageTitle = "Quick Mark";
        activeMenu = "quickmarkgrade";
    }

    @Autowired
    QuickMarkGradeRepository repository;

    @PostMapping(value = "/upload")
    @Transactional
    public String upload(@RequestParam("files") MultipartFile file) throws Exception {
        repository.deleteAll();
        Workbook workbook = getWorkbook(file);
        List<Subject> subjects = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rows = sheet.iterator();
        Row header = rows.next();
        String program = formatter.formatCellValue(header.getCell(6));
        String semester = formatter.formatCellValue(header.getCell(8));
        int counter = 1;
        while (rows.hasNext()) {
            if (counter <= 5) {
                Row row = rows.next();
                Subject sub = new Subject();
                sub.setId(counter);
                sub.setName(formatter.formatCellValue(row.getCell(1)));
                sub.setSubjectCode(formatter.formatCellValue(row.getCell(2)));
                sub.setCreditHour(Integer.parseInt(formatter.formatCellValue(row.getCell(3))));
                sub.setFullMarks(Integer.parseInt(formatter.formatCellValue(row.getCell(4))));
                subjects.add(sub);
            } else if (counter == 6) {
                rows.next();
            } else if (counter > 6) {
                Row row = rows.next();
                if (row.getCell(1).getCellType() == Cell.CELL_TYPE_BLANK) {
                    break;
                }
                QuickMarkGrade mark = new QuickMarkGrade();
                mark.setMatricNo(formatter.formatCellValue(row.getCell(0)));
                mark.setStudentName(formatter.formatCellValue(row.getCell(1)));
                mark.setProgram(program);
                mark.setSemester(semester);
                mark.setSubject1Name(subjects.get(0).getName());
                mark.setSubject1Code(subjects.get(0).getSubjectCode());
                mark.setSubject1CreditHour(subjects.get(0).getCreditHour());
                mark.setSubject2Name(subjects.get(1).getName());
                mark.setSubject2Code(subjects.get(1).getSubjectCode());
                mark.setSubject2CreditHour(subjects.get(1).getCreditHour());
                mark.setSubject3Name(subjects.get(2).getName());
                mark.setSubject3Code(subjects.get(2).getSubjectCode());
                mark.setSubject3CreditHour(subjects.get(2).getCreditHour());
                mark.setSubject4Name(subjects.get(3).getName());
                mark.setSubject4Code(subjects.get(3).getSubjectCode());
                mark.setSubject4CreditHour(subjects.get(3).getCreditHour());
                mark.setSubject5Name(subjects.get(4).getName());
                mark.setSubject5Code(subjects.get(4).getSubjectCode());
                mark.setSubject5CreditHour(subjects.get(4).getCreditHour());
                mark.setSubject1Mark((formatter.formatCellValue(row.getCell(2))));
                mark.setSubject2Mark((formatter.formatCellValue(row.getCell(3))));
                mark.setSubject3Mark((formatter.formatCellValue(row.getCell(4))));
                mark.setSubject4Mark((formatter.formatCellValue(row.getCell(5))));
                mark.setSubject5Mark((formatter.formatCellValue(row.getCell(6))));
                float subject1Gpa = getGradePoint(mark.getSubject1Mark());
                float subject2Gpa = getGradePoint(mark.getSubject2Mark());
                float subject3Gpa = getGradePoint(mark.getSubject3Mark());
                float subject4Gpa = getGradePoint(mark.getSubject4Mark());
                float subject5Gpa = getGradePoint(mark.getSubject5Mark());
                float totalCreditHour = mark.getSubject1CreditHour() + mark.getSubject2CreditHour() + mark.getSubject3CreditHour() + mark.getSubject4CreditHour() + mark.getSubject5CreditHour();
                double gpa = getPoint(subject1Gpa, mark.getSubject1CreditHour()) + getPoint(subject2Gpa, mark.getSubject2CreditHour()) + getPoint(subject3Gpa, mark.getSubject3CreditHour()) + getPoint(subject4Gpa, mark.getSubject4CreditHour()) + getPoint(subject5Gpa, mark.getSubject5CreditHour());
                mark.setGpa(getGPA((float) gpa, totalCreditHour));
                repository.save(mark);
            }
            if (!rows.hasNext()) {
                break;
            }
            counter++;
        }
//        repository.copyQuickMark();
        return "redirect:/" + uri + "?success";
    }

    private float getGpa(float mark, int totalMark) {
        float point = 0.00f;
        float percentage = (mark / totalMark) * 100;
        if (percentage <= 34) {
            point = 0.00f;
        } else if (percentage <= 39) {
            point = 1.00f;
        } else if (percentage <= 44) {
            point = 1.50f;
        } else if (percentage <= 49) {
            point = 1.75f;
        } else if (percentage <= 54) {
            point = 2.00f;
        } else if (percentage <= 59) {
            point = 2.5f;
        } else if (percentage <= 64) {
            point = 2.75f;
        } else if (percentage <= 69) {
            point = 3.0f;
        } else if (percentage <= 74) {
            point = 3.50f;
        } else if (percentage <= 79) {
            point = 3.75f;
        } else if (percentage <= 100) {
            point = 4.0f;
        }
        return point;
    }

    private double getPoint(double gpa, int creditHour) {
        return gpa * creditHour;
    }

    private float getGPA(float gradePoint, float totalCreditHour) {
        return (gradePoint / totalCreditHour);
    }

    private float getGradePoint(String grade) {
        float gpa = 0.00f;
        System.out.println(grade);
        switch (grade) {
            case "F":
                gpa = 0.00f;
                break;
            case "D":
                gpa = 1.00f;
                break;
            case "D+":
                gpa = 1.33f;
                break;
            case "C-":
                gpa = 1.67f;
                break;
            case "C":
                gpa = 2.00f;
                break;
            case "C+":
                gpa = 2.33f;
                break;
            case "B-":
                gpa = 2.67f;
                break;
            case "B":
                gpa = 3.00f;
                break;
            case "B+":
                gpa = 3.33f;
                break;
            case "A-":
                gpa = 3.67f;
                break;
            case "A":
                gpa = 4.00f;
                break;
        }
                return Math.round(gpa*100)/100;
    }
    @Override
    public boolean saveJson(QuickMarkGrade model) {
        float subject1Gpa = getGradePoint(model.getSubject1Mark());        
        float subject2Gpa = getGradePoint(model.getSubject2Mark());
        float subject3Gpa = getGradePoint(model.getSubject3Mark());
        float subject4Gpa = getGradePoint(model.getSubject4Mark());        
        float subject5Gpa = getGradePoint(model.getSubject5Mark());
        float totalCreditHour = model.getSubject1CreditHour() + model.getSubject2CreditHour() + model.getSubject3CreditHour() + model.getSubject4CreditHour() + model.getSubject5CreditHour();
        float totalGradePoint = subject1Gpa + subject2Gpa + subject3Gpa + subject4Gpa + subject5Gpa;
        model.setGpa(getGPA(totalGradePoint, totalCreditHour));
        return super.saveJson(model);
    }
}
