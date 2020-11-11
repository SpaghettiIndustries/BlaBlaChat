import { Component, Input, OnInit } from '@angular/core';
import { Message } from '../model/message';

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.scss'],
})
export class ConversationComponent implements OnInit {

  @Input() content: string;
  /*message: Message;*/

  constructor() { }

  ngOnInit() {}

}
