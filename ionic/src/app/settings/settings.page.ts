import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';


@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
})

export class SettingsPage implements OnInit {


constructor(public alertCtrl: AlertController) { }

  async Delete(){
    const alert = await this.alertCtrl.create({
      header: 'WARNING!',
      message: 'Are you sure you want delete account?',
      buttons: [
        {
          text: 'Yes',
          handler: data => {
            window.location.href = '/login';
          }
        },
        {
          text: 'No',
        }
      ]
    });
    await alert.present();
  }
  async Change(){
    const alert = await this.alertCtrl.create({
      header: 'WARNING!',
      message: 'Are you sure you want change your password?',
      buttons: [
        {
          text: 'Yes',
          handler: data => {
            window.location.href = '/change';
          }
        },
        {
          text: 'No'
        }
      ]
    });
    await alert.present();
  }

  ngOnInit() {
  }

}
