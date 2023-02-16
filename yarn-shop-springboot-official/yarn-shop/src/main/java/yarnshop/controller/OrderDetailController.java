package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yarnshop.dto.OrderDetailDto;
import yarnshop.model.customer.Customer;
import yarnshop.model.payment.OrderDetail;
import yarnshop.model.product.ProductDetail;
import yarnshop.service.product.ICustomerService;
import yarnshop.service.product.IOrderDetailService;
import yarnshop.service.product.IProductDetailService;

import java.util.List;

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
            productDetail.setQuantity(productDetail.getQuantity() - orderDetail.getQuantity());
            return new ResponseEntity(HttpStatus.CREATED);
        }
        orderDetail.setQuantity(orderDetail.getQuantity() + Integer.parseInt(quantity));
        orderDetailService.saveOrderDetail(orderDetail);
        productDetail.setQuantity(productDetail.getQuantity() - orderDetail.getQuantity());
        productDetailService.saveProductDetail(productDetail);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/findOrderDetailsByPaymentId")
    public ResponseEntity<List<OrderDetailDto>> getOrderDetailsByPaymentId(@RequestParam(value = "id") Integer paymentId) {
        List<OrderDetailDto> orderDetailList = orderDetailService.findOderDetailByPaymentId(paymentId);
        if (orderDetailList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailList, HttpStatus.OK);
    }

    @GetMapping("/deleteOrder")
    public ResponseEntity<String> deleteOrderDetail(@RequestParam(value = "id") Integer id) {
        OrderDetail orderDetail = orderDetailService.findOrderDetailById(id);
        if (orderDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetail.setIsDelete(1);
        ProductDetail productDetail = orderDetail.getProductDetail();
        productDetail.setQuantity(productDetail.getQuantity() + orderDetail.getQuantity());
        productDetailService.saveProductDetail(productDetail);
        orderDetailService.saveOrderDetail(orderDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/updateOrderQuantity")
    public ResponseEntity<String> updateOrderQuantity(@RequestParam(value = "id") Integer id, @RequestParam(value = "quantity") Integer quantity) {
        OrderDetail orderDetail = orderDetailService.findOrderDetailById(id);
        if (orderDetail == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetail.setQuantity(quantity);
        orderDetailService.saveOrderDetail(orderDetail);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
