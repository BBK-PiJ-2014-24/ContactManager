package impls;

import iinterfaces.Contact;

public class ContactImpl implements Contact{

	// Fields
	// ------
	
	private int id;
	private String name;
	private String notes;
	
	// Constructor
	// -----------
	
	public ContactImpl(String name, String notes){
		id = 1234;
		this.name = name;
		this.notes = notes;
	}
	
	public ContactImpl(int id, String name, String notes){
		this.id = id;
		this.name = name;
		this.notes = notes;
	}
	
	// Getter/Setters
	// --------------
	
	public String getName(){
		return name;
	}
	
	public void setName(String newName){ 
		name = newName;
	}
	
	public String getNotes(){
		return notes;
	}
	
	public void addNotes(String n){
		notes = n;
	}
	
	public int getId(){
		return id;
	}
	
}
