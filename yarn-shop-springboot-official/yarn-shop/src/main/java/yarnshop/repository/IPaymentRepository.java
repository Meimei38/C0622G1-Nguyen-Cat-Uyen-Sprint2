package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import yarnshop.model.payment.Payment;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
}
