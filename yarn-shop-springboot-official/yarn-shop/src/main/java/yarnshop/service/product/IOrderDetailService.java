package yarnshop.service.product;

import yarnshop.dto.OrderDetailDto;
import yarnshop.model.payment.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetail findOrderDetailByProductDetailIdAndAccountId(String productDetailId, String accountId);

    void saveOrderDetail(OrderDetail orderDetail);

    OrderDetail findOrderDetailById(Integer id);

    List<OrderDetailDto> findOderDetailByPaymentId(Integer paymentId);

    void updateStatus(Integer id);

}
