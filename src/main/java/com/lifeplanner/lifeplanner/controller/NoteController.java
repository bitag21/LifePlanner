package com.lifeplanner.lifeplanner.controller;

import com.lifeplanner.lifeplanner.model.Note;
import com.lifeplanner.lifeplanner.repository.NoteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private final NoteRepository noteRepository;

    public NoteController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping
    public String notes(Model model) {
        model.addAttribute("notes", noteRepository.findAll());
        return "notes";
    }

    @PostMapping("/add")
    public String addNote(
            @RequestParam String title,
            @RequestParam String content) {

        if (title != null && !title.trim().isEmpty()) {
            Note note = new Note(title, content);
            noteRepository.save(note);
        }

        return "redirect:/notes";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return "redirect:/notes";
    }
}