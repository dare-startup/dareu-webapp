import { Injectable } from '@angular/core';
import { Logger } from 'angular2-logger/core';
import { Http, Response, Headers, URLSearchParams } from '@angular/http';
import { Observable } from 'rxjs/Observable';

//classes
import { ServerUtilities } from 'app/service/server-utilities';
import { DiscoverUserAccount } from 'app/model/response/discover-user-account';
import { DareDescription } from '../../model/response/dare-description';
import { Page } from 'app/model/response/page';

//providers
import { AuthenticationService } from 'app/service/security/authentication.service';


@Injectable()
export class DareuUserBackendService {


  private discoverUsersPath = 'account/discoverUsers?pageNumber=';
  private discoverDaresPath = 'dare/discover?pageNumber=';
  private discoverUsersUrl = ServerUtilities.HOST + this.discoverUsersPath;
  private discoverDaresUrl = ServerUtilities.HOST + this.discoverDaresPath;

  constructor(private authenticationService:AuthenticationService,
              private httpClient:Http,
              private log:Logger) {  }

  /**
  * Fetch a page of discoverable users for this account
  **/
  discoverUsers(pageNumber:number): Observable<Page<DiscoverUserAccount>> {
    if(pageNumber <= 0) {
        //set value
        pageNumber = 1;
    }
    let requestOptions = this.authenticationService.getRequestOptions(true);
    return this.httpClient.get(this.discoverUsersUrl + pageNumber, requestOptions)
          .map(this.extractData);
  }

  /**
  * Getch a page of discoverable dares
  **/
  discoverDares(pageNumber:number):Observable<Page<DareDescription>>{
    if(pageNumber <= 0){
      pageNumber = 1;
    }
    let requestOptions = this.authenticationService.getRequestOptions(true);
    return this.httpClient.get(this.discoverDaresUrl + pageNumber, requestOptions)
          .map(this.extractData);
  }

  private extractData(res: Response) {
    let body = res.json();
    return body || { };
  }
}
