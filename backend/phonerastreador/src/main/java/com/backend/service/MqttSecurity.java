package com.backend.service;

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

/**
 * This class implements functionality to generate and validate PBKDF2 hashes
 * for mosquitto-auth-plug.
 *
 * @author Manuel Domínguez-Dorado - manuel.dominguez@enzinatec.es
 * @version 1.0
 */
public class MqttSecurity {

    private static final Logger log = LoggerFactory.getLogger(MqttSecurity.class);
    
    /**
     * This method compares a plain password and a PBKDF2 password (in
     * mosquitto-auth-plug format) to know whether the password match the PBKDF2
     * hash.
     *
     * @author Manuel Domínguez Dorado - manuel.dominguez@enzinatec.com
     * @param plainPassword Tha plain password to be compared to the PBKDF2
     * hash.
     * @param hashedPasword The PBKDF2 password in mosquitto-auth-plug format
     * (usualli it is stored in a MySQL database).
     * @return true, if password matches the PBKDF2 hash. false on the contrary.
     * @since 1.0
     */
    public boolean isValidPassword(String plainPassword, String hashedPasword) {
        String[] encodedPassword = hashedPasword.split("\\$");
        int encodedIterations = Integer.parseInt(encodedPassword[2]);
        byte[] encodedSalt = encodedPassword[3].getBytes(Charset.forName("UTF-8"));
        String encodedHash = encodedPassword[4];
        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Need a Java implementation with cryptography.");
        }
        KeySpec ks = new PBEKeySpec(plainPassword.toCharArray(), encodedSalt, encodedIterations, MqttSecurity.KEY_LENGTH);
        SecretKey s = null;
        try {
            s = f.generateSecret(ks);
        } catch (InvalidKeySpecException e) {
            System.out.println("Encoded password is corrupt.");
        }
        return encodedHash.equals(Base64.getEncoder().encodeToString(s.getEncoded()));
    }

    /**
     * This method creates a new PBKDF2 password (in mosquitto-auth-plug format)
     * from a plain password.
     *
     * @author Manuel Domínguez Dorado - manuel.dominguez@enzinatec.com
     * @param plainPassword The plain password used to generate the
     * corresponding PBKDF2 password (in mosquitto-auth-plug) format.
     * @return The generated PBKDF2 password in mosquitto-auth-plug format
     * (usually, it will be stored in a MySQL database).
     * @since 1.0
     */
    public String createPassword(String plainPassword) {
        byte someBytes[] = new byte[MqttSecurity.SALT_LENGTH];
        Random randomGenerator = new Random();
        randomGenerator.nextBytes(someBytes);
        String encodedSalt = Base64.getEncoder().encodeToString(someBytes);

        SecretKeyFactory f = null;
        try {
            f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        } catch (NoSuchAlgorithmException ex) {
            log.error("Password Algorithm not found", ex);
        }
        KeySpec ks = new PBEKeySpec(plainPassword.toCharArray(), encodedSalt.getBytes(), MqttSecurity.ITERATIONS, MqttSecurity.KEY_LENGTH);
        SecretKey s;
        try {
            s = f.generateSecret(ks);
            String encodedKey = Base64.getEncoder().encodeToString(s.getEncoded());
            String hashedKey = "PBKDF2$sha256$" + MqttSecurity.ITERATIONS + "$" + encodedSalt + "$" + encodedKey;
            return hashedKey;
        } catch (InvalidKeySpecException ex) {
            log.error("Invalid Password key specified", ex);
        }
        return "";
    }

    private static final int KEY_LENGTH = 24 * 8;
    private static final int SALT_LENGTH = 12;
    private static final int ITERATIONS = 901;
}
