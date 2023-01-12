package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import yarnshop.model.account.Account;

public interface IAccountRepository extends JpaRepository<Account, Integer> {

    @Query(
            value = " select * " +
                    " from account" +
                    " where username = :username ",
            nativeQuery = true
    )
    Account findByUserName(@Param("username") String username);

}
