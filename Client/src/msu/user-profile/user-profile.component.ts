import {AfterViewInit, Component, OnInit} from '@angular/core';
import {UserService} from "../services/user.service";
import {UserType} from "../../types/user.type";
import {environment} from "../../environments/environment.development";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.sass']
})
export class UserProfileComponent implements AfterViewInit, OnInit {

  imageInput!: HTMLInputElement

  user: UserType = {
    department: "", email: "", fullName: "", id: 0, password: "", phoneNumber: "", profileImage: ""
  }

  edit: boolean = false


  constructor(private us: UserService) {
  }

  ngOnInit() {
    this.getProfileInfo()

  }

  ngAfterViewInit() {
    this.imageInput = document.getElementById('userImage') as HTMLInputElement
  }

  openFIleSelector() {
    this.imageInput.click()
  }

  getProfileInfo() {
    this.us.getProfileInfo().subscribe((r: any) => {
      console.log(r)
      this.user = r.data
    })
  }

  onEditProfile() {
    this.edit = true
  }

  updateInfo() {
    this.us.updateUserInfo(this.user).subscribe(r => {
      console.log(r)
      this.edit = false
      this.getProfileInfo()
    })
  }

  onFileSelected(event: Event) {
    let files = (event.target as HTMLInputElement).files
    console.log(files?.item(0))

    let data = new FormData();
    data.append("file", files?.item(0) as any);
    this.us.uploadImage(data).subscribe((r: any) => {
      console.log(r)
      this.user.profileImage =  r.url
      if (environment.API) {
        this.user.profileImage = 'http://localhost:5001/api' + this.user.profileImage
      }
    })
  }
}
