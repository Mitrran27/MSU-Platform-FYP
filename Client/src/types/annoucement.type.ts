import {UserType} from "./user.type";

export interface AnnouncementType {
  title: string;
  description: string
  userId?: string;
  createdBy?: UserType
}
