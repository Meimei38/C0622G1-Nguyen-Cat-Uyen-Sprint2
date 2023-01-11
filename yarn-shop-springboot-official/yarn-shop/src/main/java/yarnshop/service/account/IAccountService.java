package com.project.be.service.account;

import com.project.be.model.account.Account;
import com.project.be.service.IGeneralService;

public interface IAccountService extends IGeneralService<Account> {

    Account findAccountByUserName(String userName);

}
