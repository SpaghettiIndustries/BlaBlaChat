import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { AuthenticationService } from '../service/authentication.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
})
export class SettingsPage implements OnInit {

  constructor(private alertController: AlertController, private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  async change() {
    const alert = await this.alertController.create({
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

  async delete() {
    const alert = await this.alertController.create({
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
          text: 'No'
        }
      ]
    });

    await alert.present();
  }

  async logout() {
    const logoutAlert = await this.alertController.create({
      message: 'Czy na pewno chcesz się wylogować?',
      buttons: [
        {
          text: 'Tak',
          handler: data => {
            this.authenticationService.logout();
          }
        },
        {
          text: 'Nie'
        }
      ]
    });

    await logoutAlert.present();
  }
}
