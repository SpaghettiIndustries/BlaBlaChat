import { Message } from './message';

export class Conversation {
  id: number;
  secondUserId: number;
  lastMessage: Message;

  createdAt: string;
  updatedAt: string;
}
