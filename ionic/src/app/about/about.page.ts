import { Component, OnInit } from '@angular/core';
import { AuthenticationService} from '../service/authentication.service';
import { User } from '../model/user';
@Component({
  selector: 'app-about',
  templateUrl: './about.page.html',
  styleUrls: ['./about.page.scss'],
})



export class AboutPage implements OnInit {

  currentUser: User;

  constructor(private authenticationService: AuthenticationService) {
    this.authenticationService.currentUser.subscribe(user => this.currentUser = user);
  }

  ngOnInit() {
  }

}
