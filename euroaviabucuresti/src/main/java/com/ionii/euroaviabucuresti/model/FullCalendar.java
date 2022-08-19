package com.ionii.euroaviabucuresti.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="fullcalendar")
public class FullCalendar {
    @Id
    @GeneratedValue(strategy =IDENTITY)
    private Long fullCalendarId;
    private String title;
    private String startDate;
    private String endDate;
    private Boolean allDay;

    public FullCalendar(String title, String startDate, String endDate, Boolean allDay) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.allDay = allDay;
    }
}
