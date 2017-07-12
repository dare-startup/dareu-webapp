import { Component, OnInit, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Logger } from 'angular2-logger/core';


@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.css']
})
export class PaginationComponent implements OnInit {

  _pagesAvailable:number = null;
  _pageNumber:number = null;
  _currentUrl:string = null;



  constructor(private router:Router,
              private log:Logger) { }

  ngOnInit() {
  }


  @Input()
  set pagesAvailable(pagesAvailable:number){
    this._pagesAvailable = pagesAvailable;
  }

  @Input()
  set pageNumber(pageNumber:number){
    this._pageNumber = pageNumber;
    this.router.events.subscribe((urlObject:any) => {
      this._currentUrl = urlObject.url + '?pageNumber=' + (++ this._pageNumber);
      this.log.info(this._currentUrl);
  });
}

  @Input()
  set currentUrl(currentUrl:string){
    this._currentUrl = currentUrl;
  }

  get pagesAvailable(){
    return this._pagesAvailable;
  }

  get pageNumber(){
    return this._pageNumber;
  }

  back(){
    this.router.navigate([this.currentUrl + 'pageNumber=' + (this._pageNumber + 1)]);
  }

  forward(){
    this.router.navigate([this.currentUrl + 'pageNumber=' + (this._pageNumber - 1)]);
  }

  canGoBack(){
    return this._pageNumber == 1;
  }

  canGoForward(){
    let canGoForward = this._pageNumber < this._pagesAvailable;
    this.log.info('Can go forward: ' + canGoForward);
    return canGoForward;
  }
}
