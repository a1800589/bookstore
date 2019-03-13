package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;



@SpringBootApplication
public class Application {

	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository1, CategoryRepository cRepository, UserRepository uRepository) {
		return (args) -> {
			log.info("save a couple of boks");
			cRepository.save(new Category("Eepos")); 
			cRepository.save(new Category("Komedia"));
			cRepository.save(new Category("Muu"));
		
			
			
			bookRepository1.save(new Book(null, "Kalevala",  "Makkonen Sami", 2019, "9789511335696", 21.95, cRepository.findByName("Eepos").get(0)));
			bookRepository1.save(new Book(null, "Fingerpori 12", "Jarla Pertti", 2019, "9789520118990", 17.95, cRepository.findByName("Komedia").get(0)));
			
			User user1 = new User("user", "$2a$10$rYD0rD8hxswjSFWFhyuROOz3T2GnnffuOHrBzotPx1k/aGowJGsL2", "USER");
			User user2 = new User("admin", "$2a$10$kK6wHneFME4Mo3Ar9H.NXu/x09MAK6M0C8Zvcxutx/j3YJ7SNM5.y", "ADMIN");
			uRepository.save(user1);
			uRepository.save(user2);

			log.info("fetch all books");
			for (Book book : bookRepository1.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
