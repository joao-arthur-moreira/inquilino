CREATE SEQUENCE seq_estilo START 1;
CREATE TABLE estilo (
    codigo BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('seq_estilo'),
    nome VARCHAR(50) NOT NULL
);

CREATE SEQUENCE seq_imovel START 1;
CREATE TABLE imovel (
    codigo BIGINT PRIMARY KEY NOT NULL DEFAULT nextval('seq_imovel'),
    sku VARCHAR(50) NOT NULL,
    nome VARCHAR(80) NOT NULL,
    descricao TEXT NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    teor_alcoolico DECIMAL(10, 2) NOT NULL,
    comissao DECIMAL(10, 2) NOT NULL,    
    origem VARCHAR(50) NOT NULL,
    codigo_estilo BIGINT NOT NULL REFERENCES estilo(codigo)    
);

INSERT INTO estilo(nome) VALUES ('Básico');
INSERT INTO estilo(nome) VALUES ('Luxo');
INSERT INTO estilo(nome) VALUES ('Familiar');
INSERT INTO estilo(nome) VALUES ('Apartamento');
