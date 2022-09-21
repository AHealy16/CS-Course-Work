package com.company;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
//https://www.geeksforgeeks.org/hashtable-in-java/

public class Hashing {

//    public void Hash
//
//    {
//
//
//        private static String getSecurePassword (String, String){
//        String generatedPassword = null;
//        try {
//
//            MessageDigest md = MessageDigest.getInstance("MD5");
//
//            md.update(salt.getBytes());
//
//
//            byte[] bytes = md.digest(passwordToHash.getBytes());
//
//            StringBuilder sb = new StringBuilder();
//
//            for (int i = 0; i < bytes.length; i++) {
//                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
//                        .substring(1));
//            }
//
//            generatedPassword = sb.toString();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return generatedPassword;
//    }
//
//
//        private static String getSalt () throws NoSuchAlgorithmException, NoSuchProviderException {
//
//        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
//
//        byte[] salt = new byte[16];
//
//        sr.nextBytes(salt);
//
//        return salt.toString();
//    }
//    }
}



