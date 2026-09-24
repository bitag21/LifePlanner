package com.lifeplanner.lifeplanner.repository;

import com.lifeplanner.lifeplanner.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}