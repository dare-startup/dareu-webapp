import { Component, OnInit } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { Logger } from "angular2-logger/core";
import { Router } from '@angular/router';

//providers
import { DareuPublicBackendService } from 'app/service/public/dareu-public-backend.service';
import { AuthenticationService } from 'app/service/security/authentication.service';

@Component({
  selector: 'app-signup-form',
  templateUrl: './signup-form.component.html',
  styleUrls: ['../signup.css']
})
export class SignupFormComponent implements OnInit {

  errorSigningUp = false;

  //form group
  signupFormGroup = this.formBuilder.group({
    'name': [null, Validators.required],
    'email': [null, Validators.required],
    'password': [null, Validators.required],
    'birthday': [null, Validators.required]
  });

  constructor(private formBuilder:FormBuilder,
              private publicBackendService:DareuPublicBackendService,
              private authenticationService:AuthenticationService,
              private router:Router,
              private logger:Logger) { }

  ngOnInit() {
  }

  /**
  * This function is executed when a user submit a signup form
  **/
  onSignupSubmit(signupRequest){
    this.publicBackendService.signup(signupRequest)
            .subscribe(authenticationResponse => {
                //save auth token
                this.authenticationService.setAuthenticationToken(authenticationResponse);
                this.router.navigate(["/user/"]);
            }, err => {
                //error signing up
                this.errorSigningUp = true;
            }
    );
  }
}
