package com.phonerastreador.backend.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service(value = "BCrypt")
public class BCryptService implements HashGeradorService {

    public String gerarHash(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt());
    }

    public boolean verificarHash(String senha, String hashed) {
        return BCrypt.checkpw(senha, hashed);
    }
}
