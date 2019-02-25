package hh.swd20.bookstore.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@GetMapping("/booklist")
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}

	@GetMapping("/addBook")
	public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book());
		return "addBook";
	}

	@PostMapping("/addBook")
	public String saveBook(@ModelAttribute Book book) {

		bookRepository.save(book);
		return "redirect:/booklist";
	}

	@GetMapping("/deletebook/{id}")
	public String deleteBook(@PathVariable("id") Long bookId) {

		bookRepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@GetMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") Long bookId, Model model) {

		model.addAttribute("book", bookRepository.findById(bookId));
		return "editBook";
	}

	@PostMapping("/editBook")
	public String editSaveBook(@ModelAttribute Book book) {
		
		bookRepository.save(book);
		return "redirect:/booklist";
	}
	
}
