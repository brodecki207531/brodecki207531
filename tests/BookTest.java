import static org.junit.Assert.*;
import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;


public class BookTest {
	
	private Book sut;

	@Before
	public void initializeSUT() {
		sut = new Book("Julian Tuwim", "Zemsta");
	}
	
	@Test
	public void shouldBeAvailableIn30Days() throws Exception {
		//given
		DateTime now = new DateTime();
		sut.setAvailable(false);
		sut.setDate(now.minusMonths(1));
		//when
		String days = sut.checkAvailability();
		//then
		assertTrue(days.equals("Powinna być dostępna za: 29dni."));
	}
	
	

	@Test
	public void shouldBeAvailable() throws Exception {
		//given
		sut.setAvailable(true);
		//when
		String days = sut.checkAvailability();
		//then
		assertTrue(days.equals("Dostępna"));
	}

	@Test
	public void shouldIncrementId() throws Exception {
		Book.setCount(0);
		//given
		Book book = new Book("Autor", "Tytuł");
		//then
		assertTrue(book.getUniqueId().equals("B1"));
	}

}
