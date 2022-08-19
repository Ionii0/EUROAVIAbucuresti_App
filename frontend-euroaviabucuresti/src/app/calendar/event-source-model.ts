import {CalendarModel} from "../services/calendar/calendar-model";

export class EventSourceModel{
  private events:Array<CalendarModel>;


  constructor(events: Array<CalendarModel>) {
    this.events = events;
  }
}
