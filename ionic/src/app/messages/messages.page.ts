import { Component, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';
import { MessagesPopoverComponent} from '../messages-popover/messages-popover.component';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.page.html',
  styleUrls: ['./messages.page.scss'],
})
export class MessagesPage implements OnInit {

  term: string;

  constructor(public popoverController: PopoverController) {}

  ngOnInit() {
  }

  Message = [
    { nick: 'PUDZIAN',
      src: 'https://n-22-6.dcs.redcdn.pl/file/o2/redefine/cp/1s/1ssk46fv243bixm4i8ffh6c8pkqm6e9e.jpg',
      content: 'POLAND MOUNTAIN'
    },
    { nick: 'YODA',
      src: 'https://gamingsociety.pl/wp-content/uploads/2020/01/Baby-Yoda-figurka-3.jpg',
      content: 'Hello there'
    },
    { nick: 'HARDKOROWY KOKSU',
      src: 'https://gfx.radiozet.pl/var/radiozet/storage/images/rozrywka/plotki/hardkorowy-koksu-bedzie-ojcem.-kiedy-urodzi-sie-dziecko-roberta-burneiki/1382442-1-pol-PL/Robert-Burneika-zostanie-ojcem.-Hardkorowy-Koksu-zdradzil-termin-porodu_article.jpg',
      content: 'Co tam byczku?'
    }
  ];

  async refresh(event) {
    
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
