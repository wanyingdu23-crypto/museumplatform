package com.cpt208.museumplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "puzzle_artifacts")
public class PuzzleArtifactEntity {

    @Id
    private Long id;

    @Column(nullable = false, unique = true, length = 160)
    private String title;

    @Column(name = "title_zh", length = 160)
    private String titleZh;

    @Column(name = "image_label", nullable = false, length = 160)
    private String imageLabel;

    @Column(name = "image_label_zh", length = 160)
    private String imageLabelZh;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "thumbnail_url", length = 500)
    private String thumbnailUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "description_zh", columnDefinition = "TEXT")
    private String descriptionZh;

    @Column(name = "background_story", nullable = false, columnDefinition = "TEXT")
    private String backgroundStory;

    @Column(name = "background_story_zh", columnDefinition = "TEXT")
    private String backgroundStoryZh;

    @Column(name = "hint_text", columnDefinition = "TEXT")
    private String hintText;

    @Column(name = "hint_text_zh", columnDefinition = "TEXT")
    private String hintTextZh;

    @Column(nullable = false, length = 80)
    private String category;

    @Column(name = "shape_tags", nullable = false, length = 255)
    private String shapeTags;

    @Column(length = 120)
    private String culture;

    @Column(length = 120)
    private String department;

    protected PuzzleArtifactEntity() {
    }

    public PuzzleArtifactEntity(
        Long id,
        String title,
        String titleZh,
        String imageLabel,
        String imageLabelZh,
        String imageUrl,
        String thumbnailUrl,
        String description,
        String descriptionZh,
        String backgroundStory,
        String backgroundStoryZh,
        String hintText,
        String hintTextZh,
        String category,
        String shapeTags,
        String culture,
        String department
    ) {
        this.id = id;
        this.title = title;
        this.titleZh = titleZh;
        this.imageLabel = imageLabel;
        this.imageLabelZh = imageLabelZh;
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.descriptionZh = descriptionZh;
        this.backgroundStory = backgroundStory;
        this.backgroundStoryZh = backgroundStoryZh;
        this.hintText = hintText;
        this.hintTextZh = hintTextZh;
        this.category = category;
        this.shapeTags = shapeTags;
        this.culture = culture;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getTitleZh() {
        return titleZh;
    }

    public String getImageLabel() {
        return imageLabel;
    }

    public String getImageLabelZh() {
        return imageLabelZh;
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

    public String getDescriptionZh() {
        return descriptionZh;
    }

    public String getBackgroundStory() {
        return backgroundStory;
    }

    public String getBackgroundStoryZh() {
        return backgroundStoryZh;
    }

    public String getHintText() {
        return hintText;
    }

    public String getHintTextZh() {
        return hintTextZh;
    }

    public String getCategory() {
        return category;
    }

    public String getShapeTags() {
        return shapeTags;
    }

    public String getCulture() {
        return culture;
    }

    public String getDepartment() {
        return department;
    }

    public String getTitleByLanguage(String lang) {
        return isZh(lang) && titleZh != null && !titleZh.isBlank() ? titleZh : title;
    }

    public String getImageLabelByLanguage(String lang) {
        return isZh(lang) && imageLabelZh != null && !imageLabelZh.isBlank() ? imageLabelZh : imageLabel;
    }

    public String getDescriptionByLanguage(String lang) {
        return isZh(lang) && descriptionZh != null && !descriptionZh.isBlank() ? descriptionZh : description;
    }

    public String getBackgroundStoryByLanguage(String lang) {
        return isZh(lang) && backgroundStoryZh != null && !backgroundStoryZh.isBlank() ? backgroundStoryZh : backgroundStory;
    }

    public String getHintTextByLanguage(String lang) {
        return isZh(lang) && hintTextZh != null && !hintTextZh.isBlank() ? hintTextZh : hintText;
    }

    private boolean isZh(String lang) {
        return "zh".equalsIgnoreCase(lang);
    }
}
