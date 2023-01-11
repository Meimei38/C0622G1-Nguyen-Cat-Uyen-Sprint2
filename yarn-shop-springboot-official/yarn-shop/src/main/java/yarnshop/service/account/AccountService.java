package com.project.be.service.account;

import com.project.be.model.account.Account;
import com.project.be.repository.account.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private IAccountRepository accountRepository;

    @Override
    public Iterable<Account> findAll() {
        return null;
    }

    @Override
    public Optional<Account> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Account save(Account account) {
        return null;
    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public Account findAccountByUserName(String userName) {
        return accountRepository.findByUserName(userName);
    }
}
