package com.example.demo.payload.reponse;

import com.example.demo.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtRespone {
    private String token;
    private Collection<? extends GrantedAuthority> roles;
    private User user;

    public JwtRespone(String token, Collection<? extends GrantedAuthority> roles,
                      User user) {
        this.token = token;
        this.roles = roles;
        this.user = user;
    }

    public JwtRespone() {
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
