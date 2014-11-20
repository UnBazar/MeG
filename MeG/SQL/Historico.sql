CREATE TABLE `Historico` (
  `id` TINYINT NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `acessos` int(11),
  PRIMARY KEY (`id`)
  );
 
use MEG;
INSERT INTO Historico (nome, acessos) VALUES('ranking', 0);
INSERT INTO Historico (nome, acessos) VALUES('compara', 0);
INSERT INTO Historico (nome, acessos) VALUES('projecao', 0);
INSERT INTO Historico (nome, acessos) VALUES('grafico', 0);