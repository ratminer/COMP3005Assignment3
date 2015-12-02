package java_sqlite_3005;

import java.sql.*;
import java.util.ArrayList;

public class SQLite {
	
	private ArrayList<Champion> champions;
	private ArrayList<Item> items;
	
	public SQLite(){
		try{
			Class.forName("org.sqlite.JDBC");
			Connection database = DriverManager.getConnection("jdbc:sqlite:db_LOLBuilds");
			Statement stat = database.createStatement();
			
			String sql = "select * from champion order by id asc;";
			System.out.println(sql);
			
			champions = new ArrayList<>();
			items = new ArrayList<>();
			
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()){
				Champion champion = new Champion(rs.getInt("id"),
						rs.getString("name"), rs.getString("title"), rs.getString("key"));
				champions.add(champion);
			}
			rs.close();
			
			for(Champion c : champions){
				System.out.println(c.getName());
			}
			
			sql = "select * from item";
			System.out.println("\n" + sql);
			rs = stat.executeQuery(sql);
			
			while(rs.next()){
				Item item = new Item(rs.getInt("id"),
						rs.getString("name"), rs.getString("description"), rs.getString("plaintext"), rs.getString("group"));
				items.add(item);
			}
			
			for(Item i : items){
				System.out.println(i.getName());
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		SQLite sql = new SQLite();
	}
}
