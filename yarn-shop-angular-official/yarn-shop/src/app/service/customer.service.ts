import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import { Customer } from '../model/customer/customer';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private API_CUSTOMER = environment.API_LOCAL + 'customer';

  constructor(private _httpClient: HttpClient) {
  }

  findCustomerById(id: number): Observable<Customer> {
    return this._httpClient.get<Customer>(this.API_CUSTOMER+'/getCustomer?id='+id);
  }
}
