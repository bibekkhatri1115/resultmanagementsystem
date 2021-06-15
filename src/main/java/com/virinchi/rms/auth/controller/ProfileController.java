package com.virinchi.rms.auth.controller;

import com.virinchi.rms.controller.core.SecuredController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/profile")
public class ProfileController extends SecuredController {

    public ProfileController() {
        viewPath = "auth/profile";
        pageTitle = "User";
        activeMenu = "auth";
    }

    @GetMapping()
    public String index() {
        return viewPath + "/index";
    }

}
