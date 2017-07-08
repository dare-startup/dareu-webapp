import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Logger } from "angular2-logger/core";

//classes
import { ContactRequest } from 'app/model/contact-request';
import { ContactResponse } from 'app/model/response/contact-response';

//components
import { AnnonymousNavbarComponent } from 'app/component/navbar/annonymous-navbar/annonymous-navbar.component';

//providers
import { AuthenticationService } from 'app/service/security/authentication.service';
import { DareuPublicBackendService } from 'app/service/public/dareu-public-backend.service';
@Component({
  selector: 'app-contact-form',
  templateUrl: './contact-form.component.html',
  styleUrls: ['./contact-form.component.css']
})
export class ContactFormComponent implements OnInit {

  messageSent = false;

  //contact form
  public contactForm = this.formBuilder.group({
    'name': [null, [
              Validators.compose([Validators.required,
                                 Validators.minLength(15),
                                 Validators.maxLength(50)])
            ]],
    'email': [null, Validators.required],
    'comment': [null, Validators.compose([Validators.required, Validators.maxLength(300), Validators.minLength(15)])]
  });

  constructor(private authenticationService:AuthenticationService,
              private log:Logger,
              private router:Router,
              private formBuilder:FormBuilder,
              private publicBackendService:DareuPublicBackendService) { }

  ngOnInit() {
  }

  /**
  * This function executes when the contact form has been submitted
  **/
  onContactSubmit(event): void {
    this.publicBackendService.sendContactRequest(event)
              .subscribe(contactResponse => {
                  this.messageSent = true; 
              },
                err => {

                });
  }

}
