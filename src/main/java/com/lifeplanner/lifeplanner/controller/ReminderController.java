package com.lifeplanner.lifeplanner.controller;

import com.lifeplanner.lifeplanner.model.Reminder;
import com.lifeplanner.lifeplanner.repository.ReminderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reminders")
public class ReminderController {

    private final ReminderRepository reminderRepository;

    public ReminderController(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    @GetMapping
    public String reminders(Model model) {
        model.addAttribute("reminders", reminderRepository.findAll());
        return "reminders";
    }

    @PostMapping("/add")
    public String addReminder(
            @RequestParam String title,
            @RequestParam String reminderDate,
            @RequestParam String description) {

        if (title != null && !title.trim().isEmpty()
                && reminderDate != null && !reminderDate.trim().isEmpty()) {

            Reminder reminder = new Reminder(
                    title,
                    reminderDate,
                    description
            );

            reminderRepository.save(reminder);
        }

        return "redirect:/reminders";
    }

    @GetMapping("/delete/{id}")
    public String deleteReminder(@PathVariable Long id) {
        reminderRepository.deleteById(id);
        return "redirect:/reminders";
    }
}