-- estrutura do banco de dados para o Mosquitto
CREATE TABLE DEVICE_USER (
    ID INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    USERNAME VARCHAR(25) NOT NULL,
    PW VARCHAR(100) NOT NULL,
    SUPER BOOLEAN NOT NULL DEFAULT 0,

    UNIQUE KEY UNIQUE_USER (USERNAME)
);

CREATE TABLE acls (
    id INT UNSIGNED PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(25) NOT NULL,
    topic VARCHAR(100) NOT NULL,
    rw INT NOT NULL DEFAULT 1 -- 1 para apenas ler tópicos, 2 para ler-gravar tópicos
);

-- password: str0ngp@ssword$mqtt
INSERT INTO DEVICE_USER (USERNAME, PW, SUPER) VALUES ("backend_user", "PBKDF2$sha256$901$tdFG5NxAHbCKmRsS$t3YJl4jJQkWkvfDaq8J6tRgL2OuyYRgh", 1);

-- password: 3141592
INSERT INTO DEVICE_USER (USERNAME, PW, SUPER) VALUES ("user_test", "PBKDF2$sha256$901$FV3ugJkJu/559NTh$CFdg2n9HzleGvJJEtnkdmSXs2lNhXLSl", 0);

-- 1 pode ler
-- 2 pode ler/escrever
INSERT INTO acls (username, topic, rw) VALUES ('user_test', 'owntracks/celular', 2);
INSERT INTO acls (username, topic, rw) VALUES ('backend_user', 'owntracks/#', 1);

