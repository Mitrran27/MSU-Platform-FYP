import {AfterViewInit, Component, HostListener, OnDestroy, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthService} from "../../app/services/auth.service";

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.sass']
})
export class SidebarComponent implements OnInit, AfterViewInit {

  url: string = ''

  constructor(private router: Router, private as: AuthService) {
  }

  ngOnInit() {
    console.log('sidebar')
    this.url = this.router.url.replace('/', "")
    console.log(this.url)
  }

  ngAfterViewInit() {
    this.url = this.router.url.replace('/', "")
    console.log(this.url)
    if (this.url == '') {
      this.url = 'dashboard'
    }
  }


  logOut() {
    this.as.logoutUser().subscribe(r => {
      console.log(r)
      localStorage.clear()
      window.location.reload()
    })

  }
}
