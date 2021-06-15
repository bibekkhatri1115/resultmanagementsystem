package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.QuickMarkMBAGrade;
import com.virinchi.rms.entity.Subject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "/mark/quick/mba/grade")
public class QuickMarkMBAGradeController extends CRUDController<QuickMarkMBAGrade> {

    public QuickMarkMBAGradeController() {
        viewPath = "quickmark/grade/mba";
        uri = "mark/quick/mba/grade";
        pageTitle = "Quick Mark MBA";
        activeMenu = "quickmarkgrade";
    }
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
            if (counter <= 3) {
                Row row = rows.next();
                Subject sub = new Subject();
                sub.setId(counter);
                sub.setName(formatter.formatCellValue(row.getCell(1)));
                sub.setSubjectCode(formatter.formatCellValue(row.getCell(2)));
                sub.setCreditHour(Integer.parseInt(formatter.formatCellValue(row.getCell(3))));
                sub.setFullMarks(Integer.parseInt(formatter.formatCellValue(row.getCell(4))));
                subjects.add(sub);
            } else if (counter == 4) {
                rows.next();                
                rows.next();
                rows.next();

            } else if (counter > 6) {
                Row row = rows.next();
                if (row.getCell(1).getCellType() == Cell.CELL_TYPE_BLANK) {
                    break;
                }
                QuickMarkMBAGrade mark = new QuickMarkMBAGrade();
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
                mark.setSubject1Mark((formatter.formatCellValue(row.getCell(2))));
                mark.setSubject2Mark((formatter.formatCellValue(row.getCell(3))));
                mark.setSubject3Mark((formatter.formatCellValue(row.getCell(4))));
                float subject1Gpa = getGradePoint(mark.getSubject1Mark());
                float subject2Gpa = getGradePoint(mark.getSubject2Mark());
                float subject3Gpa = getGradePoint(mark.getSubject3Mark());
                float totalCreditHour = mark.getSubject1CreditHour() + mark.getSubject2CreditHour() + mark.getSubject3CreditHour();
                double gpa = getPoint(subject1Gpa, mark.getSubject1CreditHour()) + getPoint(subject2Gpa, mark.getSubject2CreditHour()) + getPoint(subject3Gpa, mark.getSubject3CreditHour());
                mark.setGpa(roundOff(getGPA((float) gpa, totalCreditHour)));
                repository.save(mark);
            }
            if (!rows.hasNext()) {
                break;
            }
            counter++;
        }
        return "redirect:/" + uri + "?success";
    }

    private double getPoint(double gpa, int creditHour) {
        return gpa * creditHour;
    }

    private float getGPA(float gradePoint, float totalCreditHour) {
        return (gradePoint / totalCreditHour);
    }

    private float getGradePoint(String grade) {
        float gpa = 0.00f;
        switch (grade) {
            case "F":
                gpa = 0.00f;
                break;
            case "D":
                gpa = 1.00f;
                break;
            case "D+":
                gpa = 1.50f;
                break;
            case "C-":
                gpa = 1.75f;
                break;
            case "C":
                gpa = 2.00f;
                break;
            case "C+":
                gpa = 2.50f;
                break;
            case "B-":
                gpa = 2.75f;
                break;
            case "B":
                gpa = 3.00f;
                break;
            case "B+":
                gpa = 3.50f;
                break;
            case "A-":
                gpa = 3.75f;
                break;
            case "A":
                gpa = 4.00f;
                break;
        }
                return Math.round(gpa*100)/100;
    }
    
        @Override
    public boolean saveJson(QuickMarkMBAGrade model) {
        float subject1Gpa = getGradePoint(model.getSubject1Mark());        
        float subject2Gpa = getGradePoint(model.getSubject2Mark());
        float subject3Gpa = getGradePoint(model.getSubject3Mark());
        float totalCreditHour = model.getSubject1CreditHour() + model.getSubject2CreditHour() + model.getSubject3CreditHour();
        float totalGradePoint = subject1Gpa + subject2Gpa + subject3Gpa;
        model.setGpa(roundOff(getGPA(totalGradePoint, totalCreditHour)));
        return super.saveJson(model);
    }
}
