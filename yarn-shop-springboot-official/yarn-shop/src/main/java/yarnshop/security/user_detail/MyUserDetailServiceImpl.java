package com.project.be.security.user_detail;

import com.project.be.model.account.Account;
import com.project.be.service.account.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IAccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.findAccountByUserName(username);

        if (account == null) {
            throw new UsernameNotFoundException("Username not found!");
        }

        return new MyUserDetail(account);
    }
}
