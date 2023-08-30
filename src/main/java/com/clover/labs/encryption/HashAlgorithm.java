package com.clover.labs.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashAlgorithm {

    /*public static String generateHash(String originalString) {
        Keccak.Digest256 digest256 = new Keccak.Digest256();
        byte[] hashedByteArray = digest256.digest(
                originalString.getBytes(StandardCharsets.UTF_8));
        return new String(Hex.encode(hashedByteArray));
    }*/

    public static String generateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}