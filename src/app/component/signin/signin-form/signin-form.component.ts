import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Logger } from "angular2-logger/core";
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';

import { AuthenticationResponse } from 'app/model/response/authentication-response';
import { SigninRequest } from 'app/model/signin-request';
import { AuthenticationService } from '../../../service/security/authentication.service';

@Component({
  selector: 'app-signin-form',
  templateUrl: './signin-form.component.html'
})
export class SigninFormComponent implements OnInit {

  errorSigningIn = false;

  //sign-in form
  public signinForm = this.formBuilder.group({
    email: ['', Validators.required],
    password: ['', Validators.required] //TODO: add validation REGEX
  });

  constructor(private authenticationService:AuthenticationService,
              private formBuilder:FormBuilder,
              private log:Logger,
              private router:Router) { }

  ngOnInit() {
  }

  //submit function
  //{ email, password }
  onSubmit(event){
    //create a new request obj
    let signinRequest = new SigninRequest(event.email, event.password);
    //try to login to dareu backend
    this.authenticationService.signin(signinRequest)
        .subscribe(signinResponse => {
          if(signinResponse.token != null){
            //save token
            this.authenticationService.setAuthenticationToken(signinResponse);
            //check user role here
            if(signinResponse.userRole == "admin"){
              this.router.navigate(['/admin/']);
              //redirect to admin path
            }else if(signinResponse.userRole == "user"){
              this.router.navigate(['/user/']);
              //redirect to user path
            }else if(signinResponse.userRole == "sponsor"){
              //redirect to sponsor path
            }
          }else{
            //show an error message on form
          }
        }, err => {
          this.log.info(err);
          //TODO: message
          //TODO: check status
        });
  }

}
