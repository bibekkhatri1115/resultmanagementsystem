package com.virinchi.rms.auth.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.virinchi.rms.auth.entity.UserLog;
import com.virinchi.rms.auth.repository.UserLogRepository;
import com.virinchi.rms.auth.repository.UserRepository;
import com.virinchi.rms.auth.service.AuthUserDetail;
import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLogRepository userLogRepository;
    @Autowired
    private HttpSession session;

    @Transactional
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {

        if (auth.isAuthenticated()) {
            UserLog log = new UserLog();
            AuthUserDetail detail = (AuthUserDetail) auth.getPrincipal();
            System.out.println("logged " + auth.getPrincipal());
            log.setUser(detail.getUser());
            log.setIpAddress(request.getRemoteAddr());
            log.setLoginDate(new Date());
            userLogRepository.save(log);
            session.setAttribute("loggedUser", detail.getUser());
            userRepository.updateLastLogin(detail.getUser().getId());
        }
        super.onAuthenticationSuccess(request, response, auth);
    }

}
