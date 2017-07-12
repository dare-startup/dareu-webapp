import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AnnonymousNavbarComponent } from 'app/component/navbar/annonymous-navbar/annonymous-navbar.component';
import { AuthenticationService } from 'app/service/security/authentication.service';
import { AuthenticationResponse } from '../../../model/response/authentication-response';

@Component({
  selector: 'app-welcome-section',
  templateUrl: './welcome-section.component.html',
  styleUrls: ['../home.css']
})
export class WelcomeSectionComponent implements OnInit {

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
