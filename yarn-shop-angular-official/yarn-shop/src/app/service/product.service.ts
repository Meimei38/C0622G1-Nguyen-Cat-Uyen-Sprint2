import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product/product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private API_PRODUCT_PAGE = environment.API_LOCAL + 'products';

  constructor(private _httpClient: HttpClient) { }
  getAllProduct(pageNumber: number): Observable<Product>{
    return this._httpClient.get<Product>(this.API_PRODUCT_PAGE+'?page='+pageNumber);
  }
}
