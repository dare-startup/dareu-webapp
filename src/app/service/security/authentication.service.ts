import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Logger } from "angular2-logger/core";
import { Observable } from 'rxjs/Observable';
import { Router } from '@angular/router';
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/map';

//classes
import { ServerUtilities } from 'app/service/server-utilities';
import { SigninRequest } from '../../model/signin-request';
import { AuthenticationResponse } from 'app/model/response/authentication-response';
import { RestContextPath } from 'app/enum/rest-context-path.enum';

@Injectable()
export class AuthenticationService {

  signinPath = 'security/authenticate';

  constructor(private httpClient: Http,
              private log:Logger,
              private router:Router,
              private serverUtilities:ServerUtilities) { }

  /**
  * creates a sign-in request to DareU server
  **/
  signin(request:SigninRequest):Observable<AuthenticationResponse> {
    let requestBody = JSON.stringify(request);
    return this.httpClient.post(ServerUtilities.HOST + this.signinPath, requestBody, this.getPOSTRequestOptions(false))
          .map(this.serverUtilities.extractData);
  }

  /**
  * Get the current authentication token
  **/
  getAuthenticationToken(): AuthenticationResponse {
    return JSON.parse(localStorage.getItem('authentication_token'));
  }

  /**
  * Set the current authentication token
  **/
  setAuthenticationToken(token:AuthenticationResponse): void {
    let jsonString = JSON.stringify(token);
    localStorage.setItem('authentication_token', jsonString);
  }

  /**
  * Removes the current authentication token
  **/
  deleteAuthenticationToken(): void {
    localStorage.removeItem('authentication_token');
  }

  /**
  * check wheter the user is already authenticated
  **/
  isAuthenticated(router:Router): boolean {
    let authenticationToken = localStorage.getItem('authentication_token');
    if(authenticationToken != null){
      //authentication token is not null
      //TODO: should I validate it?
      return true;
    }
    return false;
  }

  /**
  * get request options
  **/
  getPOSTRequestOptions(authenticated:boolean): RequestOptions{
    let headers = null;
    if(authenticated){
      headers = new Headers({
        'content-type': 'application/json',
        "accept": "application/json",
        "authorization": this.getAuthenticationToken().token
      });
    } else {
      headers = new Headers({
        'content-type': 'application/json',
        "accept": "application/json"
      });
    }
    return new RequestOptions({headers: headers});
  }


  /**
  * get request options
  **/
  getRequestOptions(authenticated:boolean): RequestOptions{
    let headers = null;
    if(authenticated){
      headers = new Headers({
        "authorization": this.getAuthenticationToken().token,
        "accept": "application/json"
      });
    } else {
      headers = new Headers({
        "accept": "application/json"
      });
    }
    return new RequestOptions({ headers: headers });
  }
}
