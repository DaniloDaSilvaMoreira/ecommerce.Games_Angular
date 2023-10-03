-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO estado (nome, sigla) VALUES ('Acre', 'AC');
INSERT INTO estado (nome, sigla) VALUES ('Amazonas', 'AM');
INSERT INTO estado (nome, sigla) VALUES ('Goiás', 'GO');
INSERT INTO estado (nome, sigla) VALUES ('Pará', 'PA');
INSERT INTO estado (nome, sigla) VALUES ('Tocantins', 'TO');

INSERT INTO cidade (nome, id_estado) VALUES ('Manaus', 2);
INSERT INTO cidade (nome, id_estado) VALUES ('Palmas', 5);
INSERT INTO cidade (nome, id_estado) VALUES ('Guaraí', 5);
INSERT INTO cidade (nome, id_estado) VALUES ('Belém', 4);
INSERT INTO cidade (nome, id_estado) VALUES ('Goiânia', 3);

INSERT INTO genero (nome) VALUES ('Terror');
INSERT INTO genero (nome) VALUES ('Action RPG');
INSERT INTO genero (nome) VALUES ('Luta');
INSERT INTO genero (nome) VALUES ('Puzzle');
INSERT INTO genero (nome) VALUES ('RPG de turno');

INSERT INTO developer (nome, anoFundacao) VALUES ('Konami', '1970-10-22');
INSERT INTO developer (nome, anoFundacao) VALUES ('Capcom', '1980-01-12');
INSERT INTO developer (nome, anoFundacao) VALUES ('Rare', '1987-05-30');
INSERT INTO developer (nome, anoFundacao) VALUES ('Ubisoft', '1998-08-04');
INSERT INTO developer (nome, anoFundacao) VALUES ('Activision', '1990-11-15');

INSERT INTO fabricante (nome, anoFundacao) VALUES ('Nintendo', '1888-01-28');
INSERT INTO fabricante (nome, anoFundacao) VALUES ('Sony', '1977-11-09');
INSERT INTO fabricante (nome, anoFundacao) VALUES ('Microsoft', '1975-05-10');
INSERT INTO fabricante (nome, anoFundacao) VALUES ('SEGA', '1979-09-29');
INSERT INTO fabricante (nome, anoFundacao) VALUES ('Atari', '1978-02-11');

INSERT INTO plataforma (nome, descricao, anoLancamento, id_fabricante) 
       VALUES ('PlayStation', 'jogos', '1994-05-30', 2 );