import {User} from "../user/User";

export interface JwtResponse {
  token?: string;
  roles?: any;
  user?: User;
}
