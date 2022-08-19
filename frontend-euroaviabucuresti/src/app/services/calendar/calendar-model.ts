export class CalendarModel{
  public id:number;
  public title:string;
  public startDate:string;
  public endDate:string;
  public allDay:boolean;


  constructor(id: number, title: string, startDate: string, endDate: string, allDay: boolean) {
    this.id = id;
    this.title = title;
    this.startDate = startDate;
    this.endDate = endDate;
    this.allDay = allDay;
  }
}
