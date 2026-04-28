package com.cpt208.museumplatform.model;

public class ArtifactRecord {

    private final Long id;
    private final String type;
    private final String title;
    private final String imageLabel;
    private final String imageUrl;
    private final String description;
    private final boolean favorite;

    public ArtifactRecord(Long id, String type, String title, String imageLabel, String imageUrl, String description, boolean favorite) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.imageLabel = imageLabel;
        this.imageUrl = imageUrl;
        this.description = description;
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getImageLabel() {
        return imageLabel;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
