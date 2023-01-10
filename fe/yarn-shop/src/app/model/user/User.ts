import {UserRole} from "./UserRole";

export interface User {
  id?: number;
  name?: string;
  username?: string;
  password?: string;
  email?: string;
  userRoles?: UserRole[];
}
