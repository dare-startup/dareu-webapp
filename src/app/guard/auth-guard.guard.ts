import { Router, CanActivate } from '@angular/router';
import { Injectable } from '@angular/core';

import { AuthenticationService } from '../service/security/authentication.service';

@Injectable()
export class AuthGuard implements CanActivate{

  constructor(private router: Router,
              private authenticationService:AuthenticationService) { }

  canActivate() {
      if(this.authenticationService.getAuthenticationToken() != null){
        //an auth token has been found, let the user access the specified url
        return true;
      }
      this.router.navigate(['/signin']);
      return false;
  }
}
