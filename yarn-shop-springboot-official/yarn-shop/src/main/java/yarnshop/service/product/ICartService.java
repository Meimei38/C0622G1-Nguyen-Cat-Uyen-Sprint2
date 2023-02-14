package yarnshop.service.product;

import yarnshop.dto.OrderDetailDto;
import yarnshop.model.payment.OrderDetail;

import java.util.List;

public interface ICartService {
    List<OrderDetailDto> findOrderDetailList(String accountId);

//    OrderDetail findOrderDetailByAccountId( String orderId, String accountId);
}
