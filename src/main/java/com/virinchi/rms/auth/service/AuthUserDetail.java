package com.virinchi.rms.auth.service;

import java.util.Collection;
import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AuthUserDetail extends User {

    private static final long serialVersionUID = 1L;
    com.virinchi.rms.auth.entity.User user;
    private Date loginDate;

    public AuthUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public void setUser(com.virinchi.rms.auth.entity.User user) {
        this.user = user;
    }

    public com.virinchi.rms.auth.entity.User getUser() {
        return user;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

}
