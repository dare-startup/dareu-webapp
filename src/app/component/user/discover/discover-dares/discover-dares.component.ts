import { Component, OnInit } from '@angular/core';
import { Logger } from 'angular2-logger/core';
import { ActivatedRoute } from '@angular/router';

import { DaresGridComponent } from 'app/component/user/discover/discover-dares/dares-grid/dares-grid.component';
import { DareuUserBackendService } from '../../../../service/user/dareu-user-backend.service';
import { Page } from '../../../../model/response/page';
import { DareDescription } from '../../../../model/response/dare-description';

@Component({
  selector: 'app-discover-dares',
  templateUrl: './discover-dares.component.html',
  styleUrls: ['./discover-dares.component.css']
})
export class DiscoverDaresComponent implements OnInit {

  _currentDares:Page<DareDescription> = {
    pageSize: 10,
    pagesAvailable: 1,
    pageNumber: 1,
    items: [
      {
        category: "category",
        completed: false,
        creationDate: '12/12/1990',
        description: 'description',
        estimatedDareTime: "2",
        id: 'id',
        name: 'dare name',
        challenger: {
            id: '',
            name: '',
            imageUrl: ''
        }
      },
      {
        category: "category",
        completed: false,
        creationDate: '12/12/1990',
        description: 'description',
        estimatedDareTime: "2",
        id: 'id',
        name: 'dare 2 name',
        challenger: {
            id: '',
            name: '',
            imageUrl: ''
        }
      },
      {
        category: "category",
        completed: false,
        creationDate: '12/12/1990',
        description: 'description',
        estimatedDareTime: "2",
        id: 'id',
        name: 'dare 3 name',
        challenger: {
            id: '',
            name: '',
            imageUrl: ''
        }
      }
    ]
  };

  firstPage:Array<DareDescription> = [];
  secondPage:Array<DareDescription> = [];

  constructor(private route:ActivatedRoute,
              private backendService:DareuUserBackendService,
              private log:Logger) { }


  get currentDares(){
    return this._currentDares;
  }
  ngOnInit() {
    //fill first and second page
    this.firstPage = this._currentDares.items.splice(0, (this._currentDares.items.length /2));
    this.secondPage = this._currentDares.items.splice((this._currentDares.items.length / 2) - 1, this._currentDares.items.length);
    /**this.route.queryParams.subscribe(params => {
      let pageNumber = +params['pageNumber'] || 1;
      if(!pageNumber){
        pageNumber = 1;
      }
      //get dares
      this.backendService.discoverDares(pageNumber)
              .subscribe(dares => {
                this.log.info(dares);
              }, err => {

              });
    });**/
  }


}
