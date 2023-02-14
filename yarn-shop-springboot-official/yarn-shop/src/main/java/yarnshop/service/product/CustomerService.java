package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.model.customer.Customer;
import yarnshop.repository.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService{
    @Autowired
    ICustomerRepository customerRepository;
    @Override
    public Customer findCustomerByAccountId(String accountId) {
        return customerRepository.findCustomerByAccountId(accountId);
    }
}
