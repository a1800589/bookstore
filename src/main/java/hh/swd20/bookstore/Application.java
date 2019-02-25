package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;



@SpringBootApplication
public class Application {

	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository1) {
		return (args) -> {
			log.info("save a couple of boks");
			bookRepository1.save(new Book(null, "Kalevala",  "Makkonen Sami", 2019, "9789511335696", 21.95, null));
			bookRepository1.save(new Book(null, "Fingerpori 12", "Jarla Pertti", 2019, "9789520118990", 17.95, null));

			log.info("fetch all books");
			for (Book book : bookRepository1.findAll()) {
				log.info(book.toString());
			}
		};
	}

}
