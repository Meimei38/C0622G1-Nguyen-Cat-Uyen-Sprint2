package yarnshop.service.payment;

import yarnshop.dto.PaymentDto;
import yarnshop.model.payment.Payment;

import java.util.List;

public interface IPaymentService {
    void save(Payment payment);

    Payment findPaymentById(Integer paymentId);

    List<PaymentDto> getListPayment(String accountId);
}
