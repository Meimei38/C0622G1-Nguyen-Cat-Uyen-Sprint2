import {Component, OnInit} from '@angular/core';
import {PaymentService} from "../../service/payment.service";
import {PaymentDto} from "../../dto/payment-dto";
import {TokenService} from "../../service/token.service";

@Component({
  selector: 'app-payment-check',
  templateUrl: './payment-check.component.html',
  styleUrls: ['./payment-check.component.css']
})
export class PaymentCheckComponent implements OnInit {
  paymentList: PaymentDto[];
  account: Account;

  constructor(private _paymentService: PaymentService,
              private _tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.account = JSON.parse(this._tokenService.getAccount());
   this._paymentService.paymentCheck(this.account.id).subscribe(data => {
     this.paymentList = data;
     console.log(this.paymentList);
    });
  }

}
