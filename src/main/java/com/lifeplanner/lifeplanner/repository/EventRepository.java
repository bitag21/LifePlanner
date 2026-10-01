package com.lifeplanner.lifeplanner.repository;

import com.lifeplanner.lifeplanner.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}