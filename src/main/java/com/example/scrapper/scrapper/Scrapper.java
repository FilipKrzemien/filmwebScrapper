package com.example.scrapper.scrapper;

import com.example.scrapper.dto.MovieDTO;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class Scrapper {
    WebClient client;

    public Scrapper() {
        this.client = establishClient();
    }

    public WebClient establishClient() {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
        return client;
    }

    public MovieDTO getMovieDetails(String searchUrl) throws IOException {
        HtmlPage page = client.getPage(searchUrl);

        String title = getTitle(page);
        Optional<String> originalTitle = getOriginalTitle(page);
        String rate = getRate(page);
        List<String> genres = getGenres(page);
        String premiere = getPremiere(page);
        List<String> directors = getDirectors(page);
        String poster = getPoster(page);
        String duration = getDuration(page);
        String description = getDescription(page);

        return new MovieDTO(title, originalTitle, rate, genres, premiere, directors, poster, duration, description);
    }


    public String getTitle(HtmlPage page) {
        HtmlHeading1 itemTitle =
                Optional.ofNullable((HtmlHeading1) page.getFirstByXPath(".//h1[@class='filmCoverSection__title ']"))
                        .orElse(page.getFirstByXPath(".//h1[@class='filmCoverSection__title filmCoverSection__title--large']"));
        return itemTitle.getVisibleText();
    }

    public Optional<String> getOriginalTitle(HtmlPage page) {
        Optional<HtmlElement> itemOriginalTitle =
                Optional.ofNullable(page.getFirstByXPath(".//div[@class='filmCoverSection__originalTitle']"));
        return itemOriginalTitle.map(DomNode::getTextContent);
    }

    public String getRate(HtmlPage page) {
        DomElement itemRate = page.getFirstByXPath("//span[@class='filmRating__rateValue']");
        return itemRate.getVisibleText();
    }

    public List<String> getGenres(HtmlPage page) {
        HtmlElement itemsGenres = page.getFirstByXPath("//div[@class='filmInfo__info']");
        return StreamSupport.stream(itemsGenres.getChildElements().spliterator(), false)
                .map(DomNode::getVisibleText).filter(child -> !child.equals("/")).collect(Collectors.toList());
    }

    public String getPremiere(HtmlPage page) {
        HtmlElement itemPremiere = page.getFirstByXPath("//span[@itemprop='datePublished']");
        return itemPremiere.getVisibleText();
    }

    public List<String> getDirectors(HtmlPage page) {
        HtmlElement itemDirectors = page.getFirstByXPath("//div[@data-type='directing-info']");
        return StreamSupport.stream(itemDirectors.getChildElements().spliterator(), false)
                .map(DomNode::getVisibleText).filter(child -> !child.equals("/")).collect(Collectors.toList());
    }

    public String getPoster(HtmlPage page) {
        DomElement itemPoster = page.getElementById("filmPoster");
        return itemPoster.getAttribute("content");
    }

    public String getDuration(HtmlPage page) {
        HtmlElement itemDuration = page.getFirstByXPath("//div[@class='filmCoverSection__duration']");
        return itemDuration.getVisibleText();
    }

    public String getDescription(HtmlPage page) {
        HtmlElement itemDescription = page.getFirstByXPath("//span[@itemprop='description']");
        return itemDescription.getVisibleText();
    }

}
