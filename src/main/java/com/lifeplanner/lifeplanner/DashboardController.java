package com.lifeplanner.lifeplanner;

import com.lifeplanner.lifeplanner.repository.GoalRepository;
import com.lifeplanner.lifeplanner.repository.ReminderRepository;
import com.lifeplanner.lifeplanner.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final TaskRepository taskRepository;
    private final GoalRepository goalRepository;
    private final ReminderRepository reminderRepository;

    public DashboardController(
            TaskRepository taskRepository,
            GoalRepository goalRepository,
            ReminderRepository reminderRepository) {

        this.taskRepository = taskRepository;
        this.goalRepository = goalRepository;
        this.reminderRepository = reminderRepository;
    }

    @GetMapping("/")
    public String dashboard(Model model) {

        long totalTasks = taskRepository.count();

        long completedTasks = taskRepository.findAll()
                .stream()
                .filter(task -> task.isCompleted())
                .count();

        double averageGoalProgress = goalRepository.findAll()
                .stream()
                .mapToInt(goal -> goal.getProgress())
                .average()
                .orElse(0);

        long totalReminders = reminderRepository.count();

        model.addAttribute("totalTasks", totalTasks);
        model.addAttribute("completedTasks", completedTasks);
        model.addAttribute("averageGoalProgress", averageGoalProgress);
        model.addAttribute("totalReminders", totalReminders);

        return "dashboard";
    }
}