import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {OrderDetailDto} from "../dto/order-detail-dto";
import {Payment} from "../model/payment";

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  private API_PAYMENT = environment.API_LOCAL + 'payments';


  constructor(private _httpClient: HttpClient) {
  }

  getPayment(listId: number[], customerId: String, dateCreated: string, shippingFee: number): Observable<Payment> {
    return this._httpClient.get<Payment>(this.API_PAYMENT + '/addPayment?listId=' + listId + "&customerId=" + customerId + "&dateCreated=" + dateCreated + "&shippingFee=" + shippingFee);
  }
}
