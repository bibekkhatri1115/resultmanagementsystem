package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.Program;
import com.virinchi.rms.entity.ProgramSemesterSubject;
import com.virinchi.rms.entity.Semester;
import com.virinchi.rms.repository.ProgramRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/program")
public class ProgramController extends CRUDController<Program> {
@Autowired
private ProgramRepository programRepository;
    public ProgramController() {
        viewPath = "program";
        uri = "program";
        pageTitle = "Program";
        activeMenu = "master";
//        programRepository.getOne(1).getSemesterSubject().put(null, null)
    }
        @GetMapping(value = "/semester/details/{id}")
    @ResponseBody
    public List<ProgramSemesterSubject> getProgram(@PathVariable("id")int id) {
        return programRepository.getOne(id).getRelations();
    }
    
    
}
