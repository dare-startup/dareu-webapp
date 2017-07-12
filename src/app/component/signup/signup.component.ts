import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

//components
import { AnnonymousNavbarComponent } from 'app/component/navbar/annonymous-navbar/annonymous-navbar.component';

//providers
import { AuthenticationService } from 'app/service/security/authentication.service';
import { AuthenticationResponse } from '../../model/response/authentication-response';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.css']
})
export class SignupComponent implements OnInit {

  constructor(private router:Router,
              private authenticationService:AuthenticationService) { }

  ngOnInit() {
    if(this.authenticationService.isAuthenticated(this.router)) {
      let authenticationResponse = this.authenticationService.getAuthenticationToken();
      let userRole = authenticationResponse.userRole;
      this.router.navigate([userRole]);
    }
  }

}
