package com.lifeplanner.lifeplanner.controller;

import com.lifeplanner.lifeplanner.model.Goal;
import com.lifeplanner.lifeplanner.repository.GoalRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/goals")
public class GoalController {

    private final GoalRepository goalRepository;

    public GoalController(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @GetMapping
    public String goals(Model model) {
        model.addAttribute("goals", goalRepository.findAll());
        return "goals";
    }

    @PostMapping("/add")
    public String addGoal(
            @RequestParam String title,
            @RequestParam String description) {

        if (title != null && !title.trim().isEmpty()) {
            Goal goal = new Goal(title, description);
            goalRepository.save(goal);
        }

        return "redirect:/goals";
    }

    @GetMapping("/delete/{id}")
    public String deleteGoal(@PathVariable Long id) {
        goalRepository.deleteById(id);
        return "redirect:/goals";
    }

    @PostMapping("/progress/{id}")
    public String updateProgress(
            @PathVariable Long id,
            @RequestParam int progress) {

        Goal goal = goalRepository.findById(id).orElse(null);

        if (goal != null) {
            if (progress < 0) {
                progress = 0;
            }

            if (progress > 100) {
                progress = 100;
            }

            goal.setProgress(progress);
            goalRepository.save(goal);
        }

        return "redirect:/goals";
    }
}