import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {Observable} from "rxjs";
import {OrderDetailDto} from "../dto/order-detail-dto";
import {OrderDetail} from "../model/order-detail";

@Injectable({
  providedIn: 'root'
})
export class OrderDetailService {
  private API_ORDER_DETAIL = environment.API_LOCAL + 'order-detail';

  constructor(private _httpClient: HttpClient) { }

  checkAndAddOrderDetail(productDetailId: number, accountId: number, quantity: number): Observable<String> {
     return this._httpClient.get<String>(this.API_ORDER_DETAIL + '/add?productDetailId='+ productDetailId+'&accountId='+ accountId+'&quantity='+ quantity);
  }
}
