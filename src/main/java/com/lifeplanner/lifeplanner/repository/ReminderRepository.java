package com.lifeplanner.lifeplanner.repository;

import com.lifeplanner.lifeplanner.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}