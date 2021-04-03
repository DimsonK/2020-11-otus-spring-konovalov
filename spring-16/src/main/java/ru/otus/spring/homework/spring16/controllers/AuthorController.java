package ru.otus.spring.homework.spring16.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.spring.homework.spring16.models.dto.AuthorDto;
import ru.otus.spring.homework.spring16.service.AuthorService;

@Controller
public class AuthorController {

    private static final String URL_AUTHOR_LIST = "/authors";

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getAuthors(Model model) {
        var authors = authorService.getAll();
        model.addAttribute("authors", authors);
        return "authorList";
    }

    @GetMapping("/authors/{id}")
    public String getAuthor(@PathVariable("id") Long authorId, Model model) {
        AuthorDto author;
        if (authorId.equals(0L)) {
            author = new AuthorDto();
            author.setId("0");
        } else {
            author = authorService.getAuthor(authorId);
        }
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @PostMapping("/authors")
    public RedirectView addAuthor(@ModelAttribute AuthorDto authorDto) {
        if (authorDto != null) {
            authorService.addAuthor(authorDto.getName());
        }
        return new RedirectView(AuthorController.URL_AUTHOR_LIST);
    }

    @PostMapping("/authors/{id}")
    public RedirectView updateAuthor(@ModelAttribute AuthorDto authorDto) {
        if (authorDto != null) {
            authorService.updateAuthor(authorDto);
        }
        return new RedirectView(AuthorController.URL_AUTHOR_LIST);
    }

    @PostMapping("/authors/{id}/remove")
    public RedirectView deleteAuthor(@PathVariable("id") String authorId) {
        if (authorId != null) {
            authorService.deleteAuthor(Long.parseLong(authorId));
        }
        return new RedirectView(AuthorController.URL_AUTHOR_LIST);
    }

}
