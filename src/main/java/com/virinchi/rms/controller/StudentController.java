package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.Semester;
import com.virinchi.rms.entity.Student;
import com.virinchi.rms.entity.Subject;
import com.virinchi.rms.repository.StudentRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/student")
public class StudentController extends CRUDController<Student> {

    @Autowired
    private StudentRepository studentRepository;

    public StudentController() {
        viewPath = "student";
        uri = "student";
        pageTitle = "Student";
        activeMenu = "student";
    }

    @GetMapping(value = "/semester/{semesterId}/{programId}")
    @ResponseBody
    public List<Subject> getProgram(@PathVariable("semesterId") int semesterId, @PathVariable("programId") int programId) {
        List<Object> list = studentRepository.findSemester(semesterId, programId);
        List<Subject> subjects = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object[] row = (Object[]) list.get(i);
            Subject subject = new Subject();
            subject.setId((int) row[0]);
            subject.setName((String) row[1]);
            subject.setSubjectCode((String) row[2]);
            subject.setCreditHour((int) row[3]);
            subjects.add(subject);
        }
        return subjects;
    }

}
