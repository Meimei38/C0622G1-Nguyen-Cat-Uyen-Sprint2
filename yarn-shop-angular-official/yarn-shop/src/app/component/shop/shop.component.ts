import {Component, OnInit} from '@angular/core';
import {Brand} from "../../model/product/brand";
import {PageProduct} from "../../dto/page-product";
import {ProductService} from "../../service/product.service";
import {Category} from "../../model/product/category";
import {FormBuilder, FormGroup} from "@angular/forms";
import {OrderDetailService} from "../../service/order-detail.service";
import {TokenService} from 'src/app/service/token.service';
import {ProductDetailService} from "../../service/product-detail.service";

@Component({
  selector: 'app-shop',
  templateUrl: './shop.component.html',
  styleUrls: ['./shop.component.css']
})
export class ShopComponent implements OnInit {
  brand: Brand;
  pageProduct: PageProduct | undefined;
  categoryList: Category[];
  brandList: Brand[];
  searchBrand: String = '';
  searchPrice: String = '40';
  searchCategory: String = '';
  searchName: String = '';

  constructor(private _productService: ProductService,
              private _productDetailService: ProductDetailService,
              private _tokenService: TokenService) {
  }

  ngOnInit(): void {
    this.getProductCategory();
    this.getProductBrand();
    this.getPageProduct();
  }

  private getPageProduct() {
    this._productService.getAllProduct(0, this.searchName, this.searchCategory, this.searchBrand, this.searchPrice).subscribe(data => {
      this.pageProduct = data;
      console.log(this.pageProduct.totalPages);
      console.log(this.pageProduct.number + 1 == this.pageProduct.totalPages);
    })
  }

  goToPage(i: number) {
    this._productService.getAllProduct(i, this.searchName, this.searchCategory, this.searchBrand, this.searchPrice).subscribe(data => {
      this.pageProduct = data;
      console.log(this.pageProduct);
    })
  }

  private getProductCategory() {
    this._productService.getAllCategory().subscribe(data => {
      this.categoryList = data;
    })
  }

  private getProductBrand() {
    this._productService.getAllBrand().subscribe(data => {
      this.brandList = data;
    })
  }

  search() {
    this.getPageProduct();
  }

  clear() {
    this.searchBrand = '';
    this.searchPrice = '40';
    this.searchCategory = '';
    this.searchName = '';
    this.search();
  }

}
