import { Component, OnInit, Input } from '@angular/core';
import { DiscoverUserAccount } from '../../../../../model/response/discover-user-account';
import { Router } from '@angular/router';
import { Logger } from 'angular2-logger/core';

@Component({
  selector: 'app-user-card',
  templateUrl: './user-card.component.html',
  styleUrls: ['../discover-users.css']
})
export class UserCardComponent implements OnInit {

  _currentUser:DiscoverUserAccount = null;

  constructor(private router:Router,
              private log:Logger) { }

  ngOnInit() {
  }

  @Input()
  set currentUser(user:DiscoverUserAccount){
    this._currentUser = user;
  }

  get currentUser(){
    return this._currentUser;
  }


  /**
  * Navigate to a user profile
  **/
  viewProfile(){
    let userId = this._currentUser.id;
    let url = '/user/profile/' + userId;
    this.router.navigate([url]);
  }
}
