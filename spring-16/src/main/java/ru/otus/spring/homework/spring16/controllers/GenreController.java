package ru.otus.spring.homework.spring16.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.spring.homework.spring16.models.dto.GenreDto;
import ru.otus.spring.homework.spring16.service.GenreService;

@Controller
public class GenreController {

    private static final String URL_GENRE_LIST = "/genres";

    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String getGenres(Model model) {
        var genres = genreService.getAll();
        model.addAttribute("genres", genres);
        return "genreList";
    }

    @GetMapping("/genres/{id}")
    public String getGenres(@PathVariable("id") Long genreId, Model model) {
        GenreDto genre = null;
        if (genreId.equals(0L)) {
            genre = new GenreDto();
            genre.setId("0");
        } else {
            genre = genreService.getGenre(genreId);
        }
        model.addAttribute("genre", genre);
        return "genreEdit";
    }

    @PostMapping("/genres")
    public RedirectView addGenre(@ModelAttribute GenreDto genreDto) {
        if (genreDto != null) {
            genreService.addGenre(genreDto.getName());
        }
        return new RedirectView(GenreController.URL_GENRE_LIST);
    }

    @PostMapping("/genres/{id}")
    public RedirectView updateGenre(@ModelAttribute GenreDto genreDto) {
        if (genreDto != null) {
            genreService.updateGenre(genreDto);
        }
        return new RedirectView(GenreController.URL_GENRE_LIST);
    }

    @PostMapping("/genres/{id}/remove")
    public RedirectView deleteGenre(@PathVariable("id") String genreId) {
        if (genreId != null) {
            genreService.deleteGenre(Long.parseLong(genreId));
        }
        return new RedirectView(GenreController.URL_GENRE_LIST);
    }

}
