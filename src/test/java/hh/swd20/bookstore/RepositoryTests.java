package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;
import hh.swd20.bookstore.domain.UserRepository;



@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTests {

	 @Autowired
	    private BookRepository bRepository;
	 
	 @Autowired
	    private UserRepository uRepository;
	 
	 @Autowired
	    private CategoryRepository cRepository;

	    @Test
	    public void findByCategoryName() {
	        List<Category> categories = cRepository.findByName("Muu");
	        
	        assertThat(categories).isNotNull();
	        assertThat(categories).hasSize(1);
	       
	    }
	    
	    @Test
	    public void createNewBook() {
	    	Book book = new Book(null, "New book", "New author", 2000, "123456789-abc", 20.50, new Category("Muu"));
	    	bRepository.save(book);
	    	assertThat(book.getId()).isNotNull();
	    }    
	    
	    @Test
	    public void deleteBook() {
	    	Book book = new Book(null, "New book", "New author", 2000, "123456789-abc", 20.50, new Category("Muu"));
	    	bRepository.save(book);

	    	bRepository.deleteById(book.getId());
	    	book = bRepository.findById(book.getId()).orElse(null);
	    	
	    	assertThat(book).isNull();
	    }
}
