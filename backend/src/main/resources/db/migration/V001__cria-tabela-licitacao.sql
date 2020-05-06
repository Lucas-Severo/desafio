create table licitacao (
	id bigint not null auto_increment,
	descricao text not null,
	status varchar(30) not null,
	
	primary key (id)
);

