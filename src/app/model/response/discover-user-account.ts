export class DiscoverUserAccount {
  constructor(public id:string,
              public name:string,
              public coins:number,
              public uscore:number,
              public dares:number,
              public responses:number,
              public requestSent:boolean,
              public requestReceived:boolean,
              public imageUrl:string) { }
}
