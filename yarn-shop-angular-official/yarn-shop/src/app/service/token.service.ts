import { Injectable } from '@angular/core';
import {Customer} from "../model/customer/customer";
const TOKEN_KEY = 'Token_key';
const ROLE_KEY = 'Role_key';
const USER_KEY = 'User_key';
const ACCOUNT_KEY = 'Account_key';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  roles = [];

  constructor() {
  }

  public setTokenLocal(token: string) {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public setTokenSession(token: string) {
    sessionStorage.removeItem(TOKEN_KEY);
    sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    if (localStorage.getItem(TOKEN_KEY) !== null) {
      return localStorage.getItem(TOKEN_KEY);
    } else {
      return sessionStorage.getItem(TOKEN_KEY);
    }
  }

  public setUserLocal(customer: Customer) {
    localStorage.removeItem(USER_KEY);
    localStorage.setItem(USER_KEY, JSON.stringify(customer));
  }

  public setUserSession(customer: Customer) {
    sessionStorage.removeItem(USER_KEY);
    sessionStorage.setItem(USER_KEY, JSON.stringify(customer));
  }

  public getUser(): string {
    if (localStorage.getItem(USER_KEY) !== null) {
      return localStorage.getItem(USER_KEY);
    } else {
      return sessionStorage.getItem(USER_KEY);
    }
  }

  public setAccountLocal(account: Account) {
    localStorage.removeItem(ACCOUNT_KEY);
    localStorage.setItem(ACCOUNT_KEY, JSON.stringify(account));
  }

  public setAccountSession(account: Account) {
    sessionStorage.removeItem(ACCOUNT_KEY);
    sessionStorage.setItem(ACCOUNT_KEY, JSON.stringify(account));
  }

  public getAccount(): string {
    if (localStorage.getItem(ACCOUNT_KEY) !== null) {
      return localStorage.getItem(ACCOUNT_KEY) as string;
    } else {
      return sessionStorage.getItem(ACCOUNT_KEY) as string;
    }
  }

  public setRoleLocal(roles: String[]) {
    localStorage.removeItem(ROLE_KEY);
    localStorage.setItem(ROLE_KEY, JSON.stringify(roles));
  }

  public setRoleSession(roles: String[]) {
    sessionStorage.removeItem(ROLE_KEY);
    sessionStorage.setItem(ROLE_KEY, JSON.stringify(roles));
  }

  public getRole(): string[] {
    if (localStorage.getItem(ROLE_KEY) !== null) {
      JSON.parse(localStorage.getItem(ROLE_KEY)).forEach(role => {
        this.roles.push(role.authority);
      });
    } else {
      JSON.parse(sessionStorage.getItem(ROLE_KEY)).forEach(role => {
        this.roles.push(role.authority);
      });
    }

    return this.roles;
  }

  public logOut() {
    window.localStorage.clear();
    window.sessionStorage.clear();
  }

  public rememberMe(token: string, account: Account, roles: string[], customer: Customer) {
    this.logOut();
    this.setAccountLocal(account);
    this.setRoleLocal(roles);
    this.setUserLocal(customer);
    this.setTokenLocal(token);
  }

  public isLogged(): boolean {
    return !(window.sessionStorage.getItem(TOKEN_KEY) == null && window.localStorage.getItem(TOKEN_KEY) == null);
  }
}
