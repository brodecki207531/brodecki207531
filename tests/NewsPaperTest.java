import static org.junit.Assert.assertTrue;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;



public class NewsPaperTest {
	
	private NewsPaper sut;

	@Before
	public void initializeSUT() {
		sut = new NewsPaper("Wyborcza", 2);
	}
	
	@Test
	public void shouldBeTimeExceeded() throws Exception {
		//given
		DateTime now = new DateTime();
		sut.setAvailable(false);
		sut.setDate(now.minusMonths(1));
		//when
		String days = sut.checkAvailability();
		//then
		assertTrue(days.equals("Ktoś przetrzymuje za długo!"));
	}
	
	@Test
	public void shouldBeAvailableIn1Day() throws Exception {
		//given
		DateTime now = new DateTime();
		sut.setAvailable(false);
		sut.setDate(now.minusDays(1));
		//when
		String days = sut.checkAvailability();
		//then
		assertTrue(days.equals("Ktoś przetrzymuje za długo!"));
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
		//given
		NewsPaper newsPaper = new NewsPaper("tytuł", 1);
		//then
		//TODO ZMIANA B4, zależne od kolejności wykonywania testów
		assertTrue(newsPaper.getUniqueId().equals("N4"));
	}

}
