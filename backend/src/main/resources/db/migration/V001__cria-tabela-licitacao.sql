create table licitacao (
	id bigint not null auto_increment,
	descricao text not null,
	classificacao varchar(30) not null,
	
	primary key (id)
);

