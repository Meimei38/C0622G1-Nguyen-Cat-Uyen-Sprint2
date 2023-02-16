package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.dto.OrderDetailDto;
import yarnshop.model.payment.OrderDetail;
import yarnshop.repository.IOrderDetailRepository;

import java.util.List;

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

    @Override
    public OrderDetail findOrderDetailById(Integer id) {
        return orderDetailRepository.findOrderById(id);
    }

    @Override
    public List<OrderDetailDto> findOderDetailByPaymentId(Integer paymentId) {
        return orderDetailRepository.findByPaymentId(paymentId);
    }

    @Override
    public void updateStatus(Integer id) {
        orderDetailRepository.updateStatus(id);
    }
}
