export interface UserType {
  id: number;
  fullName: string;
  email: string;
  password: string;
  profileImage: string;
  phoneNumber: string;
  department: string;
  connected?: boolean
}
