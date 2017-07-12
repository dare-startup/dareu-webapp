import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

//components
import { SigninCardComponent } from './signin-card/signin-card.component';

//providers
import { AuthenticationService } from 'app/service/security/authentication.service';
import { AuthenticationResponse } from '../../model/response/authentication-response';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.css']
})
export class SigninComponent implements OnInit {

  constructor(private authenticationService:AuthenticationService,
              private router:Router) { }

  ngOnInit() {
    if(this.authenticationService.isAuthenticated(this.router)) {
      let authenticationResponse = this.authenticationService.getAuthenticationToken();
      let userRole = authenticationResponse.userRole;
      this.router.navigate([userRole]);
    }
  }

}
