import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from "./form-login/login/login.component";
import {ContentComponent} from "./home/content/content.component";
import {UserAccountComponent} from "./form-login/user-account/user-account.component";


const routes: Routes = [

  {path: 'login', component: LoginComponent, data: {title: 'Login'}},
  {path: '', component: ContentComponent, data: {title: 'Home'}},
  {path:'user-account',component:UserAccountComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
