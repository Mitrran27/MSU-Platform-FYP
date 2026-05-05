import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment.development";
import {ChatType} from "../../types/chat.type";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  API = environment.API + '/chat'


  constructor(private http: HttpClient) {
  }

  addNewMessage(chat: ChatType) {
    return this.http.post(this.API, chat)
  }

  getChat(sender: string, receiver: string) {
    return this.http.get(`${this.API}/${sender}/${receiver}`)
  }
}
