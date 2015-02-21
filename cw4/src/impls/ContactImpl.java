package impls;

import iinterfaces.Contact;

public class ContactImpl implements Contact{

	// Fields
	// ------
	
	public int id = 1234;
	private String name;
	private String notes;
	
	// Constructor
	// -----------
	
	public ContactImpl(String name, String notes){
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
