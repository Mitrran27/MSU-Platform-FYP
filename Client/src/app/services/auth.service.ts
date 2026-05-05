import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AuthType} from "../../types/auth.type";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  checkIsLoggedIn() {
    let token = localStorage.getItem('userToken')

    // return !!token
    return true
  }

  loginUser(data: AuthType) {
    return this.http.post(environment.API + '/auth/login', {email: data.email, password: data.password})
  }

  logoutUser() {
    console.log(Number(localStorage.getItem("userId") || ''))
    return this.http.put(environment.API + '/auth/logout/' + Number(localStorage.getItem("userId") || ''), {})
  }

  registerUser(data: AuthType) {
    return this.http.post(environment.API + '/auth/register', {
      email: data.email,
      password: data.password,
      fullName: data.fullName
    })
  }
}
