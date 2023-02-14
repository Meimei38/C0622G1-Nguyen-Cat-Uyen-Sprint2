import {Product} from "./product/product";
import {OrderDetail} from "./order-detail";

export interface ProductDetail {
  id?: String;
  color?: String;
  quantity?: String;
  isDelete?: String;
  product?: Product;
  orderDetail?: OrderDetail;

}
