CREATE SEQUENCE seq_usuario START 1;
CREATE TABLE usuario (
    codigo BIGINT PRIMARY KEY not null DEFAULT nextval('seq_usuario'),
    nome VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    senha VARCHAR(120) NOT NULL,
    ativo BOOLEAN DEFAULT true,
    data_nascimento DATE
);

CREATE SEQUENCE seq_grupo START 1;
CREATE TABLE grupo (
    codigo BIGINT PRIMARY KEY not null DEFAULT nextval('seq_grupo'),
    nome VARCHAR(50) NOT NULL
);

CREATE SEQUENCE seq_permissao START 1;
CREATE TABLE permissao (
    codigo BIGINT PRIMARY KEY not null DEFAULT nextval('seq_permissao'),
    nome VARCHAR(50) NOT NULL
);

CREATE TABLE usuario_grupo (
    codigo_usuario BIGINT NOT NULL REFERENCES usuario(codigo),
    codigo_grupo BIGINT NOT NULL REFERENCES grupo(codigo),
    PRIMARY KEY (codigo_usuario, codigo_grupo) 
);

CREATE SEQUENCE seq_grupopermissao START 1;
CREATE TABLE grupo_permissao (
    codigo_grupo BIGINT NOT NULL REFERENCES grupo(codigo),
    codigo_permissao BIGINT NOT NULL REFERENCES permissao(codigo),
    PRIMARY KEY (codigo_grupo, codigo_permissao) 
);
