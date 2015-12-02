package java_sqlite_3005;

public class Champion {
	
	private int id;
	private String name;
	private String title;
	private String key;
	
	public Champion(int id, String name, String title, String key){
		this.id = id;
		this.name = name;
		this.title = title;
		this.key = key;
	}
	
	public String getName(){
		return name;
	}
	
	public String getTitle(){
		return title;
	}
	
	public String getKey(){
		return key;
	}
}
