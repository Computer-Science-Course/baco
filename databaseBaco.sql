drop schema `database`;

create database if not exists `database`;

use `database`;

create table evento(
    id int primary key not null auto_increment,
    nome varchar(40) not null,
    titulo varchar(30) not null,
    descricao varchar(100) not null,
    data_inicio date not null,
    data_termino date not null
);

create table participante(
    id int primary key not null auto_increment,
    nome varchar(40) not null,
    numero_documento varchar(16),
    tipo_documento varchar(15)
);

create table atividade(
    id int not null primary key auto_increment,
    titulo varchar(40) not null,
    descricao varchar(100) not null,
    tipo varchar(16),
    data_inicio dateTime not null,
    data_termino dateTime not null,
    duracao double not null,
    nome_responsavel varchar(40),
    id_evento int not null,
    FOREIGN KEY (id_evento) REFERENCES evento (id)
);

create table gestor(
    id int primary key auto_increment not null,
    numero_documento varchar(16) not null,
    tipo_documento varchar(16) not null,
    nome_completo varchar(40) not null,
    senha varchar(16) not null,
    isAdm bool,
    ultimo_acesso datetime
);

create table adm(
    id int primary key auto_increment not null,
    numero_documento varchar(16) not null,
    tipo_documento varchar(16) not null,
    nome_completo varchar(40) not null,
    senha varchar(16) not null,
    isAdm bool,
    ultimo_acesso datetime
);

create table participante_atividade(
    id_participante int not null,
    id_atividade int not null,
    FOREIGN KEY (id_participante) REFERENCES participante (id),
    FOREIGN KEY (id_atividade) REFERENCES atividade (id),
    PRIMARY KEY(id_participante, id_atividade)
);