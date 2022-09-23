package com.company;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
//https://www.geeksforgeeks.org/hashtable-in-java/
//https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm

public class Hashing {

    User findPassword = new User();

    String password = findPassword.getPassword();

    private Hashing() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes(StandardCharsets.UTF_8));

    }
}



