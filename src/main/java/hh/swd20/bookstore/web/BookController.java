package hh.swd20.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository cRepository; 

	
	 @RequestMapping(value="/login")
	    public String login() {	
	        return "login";
	    }	
	
	@GetMapping("/booklist")
	public String getBooks(Model model) {
		List<Book> books = (List<Book>) bookRepository.findAll();
		model.addAttribute("books", books);
		return "booklist";
	}

	@GetMapping("/addBook")
	public String getNewBookForm(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", cRepository.findAll());
		return "addBook";
	}

	@PostMapping("/addBook")
	public String saveBook(@ModelAttribute Book book) {

		bookRepository.save(book);
		return "redirect:/booklist";
	}

	@GetMapping("/deletebook/{id}")
	@PreAuthorize("hasRole('ADMIN')")
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
	
	 @RequestMapping(value="/books", method = RequestMethod.GET)
	    public @ResponseBody List<Book> bookListRest() {	
	        return (List<Book>) bookRepository.findAll();
	    }    
	   @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
	    	return bookRepository.findById(bookId);
	    }       
	    
	 
	
}
