package com.laura.libreria.repositories;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class SampleDataLoader {
    private BookRepository bookRepository;

    public SampleDataLoader(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @PostConstruct
    public void loadSampleData(){
        bookRepository.saveAll(List.of(
            new Book("Una habitación propia", "Virginia Woolf", "Ensayo"),
            new Book("Un mago de Terramar", "Ursula K. Leguin", "Fantasia"),
            new Book("Los desposeídos", "Ursula K. Leguin", "Fantasia"),
            new Book("Las flores del mal", "Charles Baudelaire", "Poesia"),
            new Book ("La comunidad del anillo", "J.R.R. Tolkien", "Fantasía"),
            new Book ("Learn Software Development", "Mary Poppendieck", "Software"),
            new Book("Object Design", "Rebbeca Wirfs-Brock", "Software")
        ));
    }
    
}
