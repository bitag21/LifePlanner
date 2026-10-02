package com.lifeplanner.lifeplanner.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vision_items")
public class VisionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private String quote;

    private String imageUrl;

    public VisionItem() {
    }

    public VisionItem(String title, String description, String quote, String imageUrl) {
        this.title = title;
        this.description = description;
        this.quote = quote;
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}