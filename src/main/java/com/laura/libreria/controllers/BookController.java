package com.laura.libreria.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.laura.libreria.repositories.Book;
import com.laura.libreria.repositories.BookRepository;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class BookController {
    private BookRepository bookRepository;

    @Autowired
    public BookController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    String listBooks(Model model){
        List<Book> books = (List<Book>) bookRepository.findAll();
        model.addAttribute("title", "Book list");
        model.addAttribute("books", books);
        return "books/all";
    }

    @GetMapping("/books/new")
    String getForm(Model model){
        Book book = new Book();
        model.addAttribute("book", book);
        return "/books/new";
    }

    @PostMapping("/books/new")
    String addBook(@ModelAttribute Book book){
        bookRepository.save(book);
        return ("redirect:/books");
    }
    
}
