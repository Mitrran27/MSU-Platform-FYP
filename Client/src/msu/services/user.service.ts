import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment.development";
import {HttpClient} from "@angular/common/http";
import {UserType} from "../../types/user.type";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  API = environment.API

  constructor(private http: HttpClient) {
  }

  getProfileInfo() {
    let id = Number(localStorage.getItem('userId'))
    return this.http.get(this.API + '/user/' + id)
  }

  getAllUsers() {
    return this.http.get(this.API + '/user')
  }

  updateUserInfo(data:UserType){
    return this.http.post(this.API + '/user',data)
  }

  uploadImage(form:FormData){
    return this.http.post(this.API + '/uploadFile', form)
  }
}
