package com.company;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
//https://www.geeksforgeeks.org/hashtable-in-java/
//https://www.tutorialspoint.com/java_cryptography/java_cryptography_message_digest.htm

public class Hashing {

    User user1 = new User();
    String password = user1.getPassword();

    Hashing HashPassword = new Hashing(password);

    String HashedPassword = HashPassword.password;

    private Hashing(String password) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("SHA-256");
    md.update(this.password.getBytes(StandardCharsets.UTF_8));
    user1.setPassword(HashedPassword);
    }


}



