import {Payment} from "./payment";
import {Customer} from "./customer/customer";

export interface ShippingInformation {
  id: number
  shippingAddress: string;

  shippingReceiver: string;
  receiverPhone: string;
  payment: Payment
  customer: Customer;
}
