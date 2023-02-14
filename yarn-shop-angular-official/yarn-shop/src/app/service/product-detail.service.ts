import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product/product";
import {environment} from "../../environments/environment";
import {ProductDetail} from "../model/product-detail";
import {Image} from "../model/image";

@Injectable({
  providedIn: 'root'
})
export class ProductDetailService {
  private API_PRODUCT_DETAIL = environment.API_LOCAL + 'product-detail';

  constructor(private _httpClient: HttpClient) {
  }
  findProductDetailByProductId(idProduct: number): Observable<ProductDetail[]> {
    return this._httpClient.get<ProductDetail[]>(this.API_PRODUCT_DETAIL + '/search?idProduct=' + idProduct);
  }
  findImagesByProductId(idProduct: number): Observable<Image[]>{
    return this._httpClient.get<Image[]>(this.API_PRODUCT_DETAIL+'/images?idProduct=' + idProduct);
  }
}
