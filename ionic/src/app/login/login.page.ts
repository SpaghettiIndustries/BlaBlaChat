import {Component, Input, OnInit} from '@angular/core';
import { AlertController } from '@ionic/angular';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {Observable} from "rxjs";


@Component({
  selector: 'app-login',
  templateUrl: './login.page.html',
  styleUrls: ['./login.page.scss'],
})
export class LoginPage implements OnInit {

  @Input()
  result$: Observable<any>;

  loginForm: FormGroup;

  constructor(public alertCtrl: AlertController, private formBuilder: FormBuilder) {
    this.loginForm = this.formBuilder.group({
      nick: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(25)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
    });
  }

  ngOnInit() {
  }

  submitLoginForm() {
    console.log(this.loginForm.value);
  }

  async login() {
    const alert = await this.alertCtrl.create({
      message: 'Hello ' + this.loginForm.get('username').value,
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
