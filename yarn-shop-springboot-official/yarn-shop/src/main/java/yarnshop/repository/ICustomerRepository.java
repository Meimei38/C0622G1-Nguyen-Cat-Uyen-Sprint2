package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yarnshop.model.customer.Customer;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICustomerRepository extends JpaRepository<Customer, Integer> {
    @Query(value = "select customer.* from customer\n" +
            "join account on customer.account_id = account.id\n" +
            "where account.id =:accountId", nativeQuery = true)
    Customer findCustomerByAccountId(@Param("accountId") String accountId);
}
