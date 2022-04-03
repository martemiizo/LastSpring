package ru.vtb.Last.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PremiereDto {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty("comment")
    private String description;
    @JsonProperty
    private Integer ageCategory;
    @JsonProperty
    private Integer numberOfSeats;

    public PremiereDto() {
    }

    public PremiereDto(Long id, String name, String description, Integer ageCategory, Integer numberOfSeats) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.ageCategory = ageCategory;
        this.numberOfSeats = numberOfSeats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(Integer ageCategory) {
        this.ageCategory = ageCategory;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
