CREATE SEQUENCE seq_estado START 1;
CREATE TABLE estado (
    codigo BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('seq_estado'),
    nome VARCHAR(50) NOT NULL,
    sigla VARCHAR(2) NOT NULL
);

CREATE SEQUENCE seq_cidade START 1;
CREATE TABLE cidade (
    codigo BIGINT PRIMARY KEY not null DEFAULT nextval('seq_cidade'),
    nome VARCHAR(50) NOT NULL,
    codigo_estado BIGINT NOT NULL REFERENCES estado(codigo)    
);

INSERT INTO estado (nome, sigla) VALUES ('Acre', 'AC');
INSERT INTO estado (nome, sigla) VALUES ('Bahia', 'BA');
INSERT INTO estado (nome, sigla) VALUES ('Goiás', 'GO');
INSERT INTO estado (nome, sigla) VALUES ('Minas Gerais', 'MG');
INSERT INTO estado (nome, sigla) VALUES ('Santa Catarina', 'SC');
INSERT INTO estado (nome, sigla) VALUES ('São Paulo', 'SP');


INSERT INTO cidade (nome, codigo_estado) VALUES ('Rio Branco', 1);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Cruzeiro do Sul', 1);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Salvador', 2);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Porto Seguro', 2);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Santana', 2);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Goiânia', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Itumbiara', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Novo Brasil', 3);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Belo Horizonte', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Uberlândia', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Montes Claros', 4);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Florianópolis', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Criciúma', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Camboriú', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Lages', 5);
INSERT INTO cidade (nome, codigo_estado) VALUES ('São Paulo', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Ribeirão Preto', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Campinas', 6);
INSERT INTO cidade (nome, codigo_estado) VALUES ('Santos', 6);