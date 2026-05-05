import {UserType} from "./user.type";
import {ForumCommentType} from "./forum-comment.type";

export interface ForumType {
  id: number;
  title: string;
  topic: string;
  content: string;
  faq?: boolean;
  user?: UserType;
  forumComments?: ForumCommentType[];
  postedOn?: string;
  modifiedOn?: string;
  likes?: number;
  likedBy?: [];
  userLiked?: boolean
}
