package yarnshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import yarnshop.dto.OrderDetailDto;
import yarnshop.service.product.ICartService;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/carts")
public class CartController {
    @Autowired
    ICartService cartService;
    @GetMapping("/list")
    public ResponseEntity<List<OrderDetailDto>>getOrderDetailList(@RequestParam("accountId") String accountId){
        List<OrderDetailDto> orderDetailList = cartService.findOrderDetailList(accountId);
        if (orderDetailList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailList, HttpStatus.OK);
    }

//    @GetMapping("/add")
//    public ResponseEntity<OrderDetail>addOrderDetail(@RequestParam("orderId") String orderId, @RequestParam("accountId") String accountId){
//        OrderDetail orderDetail = cartService.findOrderDetailByAccountId( orderId, accountId);
//        if (orderDetail == null){
//            cartService.addOrderDetail(String orderId, String accountId)
//        }
//        }
    }

