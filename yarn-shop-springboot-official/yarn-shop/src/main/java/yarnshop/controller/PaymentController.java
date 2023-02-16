package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yarnshop.dto.PaymentDto;
import yarnshop.model.customer.Customer;
import yarnshop.model.payment.OrderDetail;
import yarnshop.model.payment.Payment;
import yarnshop.model.shipping.ShippingInformation;
import yarnshop.service.payment.IPaymentService;
import yarnshop.service.product.ICustomerService;
import yarnshop.service.product.IOrderDetailService;
import yarnshop.service.product.IShippingInformationService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
    @Autowired
    IPaymentService paymentService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    IOrderDetailService orderDetailService;
    @Autowired
    IShippingInformationService shippingInformationService;

    @GetMapping("addPayment")
    public ResponseEntity<Payment> addPayment(@RequestParam(value = "total") Double total, @RequestParam(value = "listId") List<Integer> listId, @RequestParam(value = "customerId") Integer customerId, @RequestParam(value = "dateCreated") String dateCreated, @RequestParam(value = "shippingFee") Integer shippingFee) {
        Customer customer = customerService.findCustomerById(customerId);
        Payment payment = new Payment();
        payment.setPaymentStatus(0);
        payment.setCustomer(customer);
        payment.setDateCreated(dateCreated);
        payment.setShippingFee(shippingFee);
        payment.setIsDelete(0);
        payment.setTotalBill(total);
        paymentService.save(payment);
        for (int i = 0; i < listId.size(); i++) {
            OrderDetail orderDetail = orderDetailService.findOrderDetailById(listId.get(i));
            orderDetail.setPayment(payment);
            orderDetailService.saveOrderDetail(orderDetail);
        }
        return new ResponseEntity<>(payment, HttpStatus.OK);

    }

    @GetMapping("findPayment")
    public ResponseEntity<Payment> findPaymentById(@RequestParam(value = "id") Integer paymentId) {
        Payment payment = paymentService.findPaymentById(paymentId);
        if (payment == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(payment, HttpStatus.OK);
    }

    @GetMapping("updatePayment")
    public ResponseEntity<String> updatePayment(@RequestParam("paymentId") String paymentId, @RequestParam("shippingAddress") String address,
                                                @RequestParam("shippingReceiver") String receiver, @RequestParam("shippingPhone") String phone){
        Payment oldPayment = paymentService.findPaymentById(Integer.parseInt(paymentId));
        ShippingInformation shippingInformation = new ShippingInformation();
        shippingInformation.setPayment(oldPayment);
        shippingInformation.setShippingAddress(address);
        shippingInformation.setShippingReceiver(receiver);
        shippingInformation.setReceiverPhone(phone);
        shippingInformation.setCustomer(oldPayment.getCustomer());
        shippingInformationService.save(shippingInformation);
        oldPayment.setShippingInformation(shippingInformation);
        oldPayment.setPaymentStatus(1);
        paymentService.save(oldPayment);
        orderDetailService.updateStatus(Integer.parseInt(paymentId));
        return new ResponseEntity<> ("Payment updated",HttpStatus.OK);
    }

    @GetMapping("paymentCheck")
    public ResponseEntity<List<PaymentDto>>getListPayment(@RequestParam(value = "accountId")String accountId){
        List<PaymentDto> listPayment = paymentService.getListPayment(accountId);
        if (listPayment.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(listPayment, HttpStatus.OK);
    }


}
