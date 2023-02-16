package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yarnshop.dto.PaymentDto;
import yarnshop.model.payment.Payment;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
    @Query(value = "select payment.date_created as dateCreated, \n" +
            "payment.payment_status as status, \n" +
            "payment.total_bill as totalBill, \n" +
            "payment.shipping_fee as fee, \n" +
            "shipping_information.shipping_address as address, \n" +
            "shipping_information.shipping_receiver as receiver, \n" +
            "shipping_information.receiver_phone as phone\n" +
            "from payment \n" +
            "left join shipping_information on shipping_information.id = payment.shipping_information_id\n" +
            "join customer on payment.customer_id = customer.id\n" +
            "join account on customer.account_id = account.id\n" +
            "where payment.payment_status = 1 and account.id =:accountId", nativeQuery = true)
    List<PaymentDto> getListPayments(@Param("accountId") String accountId);
}
