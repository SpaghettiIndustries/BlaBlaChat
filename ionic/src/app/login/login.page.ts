import { Component, OnInit } from '@angular/core';
import { AlertController } from '@ionic/angular';
import {FormBuilder, FormGroup} from '@angular/forms';


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  ionicForm: FormGroup;
  username;

  constructor(public alertCtrl: AlertController,
              private formBuilder: FormBuilder) {
    this.ionicForm = this.formBuilder.group({
      username: '',
      password: ''
    });
  }

  ngOnInit() {
  }
  SubmitForm(){
    console.log(this.ionicForm.value);
  }

  async login(){
    const alert = await this.alertCtrl.create({
      message: 'Hello ' + this.ionicForm.get('username').value,
      buttons: [
        {
          text: 'Ok',
          handler: data => {
            window.location.href = '/tabs';
          }
        }
      ]
    });
    await alert.present();
  }
}
