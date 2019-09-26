package com.sandi.authorizationsvc.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Override
    public UserDetails loadUserByUsername(final String userName) {
        String[] roles = {"ROLE1", "ROLE2", "ROLE3"};
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(String role : roles){
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new User("Sandeep", "$2a$04$wYagTuKZoKQyvAMlOwnlseVD.WiOOUROnjGCHm7xQhJrI5shv/9Du", true, true, true, true, authorities);
    }

}
