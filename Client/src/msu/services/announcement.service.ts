import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment.development";
import {HttpClient} from "@angular/common/http";
import {AnnouncementType} from "../../types/annoucement.type";

@Injectable({
  providedIn: 'root'
})
export class AnnouncementService {

  API = environment.API

  constructor(private http: HttpClient) {
  }

  addNewAnnouncement(announcement: AnnouncementType) {
    announcement.userId = localStorage.getItem("userId") || ''
    return this.http.post(this.API + '/announcement', announcement)
  }

  getAllAnnouncements() {
    return this.http.get(this.API + '/announcement')
  }
}
