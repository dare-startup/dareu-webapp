import { Component, OnInit } from '@angular/core';
import { Logger } from 'angular2-logger/core';
import { Router, Params, ActivatedRoute, NavigationCancel } from '@angular/router';
import { URLSearchParams } from '@angular/http';

//components
import { UsersGridComponent } from 'app/component/user/discover/discover-users/users-grid/users-grid.component';
import { PaginationComponent } from '../../../utilities/pagination/pagination.component';

//classes
import { DiscoverUserAccount } from 'app/model/response/discover-user-account';
import { Page } from 'app/model/response/page';
import { ServerUtilities } from 'app/service/server-utilities';

//providers
import { DareuUserBackendService } from 'app/service/user/dareu-user-backend.service';


@Component({
  selector: 'app-discover-users',
  templateUrl: './discover-users.component.html',
  styleUrls: ['./discover-users.css']
})
export class DiscoverUsersComponent implements OnInit {

  currentPageNumber: number = 1;
  //current items, if these items are updated, then the view are too
  currentItems:Page<DiscoverUserAccount>;
  firstPage:Array<DiscoverUserAccount> = [];
  secondPage:Array<DiscoverUserAccount> = [];

  constructor(private backendService:DareuUserBackendService,
              private log:Logger,
              private router:Router,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      let pageNumber = +params['pageNumber'] || 1;
      this.currentPageNumber = pageNumber;
      this.backendService.discoverUsers(this.currentPageNumber)
          .subscribe(users => {
            this.currentItems = users;
            //fill first and second page
            this.firstPage = users.items.splice(0, (users.items.length /2));
            this.secondPage = users.items.splice((users.items.length / 2) - 1, users.items.length);
          }, err => {
            this.log.info(err);
          });
    });
  }

}
