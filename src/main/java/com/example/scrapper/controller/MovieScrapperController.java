package com.example.scrapper.controller;

import com.example.scrapper.dto.MovieListDTO;
import com.example.scrapper.dto.UrlLinksDTO;
import com.example.scrapper.service.MovieScrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieScrapperController {

    @Autowired
    MovieScrapperService movieScrapperService;

    @PostMapping("/movie/add")
    public MovieListDTO addMovies(@RequestBody UrlLinksDTO urlLinks) {
        return movieScrapperService.scrapMovies(urlLinks);
    }
}
