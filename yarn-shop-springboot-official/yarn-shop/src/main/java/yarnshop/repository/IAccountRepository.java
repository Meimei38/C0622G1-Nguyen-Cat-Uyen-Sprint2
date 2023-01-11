package com.project.be.repository.account;

import com.project.be.model.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    @Query(
            value = " select * " +
                    " from account" +
                    " where username = :username ",
            nativeQuery = true
    )
    Account findByUserName(@Param("username") String username);

}
