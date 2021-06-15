/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.virinchi.rms.auth.controller;

import com.virinchi.rms.auth.entity.Role;
import com.virinchi.rms.controller.core.CRUDController;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author apple
 */
@Controller
@RequestMapping(value = "/roles")
public class RoleController extends CRUDController<Role> {

    public RoleController() {
        viewPath = "auth/roles";
        uri = "roles";
        pageTitle = "Role";
        activeMenu = "auth";
    }

}
