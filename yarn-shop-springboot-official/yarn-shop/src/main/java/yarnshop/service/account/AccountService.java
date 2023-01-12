package yarnshop.service.account;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.model.account.Account;
import yarnshop.repository.IAccountRepository;

import java.util.Optional;

@Service
public class AccountService implements IAccountService {

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
