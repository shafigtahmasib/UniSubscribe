package com.example.unsubscribe.services;

import com.example.unsubscribe.models.AppUser;
import com.example.unsubscribe.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class MyUserPrincipal implements UserDetails {
    private AppUser user;
    private List<GrantedAuthority> authorityList;

    public MyUserPrincipal(AppUser user) {
        this.user = user;
        authorityList = getAuthorities(user.getRoles());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(user.getPassword());
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private List<GrantedAuthority> getAuthorities(
            List<Role> roles) {
        List<GrantedAuthority> authorities
                = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName().toString()));
        }
        return authorities;
    }
}
