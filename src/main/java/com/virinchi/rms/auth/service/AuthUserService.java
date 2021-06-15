package com.virinchi.rms.auth.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import com.virinchi.rms.auth.entity.User;
import com.virinchi.rms.auth.repository.UserRepository;
import com.virinchi.rms.auth.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String param) throws UsernameNotFoundException {
        User authUser = userRepository.findByUserName(param);
        if (authUser == null) {
            throw new UsernameNotFoundException("UserName Not Matched");
        }
        AuthUserDetail userDetail = new AuthUserDetail(authUser.getUserName(), authUser.getPassword(),
                getAuthorities(param));
        userDetail.setUser(authUser);
        userDetail.setLoginDate(new Date());
        return userDetail;
    }

    private Collection<? extends GrantedAuthority> getAuthorities(String user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        userRoleRepository.findByUserUserName(user).forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole().getName()));
        });
        return authorities;
    }

}
