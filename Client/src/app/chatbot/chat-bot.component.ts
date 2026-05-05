import {AfterViewInit, Component, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chat-bot.component.html',
  styleUrls: ['./chatbot.component.sass', 'style.css'],
  encapsulation: ViewEncapsulation.None
})
export class ChatBotComponent implements OnInit, AfterViewInit {

  chatContainer: Element | null = null
  chatBotToggle: Element | null = null
  closeBtn: Element | null = null
  chatBox: Element | null = null
  chatInput: Element | null = null
  sendChatBtn: Element | null = null

  inputInitHeight: number = 0
  private userMessage: string = '';

  constructor() {
  }


  ngOnInit() {

  }

  ngAfterViewInit() {
    this.chatContainer = document.querySelector(".chat-container");
    this.chatBotToggle = document.querySelector(".chatbot-toggler");
    this.closeBtn = document.querySelector(".close-btn");
    this.chatBox = document.querySelector(".chatbox");
    this.chatInput = document.querySelector(".chat-input textarea");
    this.sendChatBtn = document.querySelector(".chat-input span");

    console.log(this.chatBotToggle)

    this.inputInitHeight = this.chatInput?.scrollHeight || 0;


    (this.chatInput as HTMLTextAreaElement).addEventListener("input", () => {
      // Adjust the height of the input textarea based on its content
      (this.chatInput as HTMLTextAreaElement).style.height = `${this.inputInitHeight}px`;
      (this.chatInput as HTMLTextAreaElement).style.height = `${(this.chatInput as HTMLTextAreaElement).scrollHeight}px`;
    });

    (this.chatInput as HTMLTextAreaElement).addEventListener("keydown", (e) => {
      // If Enter key is pressed without Shift key and the window
      // width is greater than 800px, handle the chat
      if (e.key === "Enter" && !e.shiftKey && window.innerWidth > 800) {
        e.preventDefault();
        this.handleChat();
      }
    });

    (this.sendChatBtn as HTMLElement).addEventListener("click", this.handleChat);

    (this.closeBtn as HTMLElement).addEventListener("click", () => this.chatContainer?.classList.remove("show-chatbot"));

    (this.chatBotToggle as HTMLElement).addEventListener("click", () => {
      this.chatContainer?.classList.toggle("show-chatbot")
      console.log('click')
    });

  }

  createChatLi = (message: string, className: string) => {
    // Create a chat <li> element with passed message and className
    const chatLi = document.createElement("li");
    chatLi.classList.add("chat", `${className}`);
    chatLi.innerHTML = className === "outgoing" ? `<p></p>` : `<span class="fa-solid fa-robot"></span><p></p>`;

    (chatLi.getElementsByTagName("p")[0] as HTMLParagraphElement).textContent = message;
    return chatLi;
  }

  generateResponse = (chatElement: Element) => {
    console.log(chatElement)
    const messageElement: HTMLParagraphElement = chatElement.querySelector("p") as HTMLParagraphElement;
    const API_URL = "http://localhost:8000/chatbot/" + this.userMessage;

    // Send POST request to API, get response and set the reponse as paragraph text
    fetch(API_URL).then(res => res.json()).then(data => {
      messageElement.textContent = data.response.trim();
    }).catch(() => {
      messageElement.classList.add("error");
      messageElement.textContent = "Oops! Something went wrong. Please try again.";
    }).finally(() => this.chatBox?.scrollTo(0, this.chatBox.scrollHeight));
  }


  handleChat = () => {
    this.userMessage = (this.chatInput as HTMLTextAreaElement)?.value.trim(); // Get user entered message and remove extra whitespace
    if (!this.userMessage) return;

    // Clear the input textarea and set its height to default
    (this.chatInput as HTMLTextAreaElement).value = "";
    (this.chatInput as HTMLTextAreaElement).style.height = `${this.inputInitHeight}px`;

    // Append the user's message to the chatbox
    (this.chatBox as HTMLElement).appendChild(this.createChatLi(this.userMessage, "outgoing"));
    (this.chatBox as HTMLElement).scrollTo(0, (this.chatBox as HTMLElement).scrollHeight);

    setTimeout(() => {
      // Display "Thinking..." message while waiting for the response
      const incomingChatLi = this.createChatLi("Thinking...", "incoming");
      (this.chatBox as HTMLElement).appendChild(incomingChatLi);
      (this.chatBox as HTMLElement).scrollTo(0, (this.chatBox as HTMLElement).scrollHeight);
      this.generateResponse(incomingChatLi);
    }, 600);
  }

}
