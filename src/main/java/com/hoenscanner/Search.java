package com.hoenscanner;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Search {
    @JsonProperty
    private String city;

    // Default constructor (required for Jackson)
    public Search() {
    }

    // Constructor with city
    public Search(String city) {
        this.city = city;
    }

    // Getter for city
    public String getCity() {
        return city;
    }
}
