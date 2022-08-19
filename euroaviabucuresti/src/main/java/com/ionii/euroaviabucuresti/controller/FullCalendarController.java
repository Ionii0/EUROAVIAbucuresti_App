package com.ionii.euroaviabucuresti.controller;

import com.ionii.euroaviabucuresti.dto.FullCalendarDto;
import com.ionii.euroaviabucuresti.service.FullCalendarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fullcalendar")
@AllArgsConstructor
public class FullCalendarController {
    private final FullCalendarService fullCalendarService;

    @GetMapping("/addEvent/{title}/{startDate}/{endDate}/{allDay}")
    public ResponseEntity<Void> addEvent(@PathVariable String title, @PathVariable String startDate,
                                         @PathVariable String endDate, @PathVariable Boolean allDay) {
        fullCalendarService.addEvents(title, startDate, endDate, allDay);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/retrieveEvents")
    public ResponseEntity<List<FullCalendarDto>> retrieveEvents() {
        return ResponseEntity.status(HttpStatus.OK).body(this.fullCalendarService.retrieveEvents());
    }

    @GetMapping("/removeEvent/{id}")
    public ResponseEntity<Void> removeEvent(@PathVariable Long id) {
        fullCalendarService.removeEvent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
