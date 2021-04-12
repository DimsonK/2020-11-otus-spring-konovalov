export class User {
  id!: number;
  username!: string;
  password!: string;
  firstName: string | undefined;
  lastName: string | undefined;
  authData?: string;
  roles?: string[];
}
