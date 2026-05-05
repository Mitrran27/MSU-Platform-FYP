import {UserType} from "./user.type";
import {ForumType} from "./forum.type";

export interface ForumCommentType {
  id: number;
  comment: string;
  user: UserType;
  forumPost: ForumType;
  commentedOn: string;
}
