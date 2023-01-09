import {Injectable} from '@angular/core';
import {User} from "../model/user/User";

const TOKEN_KEY = 'Token_Key';
const USER_KEY = 'User_Key';
const ROLE_KEY = 'Role_Key';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  private roles: Array<string> = [];

  constructor() {
  }

  public setTokenLocal(token: string) {
    window.localStorage.removeItem(TOKEN_KEY);
    window.localStorage.setItem(TOKEN_KEY, token);
  }

  public setTokenSession(token: string) {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getTokenLocal(): string {
    if (localStorage.getItem(TOKEN_KEY) != null) {
      return localStorage.getItem(TOKEN_KEY);
    } else {
      return sessionStorage.getItem(TOKEN_KEY);
    }
  }

  public setUserLocal(user: User) {
    window.localStorage.removeItem(USER_KEY);
    window.localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public setUserSession(user: User) {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public getUser(): string {
    if (localStorage.getItem(USER_KEY) != null) {
      return localStorage.getItem(USER_KEY);
    } else {
      return sessionStorage.getItem(USER_KEY);
    }
  }

  public setRoleLocal(roles: string[]) {
    window.localStorage.removeItem(ROLE_KEY);
    window.localStorage.setItem(ROLE_KEY, JSON.stringify(roles));
  }

  public setRoleSession(roles: string[]) {
    window.sessionStorage.removeItem(ROLE_KEY);
    window.sessionStorage.setItem(ROLE_KEY, JSON.stringify(roles));
  }

  public getRole(): string[] {
    this.roles = [];
    if (localStorage.getItem(ROLE_KEY) != null) {
      JSON.parse(localStorage.getItem(ROLE_KEY)).forEach(role => {
        this.roles.push(role.authority)
      });
    } else {
      JSON.parse(sessionStorage.getItem(ROLE_KEY)).forEach(role => {
        this.roles.push(role.authority)
      });
    }
    return this.roles;
  }

  public logOut() {
    window.localStorage.clear();
    window.sessionStorage.clear();
  }

  public rememberMe(token: string, user: User, roles: string[]) {
    this.logOut();
    this.setUserLocal(user);
    this.setTokenLocal(token);
    this.setRoleLocal(roles);
  }

  public isLogged(): boolean {
    return !(window.sessionStorage.getItem(TOKEN_KEY) == null && window.localStorage.getItem(TOKEN_KEY) == null);
  }
}

