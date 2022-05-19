drop database if exists fansiteUser;
create database fansiteUser;
use fansiteUser;

create table fansiteUser (
 fansiteUser_id int primary key auto_increment,
 `name` varchar(50) not null 
 );
 
 create table drivers (
 position int primary key,
 `name` varchar(50) not null,
 skill varchar(50) not null
 );
 
 create table picks (
 pick_ID int primary key auto_increment,
 fansiteUser_id int,
 position int,
 foreign key (fansiteUser_id)
		references fansiteUser(fansiteUser_id),
foreign key (position)
		references drivers(position)
 );
 
 
 