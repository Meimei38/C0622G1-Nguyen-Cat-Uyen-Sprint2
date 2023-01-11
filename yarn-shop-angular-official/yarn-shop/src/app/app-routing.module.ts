import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from "./component/home/home.component";
import {LogInComponent} from "./component/user/log-in/log-in.component";


const routes: Routes = [
  {path:'home', component: HomeComponent},
  {path:'login', component: LogInComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
