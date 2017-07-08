import { Injectable } from '@angular/core';
import { Logger } from "angular2-logger/core";
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { AuthenticationService } from '../security/authentication.service';
import { Page } from '../../model/response/page';
import { Category } from '../../model/response/category';
import { ServerUtilities } from '../server-utilities';
import { CategoryRequest } from '../../model/category-request';

@Injectable()
export class AdminBackendService {

  private categoriesPath = "dare/category?pageNumber=";
  private createCategoryPath = "admin/dare/category/create";
  private updateCategoryPath = "admin/dare/category/update";

  constructor(private authenticationService:AuthenticationService,
              private logger: Logger,
              private httpClient:Http,
              private serverUtilities:ServerUtilities) { }

  categories(pageNumber:number):Observable<Page<Category>> {
    let url = ServerUtilities.HOST + this.categoriesPath + pageNumber;

    return this.httpClient.get(url, this.authenticationService.getRequestOptions(true))
      .map(this.serverUtilities.extractData);
  }

  createCategory(request:CategoryRequest){
    let url = ServerUtilities.HOST + this.createCategoryPath;
    let jsonBody = JSON.stringify(request);
    return this.httpClient.post(url, jsonBody, this.authenticationService.getPOSTRequestOptions(true))
      .map(this.serverUtilities.extractData);
  }

  editCategory(request:CategoryRequest){
    let url = ServerUtilities.HOST + this.updateCategoryPath;
    let jsonBody = JSON.stringify(request);
    return this.httpClient.put(url, jsonBody, this.authenticationService.getPOSTRequestOptions(true))
      .map(this.serverUtilities.extractData);
  }

}
