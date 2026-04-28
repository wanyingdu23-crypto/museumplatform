package com.cpt208.museumplatform.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "artifacts")
public class ArtifactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String title;

    @Column(name = "title_zh", length = 120)
    private String titleZh;

    @Column(name = "image_label", nullable = false, length = 120)
    private String imageLabel;

    @Column(name = "image_label_zh", length = 120)
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

    @Column(name = "turtle_question", columnDefinition = "TEXT")
    private String turtleQuestion;

    @Column(name = "turtle_question_zh", columnDefinition = "TEXT")
    private String turtleQuestionZh;

    @Column(nullable = false, length = 80)
    private String category;

    @Column(name = "shape_tags", nullable = false, length = 255)
    private String shapeTags;

    @Column(length = 120)
    private String culture;

    @Column(length = 120)
    private String department;

    @Column(name = "external_source", length = 60)
    private String externalSource;

    @Column(name = "external_object_id")
    private Long externalObjectId;

    @Column(name = "is_public_domain")
    private Boolean publicDomain;

    protected ArtifactEntity() {
    }

    public ArtifactEntity(
        String title,
        String imageLabel,
        String imageUrl,
        String thumbnailUrl,
        String description,
        String backgroundStory,
        String turtleQuestion,
        String category,
        String shapeTags,
        String culture,
        String department,
        String externalSource,
        Long externalObjectId,
        Boolean publicDomain
    ) {
        this.title = title;
        this.imageLabel = imageLabel;
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.backgroundStory = backgroundStory;
        this.turtleQuestion = turtleQuestion;
        this.category = category;
        this.shapeTags = shapeTags;
        this.culture = culture;
        this.department = department;
        this.externalSource = externalSource;
        this.externalObjectId = externalObjectId;
        this.publicDomain = publicDomain;
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

    public String getTurtleQuestion() {
        return turtleQuestion;
    }

    public String getTurtleQuestionZh() {
        return turtleQuestionZh;
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

    public String getExternalSource() {
        return externalSource;
    }

    public Long getExternalObjectId() {
        return externalObjectId;
    }

    public Boolean getPublicDomain() {
        return publicDomain;
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

    public String getTurtleQuestionByLanguage(String lang) {
        return isZh(lang) && turtleQuestionZh != null && !turtleQuestionZh.isBlank() ? turtleQuestionZh : turtleQuestion;
    }

    private boolean isZh(String lang) {
        return "zh".equalsIgnoreCase(lang);
    }
}
