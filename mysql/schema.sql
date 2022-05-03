CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `username` varchar(20)  UNIQUE NOT NULL,
  `password` varchar(128) NOT NULL,
  `email` varchar(320) UNIQUE DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `acls` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `rw` int NOT NULL,
  `topic` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `dispositivo` (
  `id` varchar(255) NOT NULL,
  `admin` int DEFAULT NULL,
  `criado_em` datetime NOT NULL,
  `nome` varchar(255) NOT NULL,
  `senha` varchar(255) NOT NULL,
  `topico` varchar(255) NOT NULL,
  `ultima_atualizacao` datetime DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `dispositivo_un` (`nome`,`user_id`),
  KEY `dispositivo_user_fk` (`user_id`),
  CONSTRAINT `dispositivo_user_fk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
