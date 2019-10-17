package com.sandi.authorizationsvc.service;

import com.sandi.authorizationsvc.model.Customer;
import com.sandi.authorizationsvc.model.Role;
import com.sandi.authorizationsvc.service.client.CustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private CustomerClient customerClient;

    @Override
    public UserDetails loadUserByUsername(final String customerName) {
        Customer customer = customerClient.getCustomerbyCustomerName(customerName);
        Set<Role> roles = customer.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new User(customer.getCustomerName(), customer.getPassword(), true, true, true, true, authorities);
    }

}
