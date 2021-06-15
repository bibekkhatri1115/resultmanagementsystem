package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.Registration;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController extends CRUDController<Registration> {

    public RegistrationController() {
        viewPath = "registration";
        uri = "registration";
        pageTitle = "Registration";
        activeMenu = "registration";
    }

}
