export class AuthenticationResponse {
  constructor(public token: string, public date: string, public message: string, public userRole:string){}
}
