import {Component, OnInit} from '@angular/core';
import {ChatType} from "../../types/chat.type";
import {ChatService} from "../services/chat.service";
import {UserService} from "../services/user.service";
import {UserType} from "../../types/user.type";

@Component({
  selector: 'app-instant-chat',
  templateUrl: './instant-chat.component.html',
  styleUrls: ['./instant-chat.component.sass']
})
export class InstantChatComponent implements OnInit {


  chat: ChatType = {
    content: "", id: 0, receiver: "", sender: localStorage.getItem("userId") || '', timestamp: ""
  }
  selectedReceiver: string = ''

  users: UserType[] = []

  selectedChats: ChatType[] = []

  getChatInterval: any

  constructor(private cs: ChatService, private us: UserService) {

  }

  ngOnInit() {
    this.getAllUsers()
    setInterval(() => {
      this.getAllUsers()
    }, 1500)

  }

  getAllUsers() {
    this.us.getAllUsers().subscribe((r: any) => {
      // console.log(r)
      this.users = (r.data as UserType[]).filter(u => u.id != Number(this.chat.sender))
    })
  }

  sendMessage() {
    this.chat.receiver = this.selectedReceiver

    if (this.chat.sender != '' && this.chat.content != '') {
      this.cs.addNewMessage(this.chat).subscribe(r => {
        console.log(r)
        this.chat.content = ''
      })
    }
  }

  getChatByUser() {
    this.cs.getChat(this.chat.sender, this.selectedReceiver).subscribe((r: any) => {
      // console.log(r)
      this.selectedChats = r
    })
  }

  onFacultySelected(id: number) {
    clearInterval(this.getChatInterval)

    this.selectedReceiver = id.toString()
    this.getChatByUser()
    this.getChatInterval = setInterval(() => {
      this.getChatByUser()
    }, 1000)

  }

  protected readonly localStorage = localStorage;
}
