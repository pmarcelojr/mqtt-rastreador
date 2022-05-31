package com.phonerastreador.backend.service;

import java.util.Date;

import com.phonerastreador.backend.model.User;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${app.jwt.expiracao}")
    private String expiracao;

    @Value("${app.jwt.segredo}")
    private String segredo;
    
    public String gerarToken(Authentication authentication) {
        User conectado = (User) authentication.getPrincipal();
        Date hoje = new Date();

        Date expirar = new Date(hoje.getTime() + Long.parseLong(expiracao) * 1000);

        return Jwts.builder()
            .setIssuer("API do Phone Rastreador")
            .setSubject(conectado.getId().toString())
            .setIssuedAt(hoje)
            .setExpiration(expirar)
            .signWith(SignatureAlgorithm.HS256, segredo)
            .compact();
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(segredo).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUsuarioId(String token) {
        Claims claims = Jwts.parser().setSigningKey(segredo).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }
}
