import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  customerId: number =0;
  dateCreated: string ='';
  total: number = 0;
  shippingFee: number = 0;
  listId: number[] =[];

  constructor(private _activatedRoute: ActivatedRoute) {
  }

  ngOnInit(): void {


  }

}
