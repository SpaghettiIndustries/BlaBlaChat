import { Component, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { MessagesPopoverComponent} from '../messages-popover/messages-popover.component';
import { Conversation } from '../model/conversation';
import { ConversationService } from '../service/conversation.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.page.html',
  styleUrls: ['./messages.page.scss'],
})
export class MessagesPage implements OnInit {

  conversations: Conversation[];
  term: string;

  constructor(private conversationService: ConversationService, private popoverController: PopoverController) {}

  ngOnInit() {
    this.getMessages();
  }


  getMessages() {
    this.conversationService.getAll().subscribe(
      data => { this.conversations = data; console.log(data); },
      error => console.log(error)
    );
  }

  async refresh(event) {
    this.getMessages();
  }

  async presentPopover(event) {
    const popover = await this.popoverController.create({
      component: MessagesPopoverComponent,
      cssClass: 'message-popover',
      event,
      translucent: true
    });
    return await popover.present();
  }
}
