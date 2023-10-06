package com.laura.libreria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.laura.libreria.repositories.Book;
import com.laura.libreria.repositories.BookRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@SpringBootTest
@AutoConfigureMockMvc
class ApplicationTests {

	@BeforeEach
	void setUp() {
    bookRepository.deleteAll();
	}

	@Autowired
	MockMvc mockMvc;

	@Test
	void loadsTheHomePage() throws Exception {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect(view().name("home"));
	}

	@Autowired
	BookRepository bookRepository;

	@Test
	void returnsTheExistingBooks() throws Exception{
		Book book = bookRepository.save(new Book("Harry Potter y la Piedra Filosofal", "J.K. Rowling", "Fantasia"));

		mockMvc.perform(get("/books"))
			.andExpect(status().isOk())
			.andExpect(view().name("books/all"))
			.andExpect(model().attribute("books", hasItem(book)));
	}
}
