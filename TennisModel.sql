--SQL Fake Book Song Data


--CREATE DATABASE TABLES
--=======================
DROP TABLE IF EXISTS tennisPlayer;
create table if not exists tennisPlayer(
      id integer primary key not null, 
      name text NOT NULL, 
      location text NOT NULL,  
      level int
      );

DROP TABLE IF EXISTS coach;
create table if not exists coach(
	  id integer primary key not null,
	  name text NOT NULL,
	  location text NOT NULL,
	  schedule text NOT NULL,
	  years int NOT NULL
	  );
--INSERT DATA
--===============

begin transaction;

--Insert songs
--RBFB
insert into tennisPlayer (name, location, level) values ('Josh','Richmond Hill',9);
insert into tennisPlayer (name, location, level) values ('Zing', 'Ottawa','5');
insert into coach (name, location, schedule, years) values ('Ahmad','Ottawa','Friday',7);
insert into coach (name, location, schedule, years) values ('Shafiq','Ottawa','Monday',8);

end transaction; 