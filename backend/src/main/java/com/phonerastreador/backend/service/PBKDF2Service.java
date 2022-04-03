package com.phonerastreador.backend.service;

import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.Random;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service(value = "PBKDF2")
public class PBKDF2Service implements HashGeradorService {
    private static final int KEY_LENGTH = 24 * 8;
    private static final int SALT_LENGTH = 12;
    private static final int ITERATIONS = 901;

    private static final Logger log = LoggerFactory.getLogger(PBKDF2Service.class);

    public String gerarHash(String senha) {
        byte someBytes[] = new byte[SALT_LENGTH];
        Random randomGenerator = new Random();
        randomGenerator.nextBytes(someBytes);
        String encodedSalt = Base64.getEncoder().encodeToString(someBytes);

        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException ex) {
            log.error("Algoritmo inválido para gerar PBKDF2", ex);
        }

        KeySpec ks = new PBEKeySpec(senha.toCharArray(), encodedSalt.getBytes(), ITERATIONS, KEY_LENGTH);
        SecretKey s;
        try {
            s = f.generateSecret(ks);
            String encodedKey = Base64.getEncoder().encodeToString(s.getEncoded());
            String hashedKey = "PBKDF2$sha256$" + ITERATIONS + "$" + encodedSalt + "$" + encodedKey;
            return hashedKey;
        } catch (InvalidKeySpecException ex) {
            log.error("Chave de PBKDF2 é inválida", ex);
        }

        return null;
    }

    public boolean verificarHash(String senha, String hashed) {
        String[] encodedPassword = hashed.split("\\$");
        int encodedIterations = Integer.parseInt(encodedPassword[2]);
        byte[] encodedSalt = encodedPassword[3].getBytes(Charset.forName("UTF-8"));
        String encodedHash = encodedPassword[4];
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Need a Java implementation with cryptography.");
        }
        KeySpec ks = new PBEKeySpec(senha.toCharArray(), encodedSalt, encodedIterations, KEY_LENGTH);
        SecretKey s = null;
        try {
            s = f.generateSecret(ks);
        } catch (InvalidKeySpecException e) {
            System.out.println("Encoded password is corrupt.");
        }
        return encodedHash.equals(Base64.getEncoder().encodeToString(s.getEncoded()));
    }
}
