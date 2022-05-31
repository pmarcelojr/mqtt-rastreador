package com.phonerastreador.backend.service;

public interface HashGeradorService {
    
    public String gerarHash(String senha);

    public boolean verificarHash(String senha, String hashed);

}
