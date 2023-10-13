use Newservlet_jsp
go

create table roles(
	id bigint not null primary key identity(1,1),
	name varchar(255) not null,
	code varchar(255) not null,
	createddate datetime null,
	modifieddate datetime null,
	createdby varchar(255) null,
	modifiedby varchar(255) null
)

create table users(
	id bigint not null primary key identity(1,1),
	username varchar(255) not null,
	password varchar(255) not null,
	fullname varchar(255) null,
	status int not null,
	roleid bigint not null,
	createddate datetime null,
	modifieddate datetime null,
	createdby varchar(255) null,
	modifiedby varchar(255) null
)

alter table users add constraint fk_user_role foreign key (roleid) references roles(id)

create table news(
	id bigint not null primary key identity(1,1),
	title varchar(255) null,
	thumbnail varchar(255) null,
	shortdescription text null,
	content text null,
	categoryid bigint not null,
	createddate datetime null,
	modifieddate datetime null,
	createdby varchar(255) null,
	modifiedby varchar(255) null
)

create table category(
	id bigint not null primary key identity(1,1),
	name varchar(255) not null,
	code varchar(255) not null,
	createddate datetime null,
	modifieddate datetime null,
	createdby varchar(255) null,
	modifiedby varchar(255) null
)

alter table news add constraint fk_new_category foreign key(categoryid) references category(id)

create table comment(
	id bigint not null primary key identity(1,1),
	content text not null,
	userid bigint not null,
	newsid bigint not null,
	createddate datetime null,
	modifieddate datetime null,
	createdby varchar(255) null,
	modifiedby varchar(255) null
)

alter table comment add constraint fk_comment_user foreign key(userid) references users(id)
alter table comment add constraint fk_comment_news foreign key(newsid) references news(id)