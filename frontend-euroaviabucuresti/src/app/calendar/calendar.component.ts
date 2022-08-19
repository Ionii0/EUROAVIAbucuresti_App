import {Component, OnInit} from '@angular/core';
import {CalendarOptions, DateSelectArg, EventClickArg, EventApi} from '@fullcalendar/angular';
import {createEventId, INITIAL_EVENTS} from "./event-utils";
import {AuthService} from "../auth/auth/auth.service";
import {CalendarService} from "../services/calendar/calendar.service";
import {EventInput} from '@fullcalendar/angular';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  private isAdmin?: boolean;
  public calendarOptions!: CalendarOptions;
  public eventInput: EventInput[] = [];


  constructor(private authService: AuthService, private calendarService: CalendarService) {
    //this.calendarEvents= this.calendarService.retrieveEvents();
    this.calendarService.retrieveEvents().subscribe((events => {
        events.forEach(calendarEvent=>{
          this.eventInput.push({
            id:calendarEvent.id.toString(),
            title:calendarEvent.title,
            start:calendarEvent.startDate,
            end:calendarEvent.endDate,
            allDay:calendarEvent.allDay
          });
        });
        console.log(this.eventInput);
        console.log(INITIAL_EVENTS);
        this.authService.isAdmin().subscribe((response: any) => {
            if (response == true) {
              this.isAdmin = true;
            } else console.log("false");
          },
          () => {
            console.log("error")
          },// on error
          () => {// on complete
            this.calendarOptions = {
              headerToolbar: {
                left: 'prev,next today',
                center: 'title',
                right: this.initializeRightCalendar()
              },
              initialView: 'dayGridMonth',
              events: this.eventInput,//this.calendarEvents
              weekends: true,
              editable: this.isAdmin,
              selectable: this.isAdmin,
              selectMirror: true,
              dayMaxEvents: true,
              select: this.handleDateSelect.bind(this),
              eventClick: this.handleEventClick.bind(this),
              eventsSet: this.handleEvents.bind(this)
              /* you can update a remote database when these fire:
              eventAdd:
              eventChange:
              eventRemove:
              */
            }
          });
      }),
      () => {
        console.log("error event input")
      })
  }

  ngOnInit(): void {
  }

  calendarVisible = true;
  currentEvents: EventApi[] = [];

  initializeRightCalendar(): string | undefined {
    if (this.isAdmin)
      return "dayGridMonth,timeGridWeek,timeGridDay";
    else return "dayGridMonth";
  }

  handleCalendarToggle() {
    this.calendarVisible = !this.calendarVisible;
  }

  handleWeekendsToggle() {
    const {calendarOptions} = this;
    calendarOptions.weekends = !calendarOptions.weekends;
  }

  handleDateSelect(selectInfo: DateSelectArg) {
    const title = prompt('Please enter a new title for your event');
    const calendarApi = selectInfo.view.calendar;

    calendarApi.unselect(); // clear date selection

    if (title) {
      calendarApi.addEvent({
        id: createEventId(),
        title,
        start: selectInfo.startStr,
        end: selectInfo.endStr,
        allDay: selectInfo.allDay
      });
      this.calendarService.addEvent(title, selectInfo.startStr, selectInfo.endStr, selectInfo.allDay).subscribe();
      console.log("*****\n" + "title: " + title + "\n" + "start: " + selectInfo.startStr + "\n" + "end: "
        + selectInfo.endStr + "\n" + "day: " + selectInfo.allDay + "\n*****");
    }
  }

  handleEventClick(clickInfo: EventClickArg) {
    if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
      clickInfo.event.remove();
    }
    this.calendarService.removeEvent(clickInfo.event.id).subscribe();
    console.log("\nid: " + clickInfo.event.id);
  }

  handleEvents(events: EventApi[]) {
    this.currentEvents = events;
  }


}
