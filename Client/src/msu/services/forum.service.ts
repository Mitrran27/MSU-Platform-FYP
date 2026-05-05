import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment.development";
import {ForumRequestType} from "../../types/forum-request.type";
import {ForumType} from "../../types/forum.type";

@Injectable({
  providedIn: 'root'
})
export class ForumService {
  API = environment.API

  constructor(private http: HttpClient) {
  }

  addNewPost(post: ForumRequestType) {
    return this.http.post(this.API + '/forum', post)
  }

  getAllPosts() {
    return this.http.get(this.API + '/forum/' + Number(localStorage.getItem("userId")))
  }

  getAllFaq() {
    return this.http.get(this.API + '/forum/faq/' + Number(localStorage.getItem("userId")))
  }


  addNewComment(comment: { comment: string; userId: number; forumId: number }) {
    return this.http.post(this.API + '/forum/comment', comment)
  }

  addOrUpdateLike(like: { isLiked: number; userId: number; forumId: number }) {
    console.log(like.isLiked)
    return this.http.post(this.API + '/forum/like', like)
  }

  updateForumFaq(forum: ForumType) {
    return this.http.put(this.API + '/forum', forum)
  }
}
