package com.virinchi.rms.controller.core;

import java.math.BigInteger;
import java.security.MessageDigest;
import org.springframework.web.bind.annotation.ModelAttribute;

public abstract class SiteController {

    protected String pageTitle;
    protected String viewPath;
    protected String activeMenu;

    @ModelAttribute(value = "pageTitle")
    public String getPageTitle() {
        return pageTitle;
    }

    @ModelAttribute(value = "viewPath")
    public String getViewPath() {
        return viewPath;
    }

    @ModelAttribute(value = "activeMenu")
    public String getActiveMenu() {
        return activeMenu;
    }

    public String generateMD5(String text) throws Exception {
        String token[] = text.split("\\.");
        String extension = token[token.length - 1];
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(text.getBytes(), 0, text.length());
        return new BigInteger(1, m.digest()).toString(16) + '.' + extension;
    }
}
