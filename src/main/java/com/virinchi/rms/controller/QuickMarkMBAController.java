package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.QuickMarkMBA;
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
@RequestMapping(value = "/mark/quick/mba")
public class QuickMarkMBAController extends CRUDController<QuickMarkMBA> {

    public QuickMarkMBAController() {
        viewPath = "quickmark/mba";
        uri = "mark/quick/mba";
        pageTitle = "Quick Mark MBA";
        activeMenu = "quickmark";
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
                QuickMarkMBA mark = new QuickMarkMBA();
                mark.setMatricNo(formatter.formatCellValue(row.getCell(0)));
                mark.setStudentName(formatter.formatCellValue(row.getCell(1)));
                mark.setProgram(program);
                mark.setSemester(semester);
                mark.setSubject1Name(subjects.get(0).getName());
                mark.setSubject1Code(subjects.get(0).getSubjectCode());
                mark.setSubject1CreditHour(subjects.get(0).getCreditHour());
                mark.setSubject1FullMark(subjects.get(0).getFullMarks());
                mark.setSubject2Name(subjects.get(1).getName());
                mark.setSubject2Code(subjects.get(1).getSubjectCode());
                mark.setSubject2FullMark(subjects.get(1).getFullMarks());
                mark.setSubject2CreditHour(subjects.get(1).getCreditHour());
                mark.setSubject3Name(subjects.get(2).getName());
                mark.setSubject3Code(subjects.get(2).getSubjectCode());
                mark.setSubject3CreditHour(subjects.get(2).getCreditHour());
                mark.setSubject3FullMark(subjects.get(2).getFullMarks());
                mark.setSubject1Mark(Float.parseFloat(formatter.formatCellValue(row.getCell(2))));
                mark.setSubject2Mark(Float.parseFloat(formatter.formatCellValue(row.getCell(3))));
                mark.setSubject3Mark(Float.parseFloat(formatter.formatCellValue(row.getCell(4))));
                float subject1Gpa = getGpa(mark.getSubject1Mark(), mark.getSubject1FullMark());
                float subject2Gpa = getGpa(mark.getSubject2Mark(), mark.getSubject2FullMark());
                float subject3Gpa = getGpa(mark.getSubject3Mark(), mark.getSubject3FullMark());
                float totalCreditHour = mark.getSubject1CreditHour() + mark.getSubject2CreditHour() + mark.getSubject3CreditHour();
                float gpa = getPoint(subject1Gpa, mark.getSubject1CreditHour()) + getPoint(subject2Gpa, mark.getSubject2CreditHour()) + getPoint(subject3Gpa, mark.getSubject3CreditHour());
                mark.setGpa(roundOff(getGPA(gpa, totalCreditHour)));
                mark.setSubject1Grade(getGrade(getGpa(mark.getSubject1Mark(), mark.getSubject1FullMark())));
                mark.setSubject2Grade(getGrade(getGpa(mark.getSubject2Mark(), mark.getSubject2FullMark())));
                mark.setSubject3Grade(getGrade(getGpa(mark.getSubject3Mark(), mark.getSubject3FullMark())));
                if (mark.getSubject1Mark() < (mark.getSubject1FullMark() * 0.34) || mark.getSubject2Mark() < (mark.getSubject2FullMark() * 0.34) || mark.getSubject3Mark() < (mark.getSubject3FullMark() * 0.34)) {
                    mark.setGrade(getGrade(0));
                } else {
                    mark.setGrade(getGrade(getGPA(gpa, totalCreditHour)));
                }
                repository.save(mark);
            }
            if (!rows.hasNext()) {
                break;
            }
            counter++;
        }
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
            point = 2.50f;
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

    private String getGrade(float gpa) {
        String grade = "";
        if (gpa == 0.00) {
            grade = "F";
        } else if (gpa <= 1.00) {
            grade = "D";
        } else if (gpa <= 1.50) {
            grade = "D+";
        } else if (gpa <= 1.75) {
            grade = "C-";
        } else if (gpa <= 2.00) {
            grade = "C";
        } else if (gpa <= 2.50) {
            grade = "C+";
        } else if (gpa <= 2.75) {
            grade = "B-";
        } else if (gpa <= 3.00) {
            grade = "B";
        } else if (gpa <= 3.50) {
            grade = "B+";
        } else if (gpa <= 3.75) {
            grade = "A-";
        } else if (gpa <= 4.00) {
            grade = "A";
        }
        return grade;
    }

    private float getPoint(float gpa, int creditHour) {
        return gpa * creditHour;
    }

    private float getGPA(float gradePoint, float totalCreditHour) {
        return (gradePoint / totalCreditHour);
    }

    @Override
    public boolean saveJson(QuickMarkMBA model) {
        float subject1Gpa = getGpa(model.getSubject1Mark(), model.getSubject1FullMark());
        float subject2Gpa = getGpa(model.getSubject2Mark(), model.getSubject2FullMark());
        float subject3Gpa = getGpa(model.getSubject3Mark(), model.getSubject3FullMark());
        float totalCreditHour = model.getSubject1CreditHour() + model.getSubject2CreditHour() + model.getSubject3CreditHour();
        float totalGradePoint = subject1Gpa + subject2Gpa + subject3Gpa;
        float GPA = getGPA(totalGradePoint*3, totalCreditHour);
        model.setSubject1Grade(getGrade(getGpa(model.getSubject1Mark(), model.getSubject1FullMark())));
        model.setSubject2Grade(getGrade(getGpa(model.getSubject2Mark(), model.getSubject2FullMark())));
        model.setSubject3Grade(getGrade(getGpa(model.getSubject3Mark(), model.getSubject3FullMark())));
        model.setGpa(GPA);
        model.setGrade(getGrade(GPA));
        System.out.println(model);
        return super.saveJson(model);
    }
}
