package java_sqlite_3005;

public class Item {
	
	private int id;
	private String name;
	private String plainText;
	private String description;
	private String group;
	
	public Item(int id, String name, String plaintext, String description, String group){
		this.id = id;
		this.name = name;
		this.plainText = plaintext;
		this.description = description;
		this.group = group;
	}
	
	public int getID(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public String getPlainText(){
		return plainText;
	}
	
}
