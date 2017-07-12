import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Logger } from "angular2-logger/core";
import { Observable } from 'rxjs/Observable';

//classes
import { RestContextPath } from 'app/enum/rest-context-path.enum';
import { ContactRequest } from 'app/model/contact-request';
import { SignupRequest } from 'app/model/signup-request';
import { ContactResponse } from 'app/model/response/contact-response';
import { AuthenticationResponse } from 'app/model/response/authentication-response';
import { ServerUtilities } from 'app/service/server-utilities';
import { AuthenticationService } from '../security/authentication.service';

@Injectable()
export class DareuPublicBackendService {

  private contactPath = "open/contact";
  private signupPath = "open/registerUser";

  constructor(private httpClient:Http,
              private log:Logger,
              private serverUtilities:ServerUtilities,
              private authenticationService:AuthenticationService) { }


  /**
  * Creates a contact request and send it to DareU server
  **/
  sendContactRequest(contactRequest:ContactRequest): Observable<ContactResponse>{
    let url = ServerUtilities.HOST + this.contactPath;

    let body = JSON.stringify(contactRequest);
    return this.httpClient.post(url, body, this.authenticationService.getRequestOptions(false))
      .map(this.serverUtilities.extractData);
  }



  /**
  * Creates a new user on DareU servers
  **/
  signup(signupRequest:SignupRequest): Observable<AuthenticationResponse> {
    let url = ServerUtilities.HOST + this.signupPath;
    let body = JSON.stringify(signupRequest);
    return this.httpClient.post(url, body, this.authenticationService.getPOSTRequestOptions(false))
      .map(this.serverUtilities.extractData);
  }
}
