-- Script SQL para criar a tabela tb_cliente
CREATE TABLE tb_cliente (
    id INT PRIMARY KEY,
    nome VARCHAR(100),
    cpf BIGINT,
    tel BIGINT,
    endereco VARCHAR(255),
    numero INT,
    cidade VARCHAR(100),
    estado VARCHAR(2),
    email VARCHAR(100),
    idade INT
);

-- Script SQL para criar a tabela tb_venda
CREATE TABLE tb_venda (
    id INT PRIMARY KEY,
    codigo VARCHAR(100),
    id_cliente_fk INT,
    valor_total DECIMAL(10, 2),
    data_venda DATE,
    status_venda VARCHAR(50),
    FOREIGN KEY (id_cliente_fk) REFERENCES tb_cliente(id)
);

-- Script SQL para criar a tabela tb_produto
CREATE TABLE tb_produto (
    id INT PRIMARY KEY,
    codigo VARCHAR(100),
    nome VARCHAR(100),
    descricao VARCHAR(255),
    valor DECIMAL(10, 2),
    quantidade_estoque INT,
    data_criacao DATE,
    data_atualizacao DATE
);
CREATE TABLE tb_produto_quantidade (
    id INT PRIMARY KEY,
    id_produto_fk INT,
    id_venda_fk INT,
    quantidade INT,
    valor_total DECIMAL(10, 2),
    FOREIGN KEY (id_produto_fk) REFERENCES tb_produto(id),
    FOREIGN KEY (id_venda_fk) REFERENCES tb_venda(id)
);
CREATE TABLE tb_venda (
id INT PRIMARY KEY,
codigo VARCHAR(100) UNIQUE,
id_cliente_fk INT,
valor_total DECIMAL(10, 2),
data_venda DATE,
status_venda VARCHAR(50),
FOREIGN KEY (id_cliente_fk) REFERENCES tb_cliente(id)
);
CREATE SEQUENCE seq_cliente
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO CYCLE;

CREATE OR REPLACE PROCEDURE sp_cliente (
    p_nome IN VARCHAR2,
    p_cpf IN BIGINT,
    p_tel IN BIGINT,
    p_endereco IN VARCHAR2,
    p_numero IN INT,
    p_cidade IN VARCHAR2,
    p_estado IN VARCHAR2,
    p_email IN VARCHAR2,
    p_idade IN INT
)
IS
BEGIN
    INSERT INTO tb_cliente (id, nome, cpf, tel, endereco, numero, cidade, estado, email, idade)
    VALUES (seq_cliente.NEXTVAL, p_nome, p_cpf, p_tel, p_endereco, p_numero, p_cidade, p_estado, p_email, p_idade);
END;
/
