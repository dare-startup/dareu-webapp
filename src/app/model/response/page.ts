export class Page<T> {
  constructor(public items:Array<T>,
              public pageNumber:number,
              public pageSize:number,
              public pagesAvailable:number) { }
}
