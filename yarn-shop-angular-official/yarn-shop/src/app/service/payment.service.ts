import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {OrderDetailDto} from "../dto/order-detail-dto";
import {Payment} from "../model/payment";
import {PaymentDto} from "../dto/payment-dto";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private API_PAYMENT = environment.API_LOCAL + 'payments';


  constructor(private _httpClient: HttpClient) {
  }

  getPayment(total: number, listId: number[], customerId: String, dateCreated: string, shippingFee: number): Observable<Payment> {
    return this._httpClient.get<Payment>(this.API_PAYMENT + '/addPayment?total=' + total + '&listId=' + listId + '&customerId=' + customerId + '&dateCreated=' + dateCreated + '&shippingFee=' + shippingFee);
  }

  findPaymentById(paymentId:number):Observable<Payment> {
    return this._httpClient.get<Payment>(this.API_PAYMENT + '/findPayment?id=' + paymentId);
  }
  paymentCheck(accountId: string):Observable<PaymentDto[]> {
    return this._httpClient.get<PaymentDto[]>(this.API_PAYMENT + '/paymentCheck?accountId=' + accountId);
  }
  updatePayment(paymentId: String, address: String, phone: String, receiver: String):Observable<String> {
    return this._httpClient.get<String>(this.API_PAYMENT + '/updatePayment?paymentId='+paymentId+
    '&shippingAddress='+ address
    +'&shippingReceiver='+ receiver
    +'&shippingPhone='+ phone);
  }

}
