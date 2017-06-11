import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import org.junit.Assert.*;
import org.junit.Test;
import org.assertj.core.api.Condition;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.eq;


public class ReaderTest {

	private Reader sut;

	@Before
	public void initializeSUT() {
		sut = new Reader("Jan", "Kowalski");
	}

	// @Test
	// public void shouldReturnProperListAfterLoan() throws TooManyLoansException {
	// 	//given
	// 	//when
	// 	Book testBook = new Book("autor2","tytuł2");
	// 	sut.addLoan(new Book("autor","tytuł"));
	// 	sut.addLoan(testBook);
	// 	//then
	// 	assertThat(sut.getLoaned())
	// 		.hasSize(2)
	// 		.contains(testBook)
	// 		.doesNotContainNull();
	// }

	@Test(expected=TooManyLoansException.class)
	public void shouldRaiseTooManyLoansException() throws TooManyLoansException {
		//given
		//when
		sut.addLoan(new Book("autor","tytuł"));
		sut.addLoan(new Book("autor2","tytuł2"));
		sut.addLoan(new Book("autor3","tytuł3"));
		sut.addLoan(new Book("autor4","tytuł4"));
		//then
	}

	@Test
	public void shouldReturnProperPenaltyValue() throws TooManyLoansException {
		//given
		Book firstBook = mock(Book.class);
		when(firstBook.timeExceeded())
			.thenReturn(5);

		Book secondBook = mock(Book.class);
		when(secondBook.timeExceeded())
			.thenReturn(3);
		//when
		sut.addLoan(firstBook);
		sut.addLoan(secondBook);
		//then
		assertTrue(sut.checkPenalties() == 8);
	}

	@Test
	public void shouldRemoveLoan() throws TooManyLoansException {
		Book book = new Book("raz", "dwa");
		sut.addLoan(book);
		assertTrue(sut.getLoaned().size() == 1);
		sut.removeLoan(book);
		assertTrue(sut.getLoaned().size() == 0);
	}
}
