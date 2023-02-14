package yarnshop.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import yarnshop.dto.OrderDetailDto;
import yarnshop.model.payment.OrderDetail;
import yarnshop.repository.ICartRepository;

import java.util.List;

@Service
public class CartService implements ICartService{
    @Autowired
    ICartRepository cartRepository;
    @Override
    public List<OrderDetailDto> findOrderDetailList(String accountId) {
        return cartRepository.findOrderDetailList(accountId);
    }

//    @Override
//    public OrderDetail findOrderDetailByAccountId(String orderId, String accountId) {
//        return cartRepository.findByAccountId(orderId, accountId);
//    }
}
