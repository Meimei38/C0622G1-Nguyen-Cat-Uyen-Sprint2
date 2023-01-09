import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../../service/token.service";
import {User} from "../../../model/user/User";
import {Router} from "@angular/router";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  user?: User;
  checkLogin = false;
  roles?:string[];
  constructor(private _tokenService: TokenService,
              private _router: Router) {
  }

  ngOnInit(): void {
    if (this._tokenService.getTokenLocal()) {
      this.checkLogin = true;
      this.user = JSON.parse(this._tokenService.getUser());
    }
  }

  logout() {
    this._tokenService.logOut();
    this._router.navigate(['']).then(() => {
      location.reload();
    })
  }
}
