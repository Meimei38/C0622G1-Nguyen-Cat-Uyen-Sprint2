import {Brand} from "./brand";
import {Category} from "./category";
import {Discount} from "../discount";
import {Image} from "../image";
import {ProductDetail} from "../product-detail";

export interface Product {
  id?: number;
  name?: string;
  description?: string;
  isDelete?: number;
  weight?: number;
  price?: number;
  brand?: Brand;
  category?: Category;
  discount?: Discount;
  image?: Image[];
  productDetail?: ProductDetail;

}
