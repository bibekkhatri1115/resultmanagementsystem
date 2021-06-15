package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.Semester;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/semester")
public class SemesterController extends CRUDController<Semester> {

    public SemesterController() {
        viewPath = "semester";
        uri = "semester";
        pageTitle = "Semester";
        activeMenu = "master";
    }

}
