package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yarnshop.model.customer.Customer;
import yarnshop.service.product.ICustomerService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    @Autowired
    ICustomerService customerService;

    @GetMapping("/getCustomer")
    public ResponseEntity<Customer> getCustomerById(@RequestParam("id") Integer id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }
}
