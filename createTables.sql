CREATE TABLE IF NOT EXISTS VENDEDOR (
  ID_VENDEDOR INTEGER NOT NULL IDENTITY,
  NOME_VENDEDOR VARCHAR(255) NOT NULL,
  CPF_VENDEDOR VARCHAR(11) NOT NULL,
  TEL_VENDEDOR VARCHAR(20) NOT NULL,
  TOTAL_VENDAS INTEGER NULL,
  SALARIO DOUBLE NOT NULL,
  PRIMARY KEY (ID_VENDEDOR)
);

CREATE TABLE IF NOT EXISTS VEICULO (
  ID_VEICULO INTEGER NOT NULL IDENTITY,
  MODELO VARCHAR(50) NOT NULL,
  PLACA VARCHAR(8) NOT NULL,
  ALUGUEL DOUBLE NOT NULL,
  VENDA DOUBLE NOT NULL,
  KM_RODADOS DOUBLE NOT NULL,
  TIPO VARCHAR(5) NOT NULL,
  DESCRICAO VARCHAR(255) NOT NULL,
  ANO INTEGER NOT NULL,
  PRIMARY KEY (ID_VEICULO)
);

CREATE TABLE IF NOT EXISTS CLIENTE (
  ID_CLIENTE INTEGER NOT NULL IDENTITY,
  NOME_CLIENTE VARCHAR(255) NOT NULL,
  CPF_CLIENTE VARCHAR(11) NOT NULL,
  EMAIL VARCHAR(255) NOT NULL,
  TEL_CLIENTE VARCHAR(20) NOT NULL,
  PRIMARY KEY(ID_CLIENTE)
);

CREATE TABLE IF NOT EXISTS ALUGUEL (
  ID_ALUGUEL INTEGER NOT NULL IDENTITY,
  ID_VEICULO INTEGER NOT NULL,
  ID_CLIENTE INTEGER NOT NULL,
  LOCATARIO VARCHAR(255) NOT NULL,
  PRECO_ALUGUEL DOUBLE NOT NULL,
  DATA_ALUGUEL DATE NOT NULL,
  DATA_DEVOLUCAO DATE NOT NULL,
  PRIMARY KEY (ID_ALUGUEL)
);

ALTER TABLE ALUGUEL ADD CONSTRAINT IF NOT EXISTS FK_ALUGUEL_VEICULO FOREIGN KEY(ID_VEICULO) REFERENCES VEICULO(ID_VEICULO) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE ALUGUEL ADD CONSTRAINT IF NOT EXISTS FK_ALUGUEL_CLIENTE FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE TABLE IF NOT EXISTS VENDAS (
  ID_VENDAS INTEGER NOT NULL IDENTITY,
  ID_VEICULO INTEGER NOT NULL,
  RENDIMENTO DOUBLE NULL,
  PRIMARY KEY (ID_VENDAS)
);

ALTER TABLE VENDAS ADD CONSTRAINT IF NOT EXISTS FK_VENDAS_VEICULO FOREIGN KEY(ID_VEICULO) REFERENCES VEICULO(ID_VEICULO) ON DELETE CASCADE ON UPDATE CASCADE;