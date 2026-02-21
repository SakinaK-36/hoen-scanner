package com.hoenscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SearchResult {
    @JsonProperty
    private String city;

    @JsonProperty
    private String kind;

    @JsonProperty
    private String title;

    // Default constructor (required for Jackson)
    public SearchResult() {
    }

    // Constructor with all fields
    public SearchResult(String city, String kind, String title) {
        this.city = city;
        this.kind = kind;
        this.title = title;
    }

    // Getters (and optionally setters)
    public String getCity() {
        return city;
    }

    public String getKind() {
        return kind;
    }

    public String getTitle() {
        return title;
    }

    // Add these setter methods to SearchResult.java
    public void setCity(String city) {
        this.city = city;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
