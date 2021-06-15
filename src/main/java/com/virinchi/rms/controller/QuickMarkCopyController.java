package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.QuickMarkCopy;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/quickmarkcopy")
public class QuickMarkCopyController extends CRUDController<QuickMarkCopy> {

    public QuickMarkCopyController() {
        viewPath = "quickmarkcopy";
        uri = "quickmarkcopy";
        pageTitle = "QuickMarkCopy";
        activeMenu = "quickmarkcopy";
    }

}
