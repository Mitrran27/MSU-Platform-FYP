import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.sass']
})
export class AppComponent implements OnInit {
  title = 'msu-learning';

  constructor(private router: Router) {
  }

  ngOnInit() {
    console.log()
    if (localStorage.getItem("loggedIn") === "true"){

    }else{
      this.router.navigate(['authentication'])
    }

  }

}
