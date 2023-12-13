package com.andrew.tgserver.entities.api_entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PictureEntity {
    private String id;
    private String slug;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonProperty("created_at")
    private String createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JsonProperty("updated_at")
    private String updatedAt;
    @JsonProperty("promoted_at")
    private String promotedAt;
    private int width;
    private int height;
    private String color;
    @JsonProperty("blur_hash")
    private String blurHash;
    private String description;
    @JsonProperty("alt_description")
    private String altDescription;
    @JsonIgnore
    private Object breadcrumbs;
    private Urls urls;
    @JsonIgnore
    private Object links;
    private int likes;
    @JsonProperty("liked_by_user")
    private boolean likedByUser;
    @JsonIgnore
    @JsonProperty("current_user_collections")
    private Object currentUserCollections;
    @JsonIgnore
    private Object sponsorship;
    @JsonIgnore
    @JsonProperty("topic_submissions")
    private Object topicSubmissions;
    @JsonIgnore
    private Object user;
    @JsonIgnore
    private Object tags;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Urls {
        private String raw;
        private String full;
        private String regular;
        private String small;
        private String thumb;
        private String small_s3;
    }
}
