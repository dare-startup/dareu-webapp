import { Injectable } from '@angular/core';
import { Logger } from 'angular2-logger/core';
import { Http, Response, Headers, URLSearchParams, RequestOptions } from '@angular/http';

Injectable()
export class ServerUtilities {
  static HOST = "http://albertoruvel.com/dareu-services/rest/";
  static DISCOVER_USERS_CONTEXT_PATH = "user/discover/users?pageNumber=";

  static ADMIN_DARES = "";

  public extractData(res: Response) {
    let body = res.json();
    return body || { };
  }
}
