import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-about',
  templateUrl: './about.page.html',
  styleUrls: ['./about.page.scss'],
})



export class AboutPage implements OnInit {

  Nicks = [
    {nick: 'pioter', id: 1},
    {nick: 'wiktor', id: 2},
    {nick: 'pawel', id: 3}
  ];
  Emails = [
    {email: 'xyz@wp.pl', id: 1},
    {email: '123@wp.pl', id: 2},
    {email: 'wsad@wp.pl', id: 3}
  ];

  constructor() { }

  ngOnInit() {
  }

}
