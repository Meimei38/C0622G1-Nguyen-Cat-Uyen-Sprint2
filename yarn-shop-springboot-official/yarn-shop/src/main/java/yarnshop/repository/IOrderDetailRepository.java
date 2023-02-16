package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yarnshop.dto.OrderDetailDto;
import yarnshop.model.payment.OrderDetail;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "select order_detail.* from order_detail\n" +
            "join customer on order_detail.customer_id = customer.id\n" +
            "join account on customer.account_id = account.id\n" +
            "where product_detail_id =:productDetailId and account.id = :accountId and order_detail.is_delete = 0", nativeQuery = true)
    OrderDetail findByProductDetailIdAndAccountId(@Param("productDetailId") String productDetailId, @Param("accountId") String accountId);

    @Query(value = "select order_detail.id as orderId, order_detail.quantity as orderQuantity, \n" +
            "        order_detail.customer_id as customerId, product_detail.quantity as totalQuantity,\n" +
            "                product_detail.color as productColor, product.name as productName, \n" +
            "                product.price as productPrice, product.weight as productWeight, \n" +
            "                product.discount_id as discountId, \n" +
            "                discount.discount_description as discountDescription,\n" +
            "                image.image_url as imageUrl \n" +
            "                from order_detail \n" +
            "                 join product_detail on order_detail.product_detail_id = product_detail.id\n" +
            "                 join product on product_detail.product_id = product.id\n" +
            "                 join discount on product.discount_id = discount.id \n" +
            "                join customer on order_detail.customer_id = customer.id \n" +
            "                join account on customer.account_id = account.id\n" +
            "                 join image on product.id = image.product_id\n" +
            "                 join payment on payment.id = order_detail.payment_id\n" +
            "                 where payment.id =:paymentId\n" +
            "                 group by order_detail.id", nativeQuery = true)
    List<OrderDetailDto> findByPaymentId(@Param("paymentId") Integer paymentId);

    @Query(value = "select order_detail.* from order_detail" +
            " where order_detail.id =:id and order_detail.is_delete = 0", nativeQuery = true)
    OrderDetail findOrderById(@Param("id") Integer id);
    @Modifying
    @Query(value = "update order_detail set `is_delete` = 2 " +
            "where (payment_id =:id);", nativeQuery = true)
    void updateStatus(@Param("id") Integer id);
}
