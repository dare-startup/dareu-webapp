import { Component, OnInit } from '@angular/core';
import { Logger } from "angular2-logger/core";
import { ActivatedRoute, Params, Router } from "@angular/router";
import { AdminNavbarComponent } from '../../../navbar/admin-navbar/admin-navbar.component';
import { AdminBackendService } from '../../../../service/admin/admin-backend.service';
import { Page } from '../../../../model/response/page';
import { Category } from '../../../../model/response/category';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { CategoryRequest } from '../../../../model/category-request';

@Component({
  selector: 'app-admin-categories',
  templateUrl: './admin-categories.component.html',
  styleUrls: ['./admin-categories.component.css']
})
export class AdminCategoriesComponent implements OnInit {

  public currentCategory:Category = null;
  public isUpdating:boolean = false;
  public categoryOperationSuccess:boolean = false;
  private categories:Page<Category>;
  public categoryRequest = this.formBuilder.group({
    id: [''],
    name: ['', Validators.required],
    description: ['', Validators.required]
  });

  constructor(private adminBackendService:AdminBackendService,
              private logger:Logger,
              private formBuilder:FormBuilder,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    //get categories using pageNumber
    this.activatedRoute.params.subscribe((params: Params) => {
      let pageNumber = params['pageNumber'];
      if(pageNumber && pageNumber <= 0){
          pageNumber = 1;
      }else{
        pageNumber = 1;
      }
      this.adminBackendService.categories(pageNumber)
        .subscribe(categoriesPageResponse => {
          this.categories = categoriesPageResponse;
        }, err => {
          this.logger.error(err);
        });
    })
  }

  addCategory(){
    this.categoryRequest.reset();
  }

  onSubmit(event){
    let category = null;
    if(this.isUpdating){
      category = new CategoryRequest(event.name, event.description, event.id);
      this.adminBackendService.editCategory(category)
        .subscribe(updateCategoryResponse => {
          this.logger.info(updateCategoryResponse);
        }, err => {
          this.logger.info(err); 
        });
    }else{
      //create a category
      category = new CategoryRequest(event.name, event.description, null);
      this.adminBackendService.createCategory(category)
        .subscribe(createCategoryResponse => {
          this.categoryOperationSuccess = true;
          this.currentCategory = category;
          this.categories.items.push(new Category(createCategoryResponse.id, this.currentCategory.name, this.currentCategory.description));
          this.categoryRequest.reset();
        }, err => {
          this.logger.error(err);
        });
    }
  }

  dismiss(){
    this.logger.info("Dismissing alert");
  }

  editCategory(category){
    this.categoryOperationSuccess = false;
    this.isUpdating = true;
    this.categoryRequest.setValue({
      name: category.name,
      description: category.description,
      id: category.id
    });
  }
}
