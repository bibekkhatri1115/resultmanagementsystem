package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.Guardian;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/guardian")
public class GuardianController extends CRUDController<Guardian> {

    public GuardianController() {
        viewPath = "guardian";
        uri = "guardian";
        pageTitle = "Guardian";
        activeMenu = "guardian";
    }

}
