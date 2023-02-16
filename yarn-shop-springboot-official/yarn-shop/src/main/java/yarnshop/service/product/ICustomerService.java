package yarnshop.service.product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yarnshop.model.customer.Customer;

public interface ICustomerService  {
    Customer findCustomerByAccountId(String accountId);

    Customer findCustomerById(Integer customerId);
}
