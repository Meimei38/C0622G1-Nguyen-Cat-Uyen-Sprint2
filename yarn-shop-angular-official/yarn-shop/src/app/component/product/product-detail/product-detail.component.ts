import {Component, OnInit} from '@angular/core';
import * as $ from "jquery";
import {ActivatedRoute} from "@angular/router";
import {ProductDetailService} from "../../../service/product-detail.service";
import {ProductDetail} from "../../../model/product-detail";
import {ProductService} from "../../../service/product.service";
import {Product} from "../../../model/product/product";
import {ProductDto} from "../../../dto/product-dto";
import {Image} from "../../../model/image";
import {OrderDetail} from "../../../model/order-detail";
import {OrderDetailService} from 'src/app/service/order-detail.service';
import {TokenService} from "../../../service/token.service";
import {Account} from "../../../model/account/account";
import Swal from 'sweetalert2';

@Component({
  selector: 'app-product-detail',
  templateUrl: './product-detail.component.html',
  styleUrls: ['./product-detail.component.css']
})
export class ProductDetailComponent implements OnInit {
  idProduct: number;
  product: ProductDto;
  productDetails: ProductDetail[];
  imageList: Image[];
  position: number = 0;
  productDetailId: number;
  quantity: number = 1;

  accountId: number;
  account: Account;
  productQuantity: number;

  constructor(private _activatedRoute: ActivatedRoute,
              private _productDetailService: ProductDetailService,
              private _productService: ProductService,
              private _orderDetailService: OrderDetailService,
              private _tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.idProduct = this._activatedRoute.snapshot.params.productId;
    this._productService.findProductByProductId(this.idProduct).subscribe(data => {
      this.product = data;
    })
    this._productDetailService.findProductDetailByProductId(this.idProduct).subscribe(data => {
      this.productDetails = data;
      this.productDetailId = parseInt(<string>this.productDetails[0].id);
      this.productQuantity = parseInt(<string>this.productDetails[0].quantity);
      console.log(this.productQuantity);
    })
    this._productDetailService.findImagesByProductId(this.idProduct).subscribe(data => {
      this.imageList = data;
    })
    $("#quantity").val(1);
    var proQty = $('.pro-qty');
    proQty.prepend('<span class="fa fa-angle-up dec qtybtn"></span>');
    proQty.append('<span class="fa fa-angle-down inc qtybtn"></span>');
    proQty.on('click', '.qtybtn', function () {
      var $button = $(this);
      var oldValue = $button.parent().find('input').val();
      if ($button.hasClass('inc')) {
        // @ts-ignore
        var newVal = parseFloat(oldValue) + 1;
      } else {
        // Don't allow decrementing below zero
        if (oldValue > 0) {
          // @ts-ignore
          var newVal = parseFloat(oldValue) - 1;
        } else {
          newVal = 0;
        }
      }
      $button.parent().find('input').val(newVal);
    });

    var proQty = $('.pro-qty-2');
    proQty.prepend('<span class="fa fa-angle-left dec qtybtn"></span>');
    proQty.append('<span class="fa fa-angle-right inc qtybtn"></span>');
    proQty.on('click', '.qtybtn', function () {
      var $button = $(this);
      var oldValue = $button.parent().find('input').val();
      console.log(oldValue)
      if ($button.hasClass('inc')) {
          // @ts-ignore
        var newVal = parseFloat(oldValue) + 1;
      } else {
        // Don't allow decrementing below zero
        if (oldValue > 0) {
          // @ts-ignore
          var newVal = parseFloat(oldValue) - 1;
        } else {
          newVal = 0;
        }
      }
      $button.parent().find('input').val(newVal);
    });
  }


  checkPosition(i: number) {
    this.position = i;
  }


  check(evt) {
    let target = evt.target;
    if (target.checked) {
      this.productDetailId = target.value;
      for (let i = 0; i < this.productDetails.length; i++) {
        if (this.productDetails[i].id == this.productDetailId +''){
          this.productQuantity = parseInt(<string>this.productDetails[i].quantity);
        }
      }
      console.log(this.productQuantity);
      $("#quantity").val(1);
    }
  }

  addOrderDetail() {
    let orderQuantity = $('#quantity').val();
    if (this.productQuantity < orderQuantity){
      Swal.fire({
        icon: 'error',
        title: 'Error',
        text: 'Order quantity is out of product quantity range, please try again',
      });
      $('#quantity').val(this.productQuantity);
    }else {
      this.account = JSON.parse(this._tokenService.getAccount());
      this.accountId = this.account.id;
      this.checkQuantity();
      this._orderDetailService.checkAndAddOrderDetail(this.productDetailId, this.accountId, this.quantity).subscribe(data => {
        Swal.fire({
          icon: 'success',
          title: 'Successfully Added',
          text: 'Product Successfully Added',
        });
        this.ngOnInit();
      })
    }
  }

  checkQuantity() {
    this.quantity = parseInt($("#quantity").val()+'');
    console.log(this.quantity);
  }
}
