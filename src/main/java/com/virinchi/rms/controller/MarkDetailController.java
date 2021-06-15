package com.virinchi.rms.controller;

import com.virinchi.rms.controller.core.CRUDController;
import com.virinchi.rms.entity.MarkDetail;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/markdetail")
public class MarkDetailController extends CRUDController<MarkDetail> {

    public MarkDetailController() {
        viewPath = "markdetail";
        uri = "markdetail";
        pageTitle = "MarkDetail";
        activeMenu = "markdetail";
    }

}
