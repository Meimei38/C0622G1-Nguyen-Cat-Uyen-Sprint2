import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../model/product/product";
import {Category} from "../model/product/category";
import {Brand} from "../model/product/brand";
import {ProductDto} from "../dto/product-dto";
import {Image} from "../model/image";

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private API_PRODUCT = environment.API_LOCAL + 'products';
  private API_PRODUCT_CATEGORY = this.API_PRODUCT + '/category';
  private API_PRODUCT_BRAND = this.API_PRODUCT + '/brand';

  constructor(private _httpClient: HttpClient) {
  }

  getAllProduct(pageNumber: number, searchName: String, searchCategory: String, searchBrand: String, searchPrice: String): Observable<any> {
    return this._httpClient.get(this.API_PRODUCT + '?page=' + pageNumber + '&searchName=' + searchName + '&searchCategory=' + searchCategory + '&searchBrand=' + searchBrand + '&searchPrice=' + searchPrice);
  }

  getAllCategory(): Observable<Category[]> {
    return this._httpClient.get<Category[]>(this.API_PRODUCT_CATEGORY);
  }

  getAllBrand(): Observable<Brand[]> {
    return this._httpClient.get<Brand[]>(this.API_PRODUCT_BRAND);
  }

  findProductByProductId(idProduct: number): Observable<ProductDto> {
    return this._httpClient.get<ProductDto>(this.API_PRODUCT+'/list?idProduct='+idProduct);
  }

}
