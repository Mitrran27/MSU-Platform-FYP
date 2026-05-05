import {AfterViewInit, Component, OnInit} from '@angular/core';
import {Modal} from "bootstrap";
import {AnnouncementService} from "../services/announcement.service";
import {AnnouncementType} from "../../types/annoucement.type";
import {UserType} from "../../types/user.type";
import {UserService} from "../services/user.service";


@Component({
  selector: 'app-announcements',
  templateUrl: './announcements.component.html',
  styleUrls: ['./announcements.component.sass']
})
export class AnnouncementsComponent implements AfterViewInit, OnInit {
  annModal!: Modal
  announcement: AnnouncementType = {
    title: '', description: ''
  };
  announcements: AnnouncementType[] = []

  users:UserType[] = []

  constructor(private as: AnnouncementService,private us:UserService) {
  }

  ngOnInit() {
    this.getAnnouncements()
  }

  ngAfterViewInit() {
    this.annModal = new Modal(document.getElementById('addModal') as HTMLElement);
  }

  onAddAnnouncement() {
    this.annModal.show()
  }

  getAnnouncements() {
    this.as.getAllAnnouncements().subscribe((r: any) => {
      this.announcements = r.data


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
  addAnnouncement() {
    this.as.addNewAnnouncement(this.announcement).subscribe((r: any) => {
      this.announcements = r.data
      this.annModal.hide()
      this.getAnnouncements()
    })
  }
}
