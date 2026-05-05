import {UserType} from "./user.type";

export interface ResourceType {
  title: string;
  description: string;
  userId: number;
  filePath: string;
  fileName: string;
  fileType: string;
  fileSize: string;
  uploadedBy?: UserType;
}
