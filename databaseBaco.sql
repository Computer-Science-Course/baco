drop schema `database`;

create database if not exists `database`;

use `database`;

create table evento(
    id int primary key not null auto_increment,
    nome varchar(40) not null,
    titulo varchar(256) not null,
    descricao varchar(256) not null,
    data_inicio datetime not null,
    data_termino datetime not null
);

create table participante(
    id int primary key not null auto_increment,
    nome varchar(40) not null,
    numero_documento varchar(16),
    tipo_documento varchar(15)
);

create table atividade(
    id int not null primary key auto_increment,
    titulo_atividade varchar(40) not null,
    descricao_atividade varchar(100) not null,
    tipo varchar(16),
    data_inicio_atividade dateTime not null,
    data_termino_atividade dateTime not null,
    duracao double not null,
    nome_responsavel varchar(40),
    id_evento int not null,
    FOREIGN KEY (id_evento) REFERENCES evento (id)
);

create table usuario(
    id int primary key auto_increment not null,
    numero_documento varchar(16) not null unique,
    tipo_documento varchar(16) not null,
    nome_completo varchar(40) not null,
    senha varchar(16) not null,
    isAdm bool,
    ultimo_acesso datetime
);

create table inscricao(
    id_participante int not null,
    id_atividade int not null,
    checkin boolean,
    FOREIGN KEY (id_participante) REFERENCES participante (id),
    FOREIGN KEY (id_atividade) REFERENCES atividade (id),
    PRIMARY KEY(id_participante, id_atividade)
);

INSERT INTO usuario
	(numero_documento, tipo_documento, nome_completo, senha, isAdm, ultimo_acesso)
VALUES
    ("192919", "MATRICULA", "Mateus Santos", "1234", true, null),
    ("192921", "MATRICULA", "Sayury Kato", "1234", true, null),
    ("192920", "MATRICULA", "Newdon Ataide", "1234", true, null),
    ("192918", "MATRICULA", "Ítala Vitória", "1234", true, null),
    ("123456", "RG", "Grande Gestor", "1234", false, null);
    