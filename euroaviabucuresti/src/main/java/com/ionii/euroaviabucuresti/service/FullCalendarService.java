package com.ionii.euroaviabucuresti.service;

import com.ionii.euroaviabucuresti.dto.FullCalendarDto;
import com.ionii.euroaviabucuresti.mapper.FullCalendarMapper;
import com.ionii.euroaviabucuresti.model.FullCalendar;
import com.ionii.euroaviabucuresti.repository.FullCalendarRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import static java.util.stream.Collectors.toList;


import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class FullCalendarService {
    private final FullCalendarRepository fullCalendarRepository;
    private  FullCalendarMapper fullCalendarMapper;

    public void addEvents(String title, String startDate, String endDate, Boolean allDay){
       FullCalendar event = new FullCalendar(title,startDate,endDate,allDay);
       this.fullCalendarRepository.save(event);
    }
    public List<FullCalendarDto> retrieveEvents (){
        return fullCalendarRepository.findAll().stream().map(fullCalendarMapper::mapToDto).collect(toList());
    }

    public void removeEvent(Long id) {
        fullCalendarRepository.deleteById(id);
    }
}
