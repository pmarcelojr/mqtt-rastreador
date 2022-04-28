
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
  `ultima_localizacao_id` varchar(255) DEFAULT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKfnm57bnjfq1mey2agt91ndsat` (`nome`,`user_id`),
  KEY `FKf577tpblghjwchowt2rjlj06v` (`ultima_localizacao_id`),
  KEY `FKe1b91y83rjtflsvna0o5u20bo` (`user_id`),
  CONSTRAINT `FKe1b91y83rjtflsvna0o5u20bo` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKf577tpblghjwchowt2rjlj06v` FOREIGN KEY (`ultima_localizacao_id`) REFERENCES `localizacao` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
