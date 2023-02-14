package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yarnshop.model.payment.OrderDetail;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "select order_detail.* from order_detail\n" +
            "join customer on order_detail.customer_id = customer.id\n" +
            "join account on customer.account_id = account.id\n" +
            "where product_detail_id =:productDetailId and account.id = :accountId and order_detail.is_delete = 0", nativeQuery = true)
    OrderDetail findByProductDetailIdAndAccountId(@Param("productDetailId") String productDetailId, @Param("accountId") String accountId);


}
