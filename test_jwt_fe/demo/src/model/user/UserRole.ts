import {User} from "./User";
import {Role} from "./Role";

export interface UserRole {
  id?: number;
  user?: User;
  role?:Role;
}
