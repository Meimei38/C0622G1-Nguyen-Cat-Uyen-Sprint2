import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {SignInForm} from "../dto/sign-in-form";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) {
  }

  signIn(value: SignInForm): Observable<any> {
    return this.httpClient.post<any>('http://localhost:8080/api/v1/security/login', value);
  }
}
