import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.*;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.joda.time.chrono.ISOChronology;

public class Library {
	private String name;
	private List<Volume> volumes;
	private List<Reader> readers;
	
	public Library(){
		name = "Biblioteka PŁ";
		List<Volume> volumeList = new ArrayList<Volume>(); 
		setVolumes(volumeList);
		
		List<Reader> readerList = new ArrayList<Reader>(); 
		setReaders(readerList);
		
	}
	
	public void save() {
		try{
		Writer writerReaders = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./readers.xml"), "utf-8"));
		XStream xstream = new XStream();
		String xml = xstream.toXML(readers);
		writerReaders.write(xml);
		writerReaders.close();

		Writer writerVolumes = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./volumes.xml"), "utf-8"));
		xstream = new XStream();
		xml = xstream.toXML(volumes);
		writerVolumes.write(xml);
		writerVolumes.close();

		}catch(Exception e){}
	}
	
	public void load(){
		try{
	       FileInputStream fileIn = new FileInputStream("readers.xml");
	       XStream xstream = new XStream(new DomDriver());

	       xstream.alias("Reader", Reader.class);
	       xstream.alias("Book", Book.class);
	       xstream.alias("NewsPaper", NewsPaper.class);
	       xstream.alias("Magazine", Magazine.class);
//	       xstream.addImplicitCollection(Reader.class, "reader");
//	       xstream.addImplicitCollection(NewsPaper.class, "newspaper");
//	       xstream.addImplicitCollection(Magazine.class, "magazine");
	     
	       List<Reader> readerList = (List<Reader>) xstream.fromXML(fileIn);
	       for(Reader reader : readerList){
	    	   createReader(reader);
	       }
	       Reader.setCount(readerList.size());
	       fileIn.close();
	       
	       fileIn = new FileInputStream("volumes.xml");
	       List<Volume> volumeList = (List<Volume>) xstream.fromXML(fileIn);
	       int bookCount = 0;
	       int magazineCount = 0;
	       for(Volume volume : volumeList){
	    	   createVolume(volume);
	    	   if(volume.getClass() == Book.class) { bookCount++; }
	    	   else if(volume.getClass() == Magazine.class) magazineCount++;
	       }
	       Book.setCount(bookCount);
	       Magazine.setCount(magazineCount);
	       NewsPaper.setCount(volumeList.size() - bookCount - magazineCount);
	       fileIn.close();
	       
	      }catch(Exception e){
	    	  System.out.println("error");
	    	  System.out.println(e);
	      }
	}
	

	public List<Volume> getVolumes() {
		return volumes;
	}

	public void setVolumes(List<Volume> volumes) {
		this.volumes = volumes;
	}

	public List<Reader> getReaders() {
		return readers;
	}

	public void setReaders(List<Reader> readers) {
		this.readers = readers;
	}

	public boolean addVolumeToReader(Volume volume, Reader reader){
		if(volume.getAvailable() == false) return false;
		try{
			reader.addLoan(volume);
			volume.loanTo(reader);
			return true;
		}catch(TooManyLoansException e){
			return false;
		}
	}
	
	public boolean removeVolumeFromOwner(Volume volume){
		try{
			Reader reader = volume.getReader();
			reader.removeLoan(volume);
			volume.removeLoan();
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public Volume findVolume(String id){
		for(Volume volume : volumes){
			if(volume.getUniqueId().equals(id)){
				return volume;
			}
		}
		return null;
	}
	
	public Reader findReader(int id){
		for(Reader reader : readers){
			if(reader.getId() == id){
				return reader;
			}
		}
		return null;
	}
	
	public Reader findReader(String surname){
		for(Reader reader : readers){
			if(reader.getSurname().equals(surname)){
				return reader;
			}
		}
		return null;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void createReader(Reader reader){
		readers.add(reader);
	}
	
	public void createVolume(Volume volume){
		volumes.add(volume);
	}

}
