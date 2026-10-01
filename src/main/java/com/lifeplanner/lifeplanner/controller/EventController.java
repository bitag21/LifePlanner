package com.lifeplanner.lifeplanner.controller;

import com.lifeplanner.lifeplanner.model.Event;
import com.lifeplanner.lifeplanner.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/calendar")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping
    public String calendar(Model model) {
        model.addAttribute("events", eventRepository.findAll());
        return "calendar";
    }

    @PostMapping("/add")
    public String addEvent(
            @RequestParam String title,
            @RequestParam String eventDate,
            @RequestParam String description) {

        if (title != null && !title.trim().isEmpty()
                && eventDate != null && !eventDate.trim().isEmpty()) {

            Event event = new Event(title, eventDate, description);
            eventRepository.save(event);
        }

        return "redirect:/calendar";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable Long id) {
        eventRepository.deleteById(id);
        return "redirect:/calendar";
    }
}