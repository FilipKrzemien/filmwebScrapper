package com.example.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@JsonPropertyOrder({"Movies", "OriginalSize", "CurrentSize"})
@Getter
public class MovieListDTO {

    @JsonProperty("Movies")
    List<MovieDTO> movies = new ArrayList<>();

    @Setter
    @JsonProperty("OriginalSize")
    long originalSize;

    @Setter
    @JsonProperty("CurrentSize")
    long currentSize;

    public void addMovie(MovieDTO movie) {
        this.movies.add(movie);
    }
}
