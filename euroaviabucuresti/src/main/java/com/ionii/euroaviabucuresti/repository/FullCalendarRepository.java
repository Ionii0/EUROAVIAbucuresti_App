package com.ionii.euroaviabucuresti.repository;

import com.ionii.euroaviabucuresti.model.FullCalendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FullCalendarRepository extends JpaRepository<FullCalendar,Long> {
    List<FullCalendar> findAll();
}
