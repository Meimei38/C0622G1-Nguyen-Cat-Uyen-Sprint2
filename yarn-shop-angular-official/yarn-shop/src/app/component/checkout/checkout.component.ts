import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {PaymentService} from "../../service/payment.service";
import {OrderDetailService} from "../../service/order-detail.service";
import {Payment} from "../../model/payment";
import {OrderDetail} from "../../model/order-detail";
import {CustomerService} from 'src/app/service/customer.service';
import {Customer} from "../../model/customer/customer";
import {OrderDetailDto} from "../../dto/order-detail-dto";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ShippingInformation} from "../../model/shipping-information";
import {render} from "creditcardpayments/creditCardPayments";
import Swal from "sweetalert2";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  paymentId: number;
  payment: Payment;
  orderDetailDtoList: OrderDetailDto[];
  customer: Customer;
  note: boolean;
  default: boolean;
  shippingInformation: FormGroup;
  shippingInfor: ShippingInformation

  constructor(private _activatedRoute: ActivatedRoute,
              private _paymentService: PaymentService,
              private _orderDetailsService: OrderDetailService,
              private _customerService: CustomerService,
              private _formBuilder: FormBuilder,
              private _route: Router) {
  }

  ngOnInit(): void {
    this.paymentId = parseInt(this._activatedRoute.snapshot.queryParamMap.get('paymentId'));
    this._paymentService.findPaymentById(this.paymentId).subscribe(data => {
      this.payment = data;
    })
    this._orderDetailsService.findOrderDetailsByPaymentId(this.paymentId).subscribe(data => {
      this.orderDetailDtoList = data;
      this.findCustomerById(parseInt(<string>this.orderDetailDtoList[0].customerId))
    })
    this.note = false;
    this.default = false;
    this.shippingInformation = this._formBuilder.group({
      shippingReceiver: ['', [Validators.required]],
      shippingAddress: ['', [Validators.required]],
      receiverPhone: ['', [Validators.required]]
    })

  }

  findCustomerById(id: number) {
    this._customerService.findCustomerById(id).subscribe(data => {
      this.customer = data;
    })
  }


  orderNote() {
    this.note = !this.note;
    console.log(this.note);
  }

  selectShipping() {
    if (this.default == false) {
      $('#receiver-name').val(this.customer.firstName + ' ' + this.customer.lastName);
      this.shippingInformation.get('shippingReceiver').setValue(this.customer.firstName + ' ' + this.customer.lastName);
      $('#shippingAddress').val(this.customer.addressDetail + ', ' + this.customer.ward + ', ' + this.customer.district + ', ' + this.customer.city + ', ' + this.customer.country);
      this.shippingInformation.get('shippingAddress').setValue(this.customer.addressDetail + ', ' + this.customer.ward + ', ' + this.customer.district + ', ' + this.customer.city + ', ' + this.customer.country);
      $('#receiverPhone').val(this.customer.phone);
      this.shippingInformation.get('receiverPhone').setValue(this.customer.phone);
    } else {
      $('#receiver-name').val(null);
      $('#shippingAddress').val(null);
      $('#receiverPhone').val(null);
    }
    this.default = !this.default;
  }

  submit() {
    this.shippingInfor = this.shippingInformation.value;

    this.renderPayPalBtn();

  }

  renderPayPalBtn() {
    document.getElementById('paypalBtn').innerHTML = '<div id="paypalButtons" style="margin-top: 8px"></div>';
    render(
      {
        id: '#paypalButtons',
        value: this.payment.totalBill + '',
        currency: 'USD',
        onApprove: (details) => {
          this._paymentService.updatePayment(this.payment.id + '', this.shippingInfor.shippingAddress, this.shippingInfor.receiverPhone, this.shippingInfor.shippingReceiver).subscribe(data => {
          });
          Swal.fire({
            icon: 'success',
            title: 'Successfully Paid',
            text: 'Your order Successfully Paid',
          });
          this._route.navigateByUrl("/shop");
        }
      }
    );
  }
}
