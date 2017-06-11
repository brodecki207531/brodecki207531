import org.joda.time.DateTime;
import org.joda.time.Duration;


public class Magazine extends Volume {
	private String name;
	private int number;
	private int id;
	private static int count = 0;

	public String getUniqueId() {
		return "M"+id;
	}
	
	public Magazine(String name, int number){
		setAvailable(true);
		count++;
		setId(count);
		this.name = name;
		this.number = number;
	}
	
	public static void setCount(int count){
		Magazine.count = count;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	private enum Type {weekly, monthly}
	
	@Override
	public String checkAvailability(){
		if(getAvailable()) return "Dostępna";
		else{
			DateTime now = new DateTime();
			Duration duration = new Duration(now, getDate().plusDays(5));
			if(duration.getStandardDays() > 0) return "Powinna być dostępna za: "+ String.valueOf(duration.getStandardDays()) + "dni.";
			else return "Ktoś przetrzymuje za długo!";
		}
	}
	
	@Override
	public int timeExceeded(){
		DateTime now = new DateTime();
		Duration duration = new Duration(now, getDate().plusDays(5));
		if (duration.getStandardDays() > 2){
			return (int) duration.getStandardDays();
		}
		else
			return 0;
	}
	

	@Override
	public String toString() {
		return "Gazeta [numer=" + number + ", nazwa=" + name + "]";
	}
}
