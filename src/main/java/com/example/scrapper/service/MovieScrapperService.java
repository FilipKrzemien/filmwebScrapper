package com.example.scrapper.service;

import com.example.scrapper.dto.MovieListDTO;
import com.example.scrapper.dto.UrlLinksDTO;
import com.example.scrapper.scrapper.Scrapper;
import io.vavr.control.Try;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;

@Service
public class MovieScrapperService {
    Scrapper scrapper = new Scrapper();
    public static final Pattern urlPattern = Pattern.compile("filmweb\\.pl/film/");

    public MovieListDTO scrapMovies(UrlLinksDTO urlLinksDTO) {
        MovieListDTO movieListDTO = new MovieListDTO();
        List<String> urls = urlLinksDTO.getUrls();
        movieListDTO.setOriginalSize(urls.size());
        long processedMoviesUrls = urls.stream()
                .filter(url -> urlPattern.matcher(url).find())
                .map(movie -> Try.run(()-> movieListDTO.addMovie(scrapper.getMovieDetails(movie)))).count();
        movieListDTO.setCurrentSize(processedMoviesUrls);

        return movieListDTO;
    }

}
