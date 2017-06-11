import org.joda.time.*;


public abstract class Volume implements java.io.Serializable {
	private Boolean available;
	private DateTime date;
	private Reader reader;
	
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public Boolean getAvailable() {
		return available;
	}
	public void setAvailable(Boolean available) {
		this.available = available;
	}
	public DateTime getDate() {
		return date;
	}
	public void setDate(DateTime date) {
		this.date = date;
	}
	
	public String checkAvailability(){
		if(available) return "Dostępna";
		else{
			DateTime now = new DateTime();
			Duration duration = new Duration(now, date.plusMonths(4));
			if(duration.getStandardDays() > 0) return "Powinna być dostępna za: "+ String.valueOf(duration.getStandardDays()) + "dni.";
			else return "Ktoś przetrzymuje za długo!";
		}
	}
	
	public void loanTo(Reader reader){
		this.reader = reader;
		available = false;
		date = new DateTime();
	}
	
	public void removeLoan(){
		this.reader = null;
		available = true;
		date = null;
	}

	public int timeExceeded(){
		DateTime now = new DateTime();
		Duration duration = new Duration(now, getDate().plusMonths(4));
		if (duration.getStandardDays() > 3){
			return (int) duration.getStandardDays();
		}
		else
			return 0;
	}
	
	public String getUniqueId() {
		return "Brak";
	}
	
	public String getName(){
		return "Brak";
	}
	
	public String getAuthor(){
		return "Brak";
	}
	
	public int getNumber(){
		return 0;
	}
}
