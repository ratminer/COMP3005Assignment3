package java_sqlite_3005;

public class FakeBook {
	/*
	 * This class represents a fake book
	 */
	
	private String bookcode; //string code for fake book
	private String title; //title of song
	
	public FakeBook(String aBookCode, String aBookTitle){
		bookcode = aBookCode;
		title = aBookTitle;
	}
	
	public String getBookCode() {return bookcode;}
	public String getTitle() {return title;}
	
	public String toString(){
		return "" + bookcode + " " +  title;
	}

}
