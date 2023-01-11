import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AuthService} from "../../../service/auth.service";
import {Router} from "@angular/router";
import {TokenService} from "../../../service/token.service";

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  rfLogin: FormGroup;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router,
              private tokenService: TokenService) {
    this.getFormLogin();
  }

  ngOnInit(): void {


  }

  getFormLogin() {
    this.rfLogin = this.formBuilder.group({
      username: [],
      password: [],
      rememberMe: [false]
    });
  }

  login() {
    this.authService.signIn(this.rfLogin.value).subscribe(data => {
      if (data.token != undefined) {
        console.log(data);
        console.log(data.user);
        if (this.rfLogin.value.rememberMe) {
          this.tokenService.rememberMe(data.token, data.account, data.roles, data.user);
        } else {
          this.tokenService.setAccountSession(data.account);
          this.tokenService.setTokenSession(data.token);
          this.tokenService.setUserSession(data.user);
          this.tokenService.setRoleSession(data.roles);
        }
        this.router.navigate(['/home']).then(() => {
          location.reload();
        });
      }
    });
  }
}
