import org.joda.time.DateTime;
import org.joda.time.Duration;


public class Book extends Volume {
	private static int count = 0;
	private int id;
	private String author;
	private String title;

	public Book(String author, String title){
		setAvailable(true);
		count++;
		setId(count);
		this.author = author;
		this.title = title;
	}
	
	public static void setCount(int count){
		Book.count = count;
	}
	
	@Override
	public String getUniqueId() {
		return "B"+id;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	
	public String getName() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String checkAvailability(){
		if(getAvailable()) return "Dostępna";
		else{
			DateTime now = new DateTime();
			Duration duration = new Duration(now, getDate().plusMonths(2));
			if(duration.getStandardDays() > 0) return "Powinna być dostępna za: "+ String.valueOf(duration.getStandardDays()) + "dni.";
			else return "Ktoś przetrzymuje za długo!";
		}
	}
	
	@Override
	public int timeExceeded(){
		DateTime now = new DateTime();
		Duration duration = new Duration(now, getDate().plusMonths(2));
		if (duration.getStandardDays() > 2){
			return (int) duration.getStandardDays();
		}
		else
			return 0;
	}
	

	@Override
	public String toString() {
		return "Książka [autor=" + author + ", tytuł=" + title + "]";
	}
	
}
