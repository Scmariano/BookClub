package com.stephen.bookclub.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stephen.bookclub.models.Book;
import com.stephen.bookclub.models.User;
import com.stephen.bookclub.services.BookServ;
import com.stephen.bookclub.services.UserServ;

@Controller
public class BookController {
	
	@Autowired BookServ bookServ;
	@Autowired UserServ userServ;
	
	@GetMapping("/books")
	public String dashboard(HttpSession session, Model model) {
		
		if(session.getAttribute("userId") == null) {
			return "redirect:/";
		}else {
			model.addAttribute("books", bookServ.allBooks());
			model.addAttribute("user", userServ.findById((Long)session.getAttribute("userId")));
			return "dashboard.jsp";
		}
	}
	
	@GetMapping("/books/new")
	public String newBook(@ModelAttribute("book") Book book, Model model, HttpSession session) {
		User loggedUser = userServ.findById((Long) session.getAttribute("userId"));
		model.addAttribute("user", loggedUser);
		return "addBook.jsp";
	}
	
	@PostMapping("/books/create")
	public String createBook(@Valid @ModelAttribute("book") Book book, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return "addBook.jsp";
		}else {
			bookServ.createBook(book);
			return "redirect:/books";
		}
	}
	
	@GetMapping("/books/{id}")
	public String showBook(@PathVariable("id") Long id, HttpSession session, Model model) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId == null) {
			return "redirect:/";
		} else {
			User loggedUser = userServ.findById(userId);
			Book showBook = bookServ.findBookId(id);
			model.addAttribute("user", loggedUser);
			model.addAttribute("book", showBook);
			return "bookShow.jsp";
		}
	}
	
	
	@GetMapping("/books/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");
		if(userId==null) {
			return "redirect:/";
		}else {
			User loggedUser = userServ.findById(userId);
			Book showBook = bookServ.findBookId(id);
			model.addAttribute("user", loggedUser);
			model.addAttribute("book", showBook);
			return "editBook.jsp";
		}
	}
	
	
	@PutMapping("/books/{id}/update")
	public String updateBook(@Valid @ModelAttribute("book") Book book, BindingResult result,
			HttpSession session,Model model ) {
		Long userId = (Long) session.getAttribute("userId");
		User loggedUser = userServ.findById(userId);
		if (result.hasErrors()) {
    		return "editBook.jsp";
    	}else {
    		book.setUser(loggedUser);
    		session.setAttribute("userId", loggedUser.getId());
    		bookServ.updateBook(book);
    		return "redirect:/books";
	    }
    	
	}
	
	
	
	@RequestMapping("/books/{id}/delete")
	public String destroy(@PathVariable("id")Long id) {
		bookServ.deleteBook(id);
		return "redirect:/books";
	}

}
