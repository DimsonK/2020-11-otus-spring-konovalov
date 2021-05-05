import { RoleModel } from './role.model';

export class UserModel {
  id!: string;
  email!: string;
  username!: string;
  isActive?: boolean;
  firstName: string | undefined;
  lastName: string | undefined;
  roles?: [RoleModel];
}
