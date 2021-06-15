package com.virinchi.rms.controller.core;

import javax.servlet.http.HttpSession;
import com.virinchi.rms.auth.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class SecuredController extends SiteController {

    @Autowired
    private HttpSession session;

    protected String activeMenu;

    @ModelAttribute("currentUser")
    public User getLoggedInUser() {
        return (User)session.getAttribute("loggedUser");
    }
    
    @ModelAttribute(value = "activeMenu")
    public String getActiveMenu() {
        return activeMenu;
    }

}
