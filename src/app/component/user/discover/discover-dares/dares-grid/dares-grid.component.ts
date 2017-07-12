import { Component, OnInit, Input } from '@angular/core';
import { Logger } from 'angular2-logger/core';

import { Page } from 'app/model/response/page';

import { DareuUserBackendService } from 'app/service/user/dareu-user-backend.service';

import { DareDescription } from 'app/model/response/dare-description';

@Component({
  selector: 'app-dares-grid',
  templateUrl: './dares-grid.component.html',
  styleUrls: ['../discover-dares.component.css']
})
export class DaresGridComponent implements OnInit {

  _firstPage: Array<DareDescription> = null;
  _secondPage: Array<DareDescription> = null;
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
  set firstPage(firstPage:Array<DareDescription>){
    this._firstPage = firstPage;
    this.log.info('First page size: ' + this._firstPage.length);
  }

  @Input()
  set secondPage(secondPage:Array<DareDescription>){
    this._secondPage = secondPage;
    this.log.info('Second page size: ' + this._secondPage.length);
  }

}
