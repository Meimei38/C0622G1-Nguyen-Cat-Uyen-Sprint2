package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.model.payment.OrderDetail;
import yarnshop.repository.IOrderDetailRepository;

@Service
public class OrderDetailService implements IOrderDetailService{
    @Autowired
    IOrderDetailRepository orderDetailRepository;
    @Override
    public OrderDetail findOrderDetailByProductDetailIdAndAccountId(String productDetailId, String accountId) {
        return orderDetailRepository.findByProductDetailIdAndAccountId(productDetailId, accountId);
    }

    @Override
    public void saveOrderDetail(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
}
