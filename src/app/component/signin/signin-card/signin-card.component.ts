import { Component, OnInit } from '@angular/core';
import { Logger } from "angular2-logger/core";


@Component({
  selector: 'app-signin-card',
  templateUrl: './signin-card.component.html',
  styleUrls: ['../signin.css']
})
export class SigninCardComponent implements OnInit {

  //constructor
  constructor(private log:Logger) { }


  ngOnInit() {
  }

}
