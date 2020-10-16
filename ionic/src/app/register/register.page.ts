import {Component, Input, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MustMatchValidator} from "../validator/must-match.validator";

@Component({
  selector: 'app-register',
  templateUrl: './register.page.html',
  styleUrls: ['./register.page.scss'],
})
export class RegisterPage implements OnInit {

  @Input()
  result$: Observable<any>;

  /*nick: string = '';
  password: string = '';
  email: string = '';*/

  registerForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.registerForm = this.formBuilder.group({
      nick: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(25)]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      passwordConfirmation: ['', Validators.required],
      email: ['', Validators.email]
    }, {
      validator: MustMatchValidator('password', 'passwordConfirmation')
    });
  }

  ngOnInit() {
  }

  registerFormSubmit() {

  }
}
