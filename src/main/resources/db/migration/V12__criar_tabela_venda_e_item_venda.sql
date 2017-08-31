CREATE SEQUENCE seq_venda START 1;
CREATE TABLE venda (
    codigo BIGINT PRIMARY KEY DEFAULT nextval('seq_venda'),
    data_criacao timestamp NOT NULL,
    valor_frete DECIMAL(10,2),
    valor_desconto DECIMAL(10,2),
    valor_total DECIMAL(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    observacao VARCHAR(200),
    data_hora_entrega timestamp,
    codigo_cliente BIGINT NOT NULL REFERENCES cliente(codigo),
    codigo_usuario BIGINT NOT NULL REFERENCES usuario(codigo)
);

CREATE SEQUENCE seq_itemvenda START 1;
CREATE TABLE item_venda (
    codigo BIGINT PRIMARY KEY DEFAULT nextval('seq_itemvenda'),
    quantidade INTEGER NOT NULL,
    valor_unitario DECIMAL(10,2) NOT NULL,
    codigo_imovel BIGINT NOT NULL REFERENCES imovel(codigo),
    codigo_venda BIGINT NOT NULL REFERENCES venda(codigo)
);