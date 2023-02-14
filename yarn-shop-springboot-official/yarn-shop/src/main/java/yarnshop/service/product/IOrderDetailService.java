package yarnshop.service.product;

import yarnshop.model.payment.OrderDetail;

public interface IOrderDetailService {
    OrderDetail findOrderDetailByProductDetailIdAndAccountId(String productDetailId, String accountId);

    void saveOrderDetail(OrderDetail orderDetail);
}
