CREATE TABLE proposta (
	id bigint not null auto_increment,
	nota bigint,
	preco decimal not null,
	data_cadastro varchar(11) not null,
	classificacao int,
	licitacao_id bigint not null,
	
	primary key (id)
);

ALTER TABLE proposta ADD CONSTRAINT fk_proposta_licitacao
FOREIGN KEY (licitacao_id) REFERENCES licitacao(id);
