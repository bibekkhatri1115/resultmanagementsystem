package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.Subject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/subject")
public class SubjectController extends CRUDController<Subject> {

    public SubjectController() {
        viewPath = "subject";
        uri = "subject";
        pageTitle = "Subject";
        activeMenu = "master";
    }

}
