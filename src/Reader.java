import java.util.ArrayList;
import java.util.List;
import org.joda.time.*;

public class Reader implements java.io.Serializable {
	private static int count = 0;
	private String name;
	private String surname;
	private int id;
	private float penalty;
	private List<Volume> loaned;
	
	public Reader(String name, String surname){
		this.name = name;
		this.surname = surname;
		count++;
		this.id = count;
		this.penalty = 0;
		List<Volume> volumeList = new ArrayList<Volume>(); 
		setLoaned(volumeList);
	}
	
	public static void setCount(int count){
		Reader.count = count;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getPenalty() {
		return penalty;
	}
	public void setPenalty(float penalty) {
		this.penalty = penalty;
	}
	public List<Volume> getLoaned() {
		return loaned;
	}
	public void setLoaned(List<Volume> loaned) {
		this.loaned = loaned;
	}
	
	public void addLoan(Volume vol) throws TooManyLoansException{
		if(loaned.size() > 2){
			throw new TooManyLoansException();
		}
		loaned.add(vol);
	}
	
	public void removeLoan(Volume vol){
		loaned.remove(vol);
	}
	
	public String toString(){
		return "Imie: " + name + ", Nazwisko: " + surname + "Kara: " + penalty;
	}
	
	public int checkPenalties(){
		int penalty = 0;
		for(Volume volume : loaned){
			penalty += volume.timeExceeded();
		}
		this.penalty = penalty;
		return penalty;
	}

}
