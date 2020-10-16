import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { MessagesPageRoutingModule } from './messages-routing.module';

import { MessagesPage } from './messages.page';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    IonicModule,
    Ng2SearchPipeModule,
    MessagesPageRoutingModule
  ],
  entryComponents: [],
  declarations: [MessagesPage]
})
export class MessagesPageModule {}
