import {Injectable} from '@angular/core';
import {environment} from "../environments/environment";
import {HttpClient} from "@angular/common/http";
import {LoginForm} from "../model/security/LoginForm";
import {Observable} from "rxjs";
import {JwtResponse} from "../model/security/JwtResponse";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private API_LOGIN = environment.API_LOCAL + "auth/login"

  constructor(private _httpClient: HttpClient) {
  }

  loginForm(loginForm: LoginForm): Observable<JwtResponse> {
    return this._httpClient.post(this.API_LOGIN, loginForm);
  }
}
