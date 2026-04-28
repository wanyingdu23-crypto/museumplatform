package com.cpt208.museumplatform.model;

public class ArtifactDetail {

    private final Long id;
    private final String title;
    private final String imageLabel;
    private final String imageUrl;
    private final String thumbnailUrl;
    private final String description;
    private final boolean favorite;

    public ArtifactDetail(Long id, String title, String imageLabel, String imageUrl, String thumbnailUrl, String description, boolean favorite) {
        this.id = id;
        this.title = title;
        this.imageLabel = imageLabel;
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.favorite = favorite;
    }

    public Long getId() {
        return id;
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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public boolean isFavorite() {
        return favorite;
    }
}
