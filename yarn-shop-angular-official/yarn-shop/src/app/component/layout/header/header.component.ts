import {Component, OnInit} from '@angular/core';
import {Customer} from "../../../model/customer/customer";
import {TokenService} from "../../../service/token.service";
import {Router} from "@angular/router";
import {Account} from "../../../model/account/account";
import { CartService } from 'src/app/service/cart.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  checkLogin: boolean;
  nameAccount: any;
  currentUser: Account;
  accountRole: string;

  sum: string;

  constructor(private tokenService: TokenService,
              private router: Router,
              private _cartService: CartService) {
  }

  ngOnInit(): void {
    if (this.tokenService.isLogged()) {
      this.checkLogin = true;
      this.currentUser = JSON.parse(this.tokenService.getAccount());
      this.nameAccount = this.currentUser.username;
      const roles = this.tokenService.getRole();

      for (let i = 0; i < roles.length; i++) {
        if (roles[i] === 'ROLE_ADMIN') {
          this.accountRole = 'ROLE_ADMIN';
        }
      }

    }
  }

  logOut() {
    this.tokenService.logOut();
    this.router.navigateByUrl('home').then(() => {
      location.reload();
    });
  }

}
