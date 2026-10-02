package com.lifeplanner.lifeplanner.controller;

import com.lifeplanner.lifeplanner.model.VisionItem;
import com.lifeplanner.lifeplanner.repository.VisionItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vision-board")
public class VisionBoardController {

    private final VisionItemRepository visionItemRepository;

    public VisionBoardController(VisionItemRepository visionItemRepository) {
        this.visionItemRepository = visionItemRepository;
    }

    @GetMapping
    public String visionBoard(Model model) {
        model.addAttribute("visionItems", visionItemRepository.findAll());
        return "vision-board";
    }

    @PostMapping("/add")
    public String addVisionItem(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String quote,
            @RequestParam String imageUrl) {

        if (title != null && !title.trim().isEmpty()) {

            VisionItem item = new VisionItem(
                    title,
                    description,
                    quote,
                    imageUrl
            );

            visionItemRepository.save(item);
        }

        return "redirect:/vision-board";
    }

    @GetMapping("/delete/{id}")
    public String deleteVisionItem(@PathVariable Long id) {
        visionItemRepository.deleteById(id);
        return "redirect:/vision-board";
    }
}