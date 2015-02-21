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
	
	public void setNotes(String n){
		notes = n;
	}
	
	
}
