package hh.swd20.bookstore;





import static org.assertj.core.api.Assertions.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import hh.swd20.bookstore.web.BookController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private BookController bController;

	@Test
	public void contextLoads() {
		assertThat(bController).isNotNull();
	}
	

}
