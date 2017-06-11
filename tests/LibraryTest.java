import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;



public class LibraryTest {

	private Library sut;

	@Before
	public void initializeSUT() {
		sut = new Library();
	}

	@Test
	public void shouldntFindReader() {
		assertTrue(null == sut.findReader(1));
		assertTrue(null == sut.findReader("kowlaski"));
	}
	
	@Test
	public void shouldFindReader() {
		Reader reader = new Reader("jan", "kowalski");
		sut.createReader(reader);
		assertTrue(reader == sut.findReader(1));
		assertTrue(reader == sut.findReader("kowalski"));
	}
	
	@Test
	public void shouldntFindVolume() {
		assertTrue(null == sut.findVolume("B1"));
	}
	
	@Test
	public void shouldFindVolume() {
		Volume volume = new Book("pan tadeusz", "dziady");
		sut.createVolume(volume);
		assertTrue(volume == sut.findVolume("B1"));
	}


}
