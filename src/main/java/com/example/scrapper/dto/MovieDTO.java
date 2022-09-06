package com.example.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;
import java.util.Optional;

@JsonPropertyOrder({"Title", "OriginalTitle", "Rating", "Genres", "PremiereDate", "Directors", "Poster", "Duration", "Description"})
public record MovieDTO(@Getter @JsonProperty("Title") String title,
                       @Getter @JsonProperty("OriginalTitle") Optional<String> originalTitle,
                       @Getter @JsonProperty("Rating") String rating,
                       @Getter @JsonProperty("Genres") List<String> genres,
                       @Getter @JsonProperty("PremiereDate") String premiereDate,
                       @Getter @JsonProperty("Directors") List<String> directors,
                       @Getter @JsonProperty("Poster") String poster,
                       @Getter @JsonProperty("Duration") String duration,
                       @Getter @JsonProperty("Description") String description) {

    public MovieDTO(String title, Optional<String> originalTitle, String rating, List<String> genres, String premiereDate,
                    List<String> directors, String poster, String duration, String description) {
        this.title = title;
        this.originalTitle = originalTitle;
        this.rating = rating;
        this.genres = genres;
        this.premiereDate = premiereDate;
        this.directors = directors;
        this.poster = poster;
        this.duration = duration;
        this.description = description;
    }
}
