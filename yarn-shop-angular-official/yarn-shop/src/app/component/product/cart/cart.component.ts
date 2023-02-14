import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import * as $ from "jquery";
import {TokenService} from "../../../service/token.service";
import {CartService} from 'src/app/service/cart.service';
import {ProductDetail} from "../../../model/product-detail";
import {OrderDetailDto} from "../../../dto/order-detail-dto";
import {OrderDetailService} from 'src/app/service/order-detail.service';
import {Payment} from "../../../model/payment";
import { ActivatedRoute, Router } from '@angular/router';
import { PaymentService } from 'src/app/service/payment.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  cartList: OrderDetailDto[];
  account: Account;
  text: string;
  sum: number = 0;
  orderListId: number [] = [];
  checkedAll: boolean;
  total: number;


  constructor(private _tokenService: TokenService,
              private _cartService: CartService,
              private _orderDetailService: OrderDetailService,
              private _router: Router,
              private _paymentService: PaymentService) {
  }

  ngOnInit(): void {
    this.account = JSON.parse(this._tokenService.getAccount());
    this._cartService.getCustomerByAccount(this.account.id).subscribe(data => {
      this.cartList = data;
    });
  }


  increase(id: number, price: string) {
    let quantity = parseInt($('#quantity' + id).val() + '');
    quantity += 1;
    $('#quantity' + id).val(quantity);
    this.calculateMoney(id, price, quantity + '');
    this.sum += parseFloat(price);
  }


  decrease(id: number, price: string) {
    let quantity = parseInt($('#quantity' + id).val() + '');
    quantity -= 1;
    $('#quantity' + id).val(quantity);
    this.calculateMoney(id, price, quantity + '');
    this.sum -= parseFloat(price);
  }

  calculateMoney(id: number, price: String, quantity: string) {
    if (parseInt(quantity) <= 0) {
      $('#quantity' + id).val(1);
    } else {
      let total: any = 1;
      total = parseFloat(<string>price) * parseInt(quantity);
      $('#total' + id).text(total.toFixed(2));
    }
  }

  countSum(orderListId: number[]) {
    // this.sum = 0;
    // for (let i = 0; i < this.cartList.length; i++) {
    //   let number = parseInt($('#quantity' + i).val() + '');
    //   this.sum += number * parseFloat(<string>this.cartList[i].productPrice);
    // }
    let tempt = 0;
    for (const item of this.cartList) {
      if (orderListId.includes(item.orderId, 0)) {
        tempt += parseFloat(<string>item.productPrice) * parseFloat(<string>item.orderQuantity);
      }
      console.log(this.orderListId);
      console.log(this.cartList);
    }
    this.sum = tempt;

  }


  updateMoney(i: number, productPrice: String, quantity: string) {
    this.calculateMoney(i, productPrice, quantity);
  }


  addToPayment(orderId: number) {
    const index = this.orderListId.indexOf(orderId, 0);
    index > -1 ? this.orderListId.splice(index, 1) : this.orderListId.push(orderId);
    this.countSum(this.orderListId);
    console.log(this.orderListId);
    console.log(orderId);
  }

  addAll() {
    this.checkedAll = true;
    for (const value of this.cartList) {
      if (!this.orderListId.includes(value.orderId)) {
        this.checkedAll = false;
        break;
      }
    }
    if (this.checkedAll) {
      for (const value of this.cartList) {
        const index = this.orderListId.indexOf(value.orderId, 0);
        this.orderListId.splice(index, 1);
      }
    } else {
      for (const value of this.cartList) {
        const index = this.orderListId.indexOf(value.orderId, 0);
        if (index == -1) {
          this.orderListId.push(value.orderId);
        }
      }
    }
    this.countSum(this.orderListId);
    console.log(this.orderListId);
  }

  createPayment() {
    let shippingFee: number = 0;
    let totalWeight: number = 0;
    for (let item of this.cartList) {
      totalWeight += parseInt(<string>item.productWeight);
    }
    if (totalWeight <= 300) {
      shippingFee = 1;
    } else if (totalWeight <= 1000) {
      shippingFee = 2;
    } else {
      shippingFee = 3;
    }
    let customerId = this.cartList[0].customerId;
    let dateCreated = new Date().toLocaleString();
    this._paymentService.getPayment(this.orderListId, customerId, dateCreated, shippingFee).subscribe(data=> {
      console.log(data);
    })
  }
}
