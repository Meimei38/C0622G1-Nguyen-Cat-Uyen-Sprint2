package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yarnshop.model.customer.Customer;
import yarnshop.model.payment.OrderDetail;
import yarnshop.model.product.ProductDetail;
import yarnshop.service.product.ICustomerService;
import yarnshop.service.product.IOrderDetailService;
import yarnshop.service.product.IProductDetailService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/order-detail")
public class OrderDetailController {
    @Autowired
    IOrderDetailService orderDetailService;
    @Autowired
    IProductDetailService productDetailService;
    @Autowired
    ICustomerService customerService;


    @GetMapping("/add")
    public ResponseEntity<String> addOrderDetail(@RequestParam(value = "productDetailId") String productDetailId, @RequestParam(value = "accountId") String accountId, @RequestParam(value = "quantity") String quantity) {
        OrderDetail orderDetail = orderDetailService.findOrderDetailByProductDetailIdAndAccountId(productDetailId, accountId);
        ProductDetail productDetail = productDetailService.findByProductDetailId(productDetailId);
        if (productDetail.getQuantity() == 0) {
            return new ResponseEntity<>("Quantity = 0", HttpStatus.BAD_REQUEST);
        }
        if (orderDetail == null) {
            Customer customer = customerService.findCustomerByAccountId(accountId);
            orderDetail = new OrderDetail();
            orderDetail.setQuantity(Integer.parseInt(quantity));
            orderDetail.setCustomer(customer);
            orderDetail.setProductDetail(productDetail);
            orderDetail.setIsDelete(0);
            orderDetailService.saveOrderDetail(orderDetail);
            productDetail.setQuantity(productDetail.getQuantity()-1);
            return new ResponseEntity(HttpStatus.CREATED);
        }
        orderDetail.setQuantity(orderDetail.getQuantity() + Integer.parseInt(quantity));
        orderDetailService.saveOrderDetail(orderDetail);
        productDetail.setQuantity(productDetail.getQuantity() - 1);
        return new ResponseEntity(HttpStatus.OK);
    }

}
