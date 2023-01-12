package yarnshop.payload.response;


import org.springframework.security.core.GrantedAuthority;
import yarnshop.model.account.Account;
import yarnshop.model.customer.Customer;

import java.util.Collection;

public class JwtRespone {

    private String token;
    private String type = "Bearer";
    private Account account;
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    private Collection<? extends GrantedAuthority> roles;

    public JwtRespone(String token, String type, Account account, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.type = type;
        this.account = account;
        this.roles = roles;
    }

    public JwtRespone(String token, Account account, Collection<? extends GrantedAuthority> authorities) {
        this.token = token;
        this.account = account;
        this.roles = authorities;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }
}
