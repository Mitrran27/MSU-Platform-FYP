import {Component, OnInit} from '@angular/core';
import {ForumType} from "../../types/forum.type";
import {UserType} from "../../types/user.type";
import {ForumService} from "../services/forum.service";
import {UserService} from "../services/user.service";
import {AnnouncementService} from "../services/announcement.service";
import {AnnouncementType} from "../../types/annoucement.type";
import {ResourceType} from "../../types/resource.type";
import {ResourceService} from "../services/resource.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.sass']
})
export class DashboardComponent implements OnInit {

  forums: ForumType[] = []
  users: UserType[] = []
  announcements: AnnouncementType[] = []
  resources: ResourceType[] = []

  activeUsers: number = 0
  registerUsers: number = 0

  constructor(private fs: ForumService, private us: UserService,
              private as: AnnouncementService, private rs: ResourceService) {
  }

  ngOnInit() {
    this.getAllForums()
    this.getAllUsers()
    this.getAllAnnouncements()
    this.getAllResources()
  }


  getAllForums() {
    this.fs.getAllPosts().subscribe((r: any) => {
      this.forums = r.data
    })
  }

  getAllUsers() {
    this.us.getAllUsers().subscribe((r: any) => {
      this.users = r.data
      this.registerUsers = this.users.length

      this.activeUsers = this.users.filter(u => u.connected).length
    })
  }

  getAllAnnouncements() {
    this.as.getAllAnnouncements().subscribe((r: any) => {
      this.announcements = r.data
    })
  }

  getAllResources() {
    this.rs.getAllResources().subscribe((r: any) => {
      this.resources = r.data
    })
  }

  getInput(event: Event) {
    return (event.target as HTMLInputElement).value
  }

  getUser(userId: any) {
    console.log(userId)
    console.log(this.users.filter(u => u.id === Number(userId)))
    return this.users.filter(u => u.id === Number(userId))[0]
  }
}
