package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yarnshop.service.payment.IPaymentService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    @Autowired
    IPaymentService paymentService;
}
