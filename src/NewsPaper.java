import org.joda.time.DateTime;
import org.joda.time.Duration;


public class NewsPaper extends Volume {
	private String name;
	private int number;
	private int id;
	private static int count = 0;
	
	public NewsPaper(String name, int number){
		setAvailable(true);
		count++;
		setId(count);
		this.name = name;
		this.number = number;
	}
	
	public static void setCount(int count){
		NewsPaper.count = count;
	}

	public String getUniqueId() {
		return "N"+id;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String checkAvailability(){
		if(getAvailable()) return "Dostępna";
		else{
			DateTime now = new DateTime();
			Duration duration = new Duration(now, getDate().plusDays(1));
			if(duration.getStandardDays() > 0) return "Powinna być dostępna za: "+ String.valueOf(duration.getStandardDays()) + "dni.";
			else return "Ktoś przetrzymuje za długo!";
		}
	}
	
	@Override
	public int timeExceeded(){
		DateTime now = new DateTime();
		Duration duration = new Duration(now, getDate().plusDays(1));
		if (duration.getStandardDays() > 2){
			return (int) duration.getStandardDays();
		}
		else
			return 0;
	}
	
}
