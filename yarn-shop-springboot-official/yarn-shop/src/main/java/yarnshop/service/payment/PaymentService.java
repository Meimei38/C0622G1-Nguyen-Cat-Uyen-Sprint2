package yarnshop.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.dto.PaymentDto;
import yarnshop.model.payment.Payment;
import yarnshop.repository.IPaymentRepository;

import java.util.List;

@Service
public class PaymentService implements IPaymentService{
    @Autowired
    IPaymentRepository paymentRepository;
    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Payment findPaymentById(Integer paymentId) {
        return paymentRepository.findById(paymentId).get();
    }

    @Override
    public List<PaymentDto> getListPayment(String accountId) {
        return paymentRepository.getListPayments(accountId);
    }
}
