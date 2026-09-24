package com.lifeplanner.lifeplanner.repository;

import com.lifeplanner.lifeplanner.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {

}
