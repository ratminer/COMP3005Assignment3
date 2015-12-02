package java_sqlite_3005;


import java.net.*;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import org.json.*;

public class PullData {
	
	private String path = "LOLDB.sql";

	public PullData(){
		String base = "https://global.api.pvp.net/api/lol/static-data/na/v1.2/";
		String key = "&api_key=479c1f0e-396e-41d0-9f04-d178279aaccf";
		
		String champData = base + "champion?champData=" + key;
		String itemData = base + "item?itemListData=" + key;
		
		String champStats = base + "champion?champData=stats" + key;
		String champSpells = base + "champion?champData=spells" + key;
		String itemStats = base + "item?itemListData=stats" + key;
		
		String createTables = "--CREATE DATABASE TABLES"
								+"--======================="
								+"DROP TABLE IF EXISTS champion;"
								+"create table if not exists champion("
								+"      id integer primary key not null," 
								+"      name text NOT NULL, "
								+"      title text NOT NULL, " 
								+"      key text NOT NULL"
								+"      );"
								
								+"DROP TABLE IF EXISTS item;"
								+"create table if not exists item("
								+"	  id integer primary key not null,"
								+"	  name text NOT NULL,"
								+"	  description text NOT NULL,"
								+"	  plaintext text,"
								+"	  'group' text"
								+"	  );"
								+"--INSERT DATA"
								+"--==============="
								
								+"begin transaction;";		
		
		
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(path));
			
			System.out.println(getSpells(champSpells, "spells"));
			
			//out.write(createTables);
			//out.write(gatherData(champData, "champion"));
			//out.write(gatherData(itemData, "item"));
			//out.write(getStats(champStats, "champion_stats"));
			//out.write(getStats(itemStats, "item_stats"));
			//out.write("end transaction");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	public static String gatherData(String url, String table){
		StringBuilder sb = new StringBuilder();
		JSONObject obj = new JSONObject(executeRequest(url));
		JSONObject data = obj.getJSONObject("data");
		String[] names = JSONObject.getNames(data);
		for(String s : names){
			String[] keys = JSONObject.getNames(data.getJSONObject(s));
			ArrayList<String> values = new ArrayList<>();
			for(String k : keys){
				values.add(data.getJSONObject(s).get(k).toString());
			}
			sb.append(insertTable(table, keys, values));
			sb.append('\n');
		}
		return sb.toString();
	}
	
	public static String getStats(String url, String table){
		StringBuilder sb = new StringBuilder();
		JSONObject obj = new JSONObject(executeRequest(url));
		JSONObject data = obj.getJSONObject("data");
		String[] names = JSONObject.getNames(data);
		for(String s : names){
			JSONObject stats = data.getJSONObject(s).getJSONObject("stats");
			String[] keys = JSONObject.getNames(stats);
			ArrayList<String> values = new ArrayList<>();
			if(keys != null){
				for(String k : keys){
					values.add(Double.toString(stats.getDouble(k)));
				}
				sb.append(insertTable(table, keys, values));
				sb.append("\n");
			}
		}
		return sb.toString();
	}
	
	public static String getSpells(String url, String table){
		StringBuilder sb = new StringBuilder();
		JSONObject obj = new JSONObject(executeRequest(url));
		JSONObject data = obj.getJSONObject("data");
		String[] names = JSONObject.getNames(data);
		for(String s : names){
			JSONArray spells = data.getJSONObject(s).getJSONArray("spells");
			for(int i = 0; i < spells.length(); i++){
				String[] keys = JSONObject.getNames(spells);
				ArrayList<String> values = new ArrayList<>();
				if(keys != null){
					for(String k : keys){
						values.add((String) spells.getJSONObject(i).get(k));
					}
					sb.append(insertTable(table, keys, values));
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}
	
	public static String insertTable(String tableName, String[] keys, ArrayList<String> values){
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO " + tableName + "(");
		
		for(String s : keys) {
			insert.append("\"" + s + "\",");
		}
		insert.deleteCharAt(insert.length()-1);
		insert.append(") VALUES (");
		for(String s : values){
			insert.append(" \"");
			insert.append(s);
			insert.append("\",");
		}
		insert.deleteCharAt(insert.length()-1);
		insert.append(");");
		return insert.toString();
	}
	
	public static String executeRequest(String requestURL){
		try {
			URL url = new URL(requestURL);
			URLConnection yc = (URLConnection) url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
			String inputLine;
			StringBuilder sb = new StringBuilder();
			while((inputLine = in.readLine()) != null){
				sb.append(inputLine);
				sb.append('\r');
			}
			return sb.toString();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args){
		PullData p = new PullData();
	}
}
