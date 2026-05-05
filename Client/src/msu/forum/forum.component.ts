import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Modal} from "bootstrap";
import {ForumType} from "../../types/forum.type";
import {ForumService} from "../services/forum.service";
import {ForumRequestType} from "../../types/forum-request.type";
import {UserService} from "../services/user.service";
import {UserType} from "../../types/user.type";

@Component({
  selector: 'app-forum',
  templateUrl: './forum.component.html',
  styleUrls: ['./forum.component.sass']
})
export class ForumComponent implements OnInit, AfterViewInit {
  showAddForum: boolean = true;

  forumModal!: Modal
  forum: ForumRequestType = {
    userId: 0, title: "", topic: "", content: ""
  }
  forums: ForumType[] = []

  users: UserType[] = []

  constructor(private fs: ForumService, private us: UserService) {
  }

  ngOnInit() {
    this.getAllPosts()
  }

  ngAfterViewInit() {
    this.forumModal = new Modal(document.getElementById('addModal') as HTMLElement);
  }

  onAddForum() {
    this.forumModal.show()
  }

  addForum() {
    this.forum.userId = Number(localStorage.getItem("userId"))
    this.fs.addNewPost(this.forum).subscribe(r => {
      console.log(r)
      this.forumModal.hide()
      this.getAllPosts()
    })
  }

  getAllPosts() {
    this.fs.getAllPosts().subscribe((r: any) => {
      console.log(r)
      this.forums = (r.data as ForumType[]).filter(f => f.user?.id === Number(localStorage.getItem("userId")))
    })
  }

  getAllUsers() {
    this.us.getAllUsers().subscribe((r: any) => {
      this.users = r.data
    })
  }

  getInput(event: Event) {
    return (event.target as HTMLInputElement).value
  }
}
