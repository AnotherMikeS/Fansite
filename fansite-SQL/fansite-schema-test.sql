drop database if exists fansite_favorites_test;
create database fansite_favorites_test;
use fansite_favorites_test;

 create table favorites (
 position int primary key not null,
 `name` varchar(50) not null
 );

delimiter //
create procedure set_known_good_state()
begin
	truncate table favorites;
    insert into favorites
		(position, `name`)
	values
		(1, 'Mario'),
        (2, 'Greggio'),
        (3, 'Princess Michael'),
        (4, 'Bowser Shelley');
end //

delimiter ;

