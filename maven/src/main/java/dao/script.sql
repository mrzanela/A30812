create database marketplace;

create table
    clientes(
        id int not null auto_increment primary key,
        nome varchar(50),
        cpf char(14),
        endereco varchar(100),
        email varchar(80),
        senha varchar(30)
    );

create table 
vendedores(
    id int not null auto_increment primary key,
    nome varchar(50),
    cnpj char(18),
    endereco varchar(100),
    email varchar(80),
    senha varchar(30)
); 

create table
produtos(
    id int not null auto_increment primary key,
    nome varchar(80),
    preco decimal(18,2),
    qtd int
);