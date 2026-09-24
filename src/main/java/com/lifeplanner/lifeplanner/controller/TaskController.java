package com.lifeplanner.lifeplanner.controller;

import com.lifeplanner.lifeplanner.model.Task;
import com.lifeplanner.lifeplanner.repository.TaskRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping
    public String tasks(Model model) {
        model.addAttribute("tasks", taskRepository.findAll());
        return "tasks";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String title) {

        if (title != null && !title.trim().isEmpty()) {
            Task task = new Task(title);
            taskRepository.save(task);
        }

        return "redirect:/tasks";
    }

    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id) {
        taskRepository.deleteById(id);
        return "redirect:/tasks";
    }

    @GetMapping("/complete/{id}")
    public String completeTask(@PathVariable Long id) {

        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            task.setCompleted(!task.isCompleted());
            taskRepository.save(task);
        }

        return "redirect:/tasks";
    }
}