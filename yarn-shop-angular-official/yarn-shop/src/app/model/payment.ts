import {Customer} from "./customer/customer";
import {ShippingInformation} from "./shipping-information";

export interface Payment {
id: number;
paymentStatus: number;
totalBill: number;
dateCreated: string;
isDelete: number;
shippingFee: number;
shippingInformation: ShippingInformation;
customer: Customer;


}
