package java_sqlite_3005;

public class FakeBookSong {
	/*
	 * This class represents a song that appears in a fake book database
	 */
	
	private int id; //database table primary key id
	private int level; //string code for fake book
	private String location; //page number of song in bookcode fakebook
	private String name; //title of song
	
	public FakeBookSong(int databaseKey, int level, String location, String name){
		id = databaseKey;
		this.level = level;
		this.location = location;
		this.name = name;
	}
	
	public int getID() {return id;}
	public int getLevel() {return level;}
	public String getLocation() {return location;}
	public String getName() {return name;}
	
	public void setTitle(String aName) {name = aName;}
	public void setLevel(int aLevel) {level = aLevel;}
	public void setLocation(String aLocation) {location = aLocation;}
	
	public String toString(){
		
		String keyIndent = "";
		int max_key_digits = 6;
		for(int i=0; i< max_key_digits-(""+id).length(); i++ ) keyIndent = keyIndent + " ";
		
		String pageIndent = "";
		int max_page_digits = 5;
		for(int i=0; i< max_page_digits-(""+location).length(); i++ ) pageIndent = pageIndent + " ";
		
		return "" + id + keyIndent + level + "   " + location + pageIndent + name;
	}

}
