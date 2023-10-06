package com.laura.libreria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.laura.libreria.repositories.Book;
import com.laura.libreria.repositories.BookRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import static org.hamcrest.Matchers.hasItem;
import static org.mockito.ArgumentMatchers.contains;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.MatcherAssert.assertThat;



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

	@Test
	void returnsAFormToAddNewBooks() throws Exception{
		mockMvc.perform(get("/books/new"))
				.andExpect(status().isOk())
				.andExpect(view().name("/books/new"));
	}

	@Test
	void allowsToCreateANewBook() throws Exception {
		mockMvc.perform(post("/books/new")
		.param("title", "Harry Potter y la piedra filosofal")
		.param("author", "J.K. Rowling")
		.param("category", "Fantasia")
		)
	  .andExpect(status().is3xxRedirection())
	  .andExpect(MockMvcResultMatchers.redirectedUrl("/books"));

        List<Book> existingBooks = (List<Book>) bookRepository.findAll();
        assertEquals(1, existingBooks.size());

        Book createdBook = existingBooks.get(0);
        assertEquals("Harry Potter y la piedra filosofal", createdBook.getTitle());
        assertEquals("J.K. Rowling", createdBook.getAuthor());
        assertEquals("Fantasia", createdBook.getCategory());
    } 
}





