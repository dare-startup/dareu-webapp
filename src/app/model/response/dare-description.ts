import { UserDescription } from './user-description';

export class DareDescription {
  constructor(public id:string,
              public name:string,
              public description:string,
              public category:string,
              public estimatedDareTime:string,
              public creationDate:string,
              public challenger:UserDescription,
              public completed:boolean) { }
}
