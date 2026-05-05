import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../app/services/auth.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  constructor(private router: Router, private as: AuthService) {
  }

  ngOnInit() {

    !this.as.checkIsLoggedIn() ?
      this.router.navigate(['/authentication']).then(r => {
        console.log(r)
      }) : null

    if (this.router.url == '/') {
      this.router.navigate(['/dashboard']).then(r => {
        console.log(r)
      })
    }
  }
}
