import { Component, OnInit } from '@angular/core';
import { AdminNavbarComponent } from '../../navbar/admin-navbar/admin-navbar.component';
import { AdminBackendService } from '../../../service/admin/admin-backend.service';
import { Logger } from "angular2-logger/core";
import { ActivatedRoute, Params } from '@angular/router';
import { ContactMessageDescription } from '../../../model/response/contact-message-description';
import { Page } from '../../../model/response/page';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  public show:boolean = false;
  public contactMessages:Page<ContactMessageDescription>;

  constructor(private adminBackendService:AdminBackendService,
              private logger:Logger,
              private activatedRoute:ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe((params: Params) => {
      let pageNumber = params['pageNumber'];
      if(pageNumber && pageNumber <= 0){
          pageNumber = 1;
      }else{
        pageNumber = 1;
      }
      this.adminBackendService.pendingContactMessages(pageNumber)
      .subscribe(contactMessagesResponse => {
        this.logger.info(contactMessagesResponse);
        if(contactMessagesResponse.items.length > 0){
          this.show = true;
          this.contactMessages = contactMessagesResponse; 
        }
      }, err => {
        this.logger.info(err);
      });
    });
    //get pending contact messages

  }

}
