package com.stephen.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.bookclub.models.Book;
import com.stephen.bookclub.repositories.BookRepository;

@Service
public class BookServ {
	
	@Autowired
	private BookRepository bookRepo;
	
	// returns all books
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	
	// creates a book
	public Book createBook(Book book) {
		return bookRepo.save(book);
	}
	
	// retrieves a book by Id
	public Book findBookId(Long id) {
		Optional<Book> optBook = bookRepo.findById(id);
		if(optBook.isPresent()) {
			return optBook.get();
		}else {
			return null;
		}
	}
	
	// update a book
	public Book updateBook(Book book) {
		return bookRepo.save(book);
	}
	
	// delete a book
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
}
