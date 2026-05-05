import {Component, OnInit} from '@angular/core';
import {AuthType} from "../../types/auth.type";
import {AuthService} from "../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.sass']
})
export class AuthenticationComponent implements OnInit {

  isLogin: boolean = true;
  authDetails: AuthType = {
    email: "", fullName: "", password: ""
  }

  message: string = ''

  constructor(private as: AuthService, private router: Router) {
  }

  ngOnInit() {
  }

  onFormSubmit() {
    if (!this.isLogin) {
      this.as.registerUser(this.authDetails).subscribe(r => {
        console.log(r)
      })
    } else {
      this.as.loginUser(this.authDetails).subscribe((r: any) => {
        console.log(r)
        this.message = r.msg
        if (r.status == "success") {
          localStorage.setItem("loggedIn", "true")
          localStorage.setItem("userId", r.userId)
          this.router.navigate(['/dashboard'])
        }
      })
    }
  }

}
