package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.ProgramSemesterSubject;
import com.virinchi.rms.repository.ProgramRepository;
import com.virinchi.rms.repository.ProgramSemesterSubjectRepository;
import com.virinchi.rms.repository.SemesterRepository;
import com.virinchi.rms.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/program/semester/subject")
public class ProgramSemesterSubjectController extends CRUDController<ProgramSemesterSubject> {
@Autowired    
ProgramSemesterSubjectRepository repository;
@Autowired 
SubjectRepository subjectRepository;
@Autowired 
SemesterRepository semesterRepository;
@Autowired 
ProgramRepository programRepository;
    public ProgramSemesterSubjectController() {
        viewPath = "program/semester/subject";
        uri = "program/semester/subject";
        pageTitle = "Program Semester Subject";
        activeMenu = "programsemestersubject";
    }

 @PostMapping(value = "/store")
    @Transactional
    public String store(@RequestParam("subject.id") int[] subjects,@RequestParam("program.id") int program,@RequestParam("semester.id") int semester) {
        for(int subject : subjects){
            ProgramSemesterSubject model = new ProgramSemesterSubject();
            model.setSubject(subjectRepository.getOne(subject));
            model.setProgram(programRepository.getOne(program));
            model.setSemester(semesterRepository.getOne(semester));
            repository.save(model);
        }
        return "redirect:/" + uri + "?success";
    }
}
