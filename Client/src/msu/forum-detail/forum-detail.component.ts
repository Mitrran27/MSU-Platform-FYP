import {Component, OnInit} from '@angular/core';
import {ForumType} from "../../types/forum.type";
import {ForumService} from "../services/forum.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-forum-detail',
  templateUrl: './forum-detail.component.html',
  styleUrls: ['./forum-detail.component.sass']
})
export class ForumDetailComponent implements OnInit {

  forum: ForumType = {
    content: "", id: 0, title: "", topic: ""
  }

  forumId!: number
  showCommentBox: boolean = false;

  comment: {
    comment: string;
    userId: number;
    forumId: number;
  } = {
    comment: '',
    userId: 0,
    forumId: 0
  }

  like: {
    isLiked: number;
    userId: number;
    forumId: number;
  } = {
    isLiked: 0,
    userId: 0,
    forumId: 0
  }

  constructor(private fs: ForumService, route: ActivatedRoute) {
    route.params.subscribe((r: any) => {
      this.forumId = Number(r.id)
      console.log(r)
    })
  }

  ngOnInit() {
    this.getForumDetails()
  }

  getForumDetails() {
    this.fs.getAllPosts().subscribe((r: any) => {
      this.forum = (r.data as ForumType[]).filter(f => f.id === this.forumId)[0]
    })
  }

  addNewComment() {
    this.comment.forumId = this.forumId
    this.comment.userId = Number(localStorage.getItem("userId"))

    this.fs.addNewComment(this.comment).subscribe(r => {
      console.log(r)
      this.comment.comment = ''
      this.getForumDetails()
    })
  }

  likeOrDislike(l: boolean) {
    this.like.isLiked = l ? 0 : 1;
    this.like.forumId = this.forumId
    this.like.userId = Number(localStorage.getItem("userId"))

    this.fs.addOrUpdateLike(this.like).subscribe(r => {
      console.log(r)
      this.getForumDetails()
    })
  }

  toggleCommentSection() {
    this.showCommentBox = !this.showCommentBox
  }

  updateForumFaq(id: number) {
    console.log(id)
    this.fs.updateForumFaq(this.forum).subscribe(r => {
      this.getForumDetails()
    })
  }
}
