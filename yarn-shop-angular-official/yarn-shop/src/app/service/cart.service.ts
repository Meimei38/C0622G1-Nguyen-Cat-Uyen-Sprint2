import {Injectable} from '@angular/core';
import {Customer} from "../model/customer/customer";
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {ProductDetail} from "../model/product-detail";
import {OrderDetailDto} from "../dto/order-detail-dto";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private API_CART = environment.API_LOCAL + 'carts';

  constructor(private _httpClient: HttpClient) {
  }

  getCustomerByAccount(accountId: String): Observable<OrderDetailDto[]> {
    return this._httpClient.get<OrderDetailDto[]>(this.API_CART + '/list?accountId=' + accountId);
  }


}
