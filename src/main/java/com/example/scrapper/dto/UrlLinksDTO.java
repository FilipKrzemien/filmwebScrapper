package com.example.scrapper.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;

import java.util.List;


@JsonPropertyOrder({"movies"})
@Getter
public class UrlLinksDTO {

    @JsonProperty("movies")
    private List<String> urls;
}
