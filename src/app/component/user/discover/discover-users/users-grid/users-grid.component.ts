import { Component, OnInit, Input } from '@angular/core';
import { Logger } from 'angular2-logger/core';

import { DiscoverUserAccount } from 'app/model/response/discover-user-account';
import { Page } from 'app/model/response/page';

import { DareuUserBackendService } from 'app/service/user/dareu-user-backend.service';

@Component({
  selector: 'app-users-grid',
  templateUrl: './users-grid.component.html',
  styleUrls: ['../discover-users.css']
})
export class UsersGridComponent implements OnInit {

  _firstPage: Array<DiscoverUserAccount> = null;
  _secondPage: Array<DiscoverUserAccount> = null;
  constructor(private log:Logger) { }

  ngOnInit() {
  }

  get firstPage() {
    return this._firstPage;
  }

  get secondPage() {
    return this._secondPage;
  }

  @Input()
  set firstPage(firstPage:Array<DiscoverUserAccount>){
    this._firstPage = firstPage;
    this.log.info('First page size: ' + this._firstPage.length);
  }

  @Input()
  set secondPage(secondPage:Array<DiscoverUserAccount>){
    this._secondPage = secondPage;
    this.log.info('Second page size: ' + this._secondPage.length);
  }

}
