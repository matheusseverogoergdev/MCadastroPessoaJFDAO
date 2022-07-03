CREATE TABLE pessoa (
    idPessoa INT PRIMARY KEY AUTO_INCREMENT,
    nomePessoa VARCHAR(60) NOT NULL,
    cpf VARCHAR(20) UNIQUE,
    endereco VARCHAR(60),
    telefone VARCHAR(20),
    idade INT,
    status BOOLEAN
);

CREATE TABLE carro (
	idCarro INT PRIMARY KEY AUTO_INCREMENT,
    placa VARCHAR(8) UNIQUE NOT NULL,
    marca VARCHAR(20),
    modelo VARCHAR(30),
    anoFabricacao INT,
    anoModelo INT,
    cor VARCHAR(10),
    nPortas INT,
    idPessoa INT,
    FOREIGN KEY (idCarro) REFERENCES pessoa(idPessoa)
);