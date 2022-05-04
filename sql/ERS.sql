--ALTER SCHEMA "ERS" RENAME TO ers;

drop table reimb;
drop table users;
drop table roles;
drop table status;
drop table reimb_type;

create table roles(
	role_id serial primary key,
	role_name text
);
create table users(
	user_id serial primary key,
	username text unique,
	pass text,
	firstname text,
	lastname text,
	email text unique,
	role_id_fk int references roles (role_id)
);




create table status(
	status_id serial primary key,
	status_name text
);
create table reimb_type(
	type_id serial primary key,
	type_name text
);
create table reimb(
	reimb_id serial primary key,
	amount float,
	author int references users(user_id),
	resolver int references users(user_id),
	status_fk int references status(status_id),
	type_fk int references reimb_type(type_id)
);

insert into roles(role_name)
values ('employee'),
		('finance manager');
		
insert into status(status_name)
values ('pending'),
		('approved'),
		('denied');

insert into reimb_type(type_name)
values ('lodging'),
		('travel'),
		('food'),
		('other');
	
insert into users(username,pass,firstname,lastname,email,role_id_fk)
values ('username','password','tom','smith','tom@email',1),
		('u','p','alex','proctor','taproctor00@gmail.com',2);

insert into reimb(amount, author, status_fk, type_fk)
values (500.23,1,1,2),
		(25.12,1,2,3),
		(150,2,1,1);

select * from users;
select * from reimb where author != 1;
select * from users where username = user;