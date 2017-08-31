CREATE SEQUENCE seq_cliente START 1;
CREATE TABLE cliente (
    codigo BIGINT PRIMARY KEY not null DEFAULT nextval('seq_cliente'),
    nome VARCHAR(80) NOT NULL,
    tipo_pessoa VARCHAR(15) NOT NULL,
    cpf_cnpj VARCHAR(30),
    telefone VARCHAR(20),
    email VARCHAR(50) NOT NULL,
    logradouro VARCHAR(50),
    numero VARCHAR(15),
    complemento VARCHAR(20),
    cep VARCHAR(15),
    codigo_cidade BIGINT REFERENCES cidade(codigo)    
);