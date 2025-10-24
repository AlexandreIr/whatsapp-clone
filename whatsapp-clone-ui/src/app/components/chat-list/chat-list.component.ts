import {Component, input, InputSignal, output} from '@angular/core';
import {ChatService} from '../../services/services/chat.service';
import {ChatResponse} from '../../services/models/chat-response';
import {DatePipe, registerLocaleData} from '@angular/common';
import {UserService} from '../../services/services/user.service';
import {UserResponse} from '../../services/models/user-response';
import {KeycloakService} from '../../utils/keycloak/keycloak.service';


@Component({
  selector: 'app-chat-list',
  templateUrl: './chat-list.component.html',
  imports: [
    DatePipe
  ],
  styleUrl: './chat-list.component.scss'
})
export class ChatListComponent {
  chats: InputSignal<ChatResponse[]> = input<ChatResponse[]>([]);
  searchNewContact = false;
  contacts: Array<UserResponse> = [];
  chatSelected = output<ChatResponse>();
  unread: boolean = false;
  favorite: boolean = false;

  constructor(
    private chatService: ChatService,
    private userService: UserService,
    private keycloakService: KeycloakService,
  ) {
  }

  searchContact() {
    this.userService.getAllUsers()
      .subscribe({
        next: (users) => {
          this.contacts = users;
          this.searchNewContact = true;
        }
      });
  }

  selectContact(contact: UserResponse) {
    const chat = this.chats().find(c => c.senderId === contact.id || c.receiverId === contact.id);
    if(!chat){
      this.chatService.createChat({
        'sender-id': this.keycloakService.userId as string,
        'receiver-id': contact.id as string
      }).subscribe({
        next: (res) => {
          const chat: ChatResponse = {
            id: res.response,
            name: contact.firstName + ' ' + contact.lastName,
            recipientOnline: contact.online,
            lastMessageTime: contact.lastSeen,
            senderId: this.keycloakService.userId,
            receiverId: contact.id
          };
          this.chats().unshift(chat);
          this.searchNewContact = false;
          this.chatSelected.emit(chat);
        }
      });
    } else {
      this.chatClicked(chat);
      this.searchNewContact = false;
    }
  }

  chatClicked(chat: ChatResponse) {
    this.chatSelected.emit(chat);
  }

  wrapMessage(lastMessage: string | undefined): string {
    if (lastMessage && lastMessage.length <= 20) {
      return lastMessage;
    }
    return lastMessage?.substring(0, 17) + '...';
  }


  favorites() {

  }

  findChats() {

  }

  formatLastMessageTime(messageTime: string | null | undefined) {
    if(!messageTime){
      return '';
    }

    const now = new Date();
    const messageDate = new Date(messageTime);
    const diffInMs = now.getTime() - messageDate.getTime();
    const diffInHours = diffInMs/ (1000 * 60 * 60);
    if(diffInHours < 24){
      return new DatePipe('en-US').transform(messageDate, 'HH:mm') || '';
    } else if(diffInHours < 730) {
      return new DatePipe('en-US').transform(messageDate, 'dd/MM HH:mm') || '';
    } else {
      return new DatePipe('en-US').transform(messageDate, 'dd/MM/yyyy HH:mm') || '';
    }
  }
}
