package java_sqlite_3005;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
This code has hard coded values for the database name, table names and names of the columns. The code is consistent with the
following sqlite3 database schema:

sqlite> .schema
CREATE TABLE bookcodes (
code text primary key,
title text not null);

CREATE TABLE songs(
id integer primary key,
bookcode text,
page int,
title text);

Note some tables might have additional columns what that should not affect the code.

*/


public class javaWithSQliteMain {

    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Java With SQLite example");
		GUI frame = null;
		

		//Connect to database
        try {
        	
        	//direct java to the sqlite-jdbc driver jar code
        	// load the sqlite-JDBC driver using the current class loader
			Class.forName("org.sqlite.JDBC");
			
			//create connection to a database in the project home directory.
			//if the database does not exist one will be created in the home directory
		    
			//Connection conn = DriverManager.getConnection("jdbc:sqlite:mytest.db");
			
			//HARD CODED DATABASE NAME:
			Connection database = DriverManager.getConnection("jdbc:sqlite:db_LOLBuilds");
		       //create a statement object which will be used to relay a
		       //sql query to the database
		     Statement stat = database.createStatement();

		    /*
		     * SQLite supports in-memory databases, which does not create any database files
		     * To use in memory database in your Java code, get the database connection as follows:
		     *
		     * Connection connection = DriverManager.getConnection("jdbc:sqlite::memory:");
		     * 
		    */
			
		   
                //Query database for initial contents for GUI

	            String sqlQueryString = "select * from tennisPlayer order by level asc;";
	            System.out.println("");
	            System.out.println(sqlQueryString);

	            ArrayList<FakeBook> books = new ArrayList<FakeBook>();

		        ResultSet rs = stat.executeQuery(sqlQueryString);
		        while (rs.next()) {
		            //System.out.print("code: " + rs.getString("code"));
		            //System.out.println(" title = " + rs.getString("title"));
		            FakeBook fakebook = new FakeBook(rs.getString("location"), rs.getString("name"));
		            books.add(fakebook);
		        }
		        rs.close(); //close the query result table
		        


	            sqlQueryString = "select * from tennisPlayer;";
		        rs = stat.executeQuery(sqlQueryString);
	            System.out.println("");
	            System.out.println(sqlQueryString);
	            
	            ArrayList<FakeBookSong> songSearchResults = new ArrayList<FakeBookSong>();

		        int DISPLAY_LIMIT = 100;
		        int count = 0;
		        while (rs.next() && count < DISPLAY_LIMIT){
		            //System.out.print("id: " + rs.getString("id"));
		            //System.out.print("book: " + rs.getString("bookcode"));
		            //System.out.print(" page: " + rs.getInt("page"));
		            //System.out.println(" song: " + rs.getString("title"));
		            
		            FakeBookSong song = new FakeBookSong(
		            		rs.getInt("id"),
		            		rs.getInt("level"),
		            		rs.getString("location"),
		            		rs.getString("name")
		            		);
		            songSearchResults.add(song);
	            count++;
		        }
		        rs.close(); //close the query result table
		        
		        FakeBookSong[] songArray = new FakeBookSong[1]; //just to establish array type
		        songArray =  songSearchResults.toArray(songArray);
				
		        //Create GUI with knowledge of database and initial query content
		        frame =  new GUI("3005 Fake Book Index", database, stat, books, songSearchResults); //create GUI frame with knowledge of the database
		        
		        //Leave it to GUI to close database
		        //conn.close(); //close connection to database			
												
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

        //make GUI visible
		frame.setVisible(true);




	}

}
