drop database if exists fansite_favorites;
create database fansite_favorites;
use fansite_favorites;
 
 create table favorites (
 position int primary key,
 picked bit
 );
 
 