import {Component, OnInit} from '@angular/core';
import {ForumService} from "../services/forum.service";
import {ForumType} from "../../types/forum.type";
import {UserType} from "../../types/user.type";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-faq',
  templateUrl: './faq.component.html',
  styleUrls: ['./faq.component.sass']
})
export class FaqComponent implements OnInit {

  faqs: ForumType[] = []
  users: UserType[] = []

  constructor(private fs: ForumService, private us: UserService) {
  }

  ngOnInit() {
    this.getAllFaqs()
  }

  getAllFaqs() {
    this.fs.getAllFaq().subscribe((r: any) => {
      this.faqs = r.data
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
}
