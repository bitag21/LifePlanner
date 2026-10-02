package com.lifeplanner.lifeplanner.controller;

import com.lifeplanner.lifeplanner.repository.GoalRepository;
import com.lifeplanner.lifeplanner.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProgressController {

    private final TaskRepository taskRepository;
    private final GoalRepository goalRepository;

    public ProgressController(
            TaskRepository taskRepository,
            GoalRepository goalRepository) {

        this.taskRepository = taskRepository;
        this.goalRepository = goalRepository;
    }

    @GetMapping("/progress")
    public String progress(Model model) {

        long totalTasks = taskRepository.count();

        long completedTasks = taskRepository.findAll()
                .stream()
                .filter(task -> task.isCompleted())
                .count();

        long totalGoals = goalRepository.count();

        double averageGoalProgress = goalRepository.findAll()
                .stream()
                .mapToInt(goal -> goal.getProgress())
                .average()
                .orElse(0);

        model.addAttribute("totalTasks", totalTasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("totalGoals", totalGoals);
        model.addAttribute("averageGoalProgress", averageGoalProgress);

        return "progress";
    }
}