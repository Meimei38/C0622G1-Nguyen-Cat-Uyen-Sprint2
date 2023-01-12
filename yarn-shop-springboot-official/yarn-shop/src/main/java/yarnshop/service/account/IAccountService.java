package yarnshop.service.account;


import yarnshop.model.account.Account;

import java.util.Optional;

public interface IAccountService {

    Iterable<Account> findAll();

    Optional<Account> findById(Integer id);

    Account save(Account account);

    void remove(Integer id);

    Account findAccountByUserName(String userName);

}
