import {Customer} from "./customer/customer";
import {ProductDetail} from './product-detail';
import {Payment} from "./payment";

export interface OrderDetail {
  id: number;
  quantity: number;
  isDelete: number;
  payment: Payment;
  customer: Customer;
  productDetail: ProductDetail;

}
