package yarnshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import yarnshop.dto.OrderDetailDto;
import yarnshop.model.payment.OrderDetail;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ICartRepository extends JpaRepository<OrderDetail, Integer> {
    @Query(value = "select order_detail.id as orderId, order_detail.quantity as orderQuantity, " +
            "order_detail.customer_id as customerId, product_detail.quantity as totalQuantity, " +
            "product_detail.color as productColor, product.name as productName, " +
            "product.price as productPrice, product.weight as productWeight, product.id as productIdDetail, " +
            "product.discount_id as discountId, " +
            "discount.discount_description as discountDescription," +
            "image.image_url as imageUrl " +
            "from order_detail \n" +
            "join product_detail on order_detail.product_detail_id = product_detail.id\n" +
            "join product on product_detail.product_id = product.id\n" +
            "join discount on product.discount_id = discount.id " +
            "join customer on order_detail.customer_id = customer.id " +
            "join account on customer.account_id = account.id" +
            " join image on product.id = image.product_id" +
            " where account.id =:account_id and order_detail.is_delete = 0" +
            " group by order_detail.id", nativeQuery = true)
    List<OrderDetailDto> findOrderDetailList(@Param("account_id") String accountId);
//    @Query(value = "select order_detail.* from order_detail\n" +
//            "join customer on order_detail.customer_id = customer.id\n" +
//            "join account on customer.account_id = account.id\n" +
//            "where account.id = :accountId;", nativeQuery = true)
//    OrderDetail findByAccountId(@Param("orderId") String orderId, @Param("accountId") String accountId);
}
