import { Component, OnInit } from '@angular/core';
import { PopoverController } from '@ionic/angular';

@Component({
  selector: 'app-messages-popover',
  templateUrl: './messages-popover.component.html',
  styleUrls: ['./messages-popover.component.scss'],
})
export class MessagesPopoverComponent implements OnInit {

  constructor(public popoverController: PopoverController) { }
  delete(){
    window.open('https://www.zspwrzesnia.pl','_blank');
    this.popoverController.dismiss();
  }
  close(){
    this.popoverController.dismiss();
  }

  ngOnInit() {}

}
