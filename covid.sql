DROP DATABASE IF EXISTS covid;
CREATE DATABASE covid;

USE covid;

CREATE TABLE Usuario(
	id VARCHAR(32) PRIMARY KEY NOT NULL,
	username VARCHAR(255),
	password VARCHAR(255),
	vida INT,
	defensa INT,
	dinero INT,
	tiempo INT
)ENGINE = InnoDB;

CREATE TABLE Objetos(
	id VARCHAR(32) PRIMARY KEY NOT NULL,
	nombre VARCHAR(255),
	coste INT,
	puntoSaludRecuperados INT,
	puntosDefensAdd INT	
)ENGINE = InnoDB;

CREATE TABLE Mapa(
	id VARCHAR(32) PRIMARY KEY NOT NULL,
	nombre VARCHAR(255),
	dato VARCHAR(1000)
)ENGINE = InnoDB;

CREATE TABLE usuarioobjetos(ID VARCHAR(32) UNIQUE, objetoId VARCHAR(32), usuarioId VARCHAR(32));





START TRANSACTION;
USE `covid`;
INSERT INTO Usuario (id,username, password,vida,defensa,dinero,tiempo) VALUES ('u1','Jordi','123',100,10,100,0);
INSERT INTO Objetos (id,nombre,coste,puntoSaludRecuperados,puntosDefensAdd) VALUES ('o1','bolsabasura',0,0,1);
INSERT INTO Objetos (id,nombre,coste,puntoSaludRecuperados,puntosDefensAdd) VALUES ('o2','mascarilla',25,0,20);
INSERT INTO Objetos (id,nombre,coste,puntoSaludRecuperados,puntosDefensAdd) VALUES ('o3','pocion',50,50,0);
INSERT INTO Objetos (id,nombre,coste,puntoSaludRecuperados,puntosDefensAdd) VALUES ('o4','regeneron',100,100,75);
INSERT INTO Objetos (id,nombre,coste,puntoSaludRecuperados,puntosDefensAdd) VALUES ('o5','pcr',20,0,0);
INSERT INTO usuarioobjetos(ID, objetoId, usuarioId) VALUES  ('uo1','o1','u1');
INSERT INTO usuarioobjetos(ID, objetoId, usuarioId) VALUES  ('uo2','o2','u1');
INSERT INTO Mapa (id,nombre,dato) VALUES ('m1','mapa1',"MMMMEEEMMMMIMMMMMEEEMMMMMMMMM;EEEEEEEEIIIIIIEEEEEEEE;ASSSAAAIIIAAAAAAAAAAA");
INSERT INTO Mapa (id,nombre,dato) VALUES ('m2','mapa1',"MMAAAAMMMMSSSSMMMMMMIIIIMMMMM;EEEOOOEEEEEEEEEEAAEEEE;ASSSAAAAAAAAAAOOOOAAA");
INSERT INTO Mapa (id,nombre,dato) VALUES ('m3','mapa1',"MMMAAAAMMMMMMMMEEEEMMMMMMMMMM;EEEEEEEEEEEEEIIIIIEEEE;ASSSAAAAIIIIOOAAAAAAAA");
COMMIT;




