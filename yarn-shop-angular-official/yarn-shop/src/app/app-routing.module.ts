import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {LogInComponent} from "./component/user/log-in/log-in.component";
import {ShopComponent} from "./component/shop/shop.component";
import {ProductDetailComponent} from "./component/product/product-detail/product-detail.component";
import {CartComponent} from "./component/product/cart/cart.component";
import { CheckoutComponent } from './component/checkout/checkout.component';
import {AuthGuardGuard} from "./guard/auth-guard.guard";
import {LoginGuard} from "./guard/login.guard";
import {PaymentCheckComponent} from "./component/payment-check/payment-check.component";


const routes: Routes = [
  {path:'', component:HomeComponent},
  {path:'home', component: HomeComponent},
  {path:'login', component: LogInComponent, canActivate: [LoginGuard]},
  {path:'shop', component: ShopComponent},
  {path:'product-detail/:productId', component: ProductDetailComponent},
  {path:'cart', component: CartComponent, canActivate:[AuthGuardGuard]},
  {path:'check-out', component: CheckoutComponent, canActivate:[AuthGuardGuard]},
  {path:'payment-check', component: PaymentCheckComponent, canActivate:[AuthGuardGuard]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
