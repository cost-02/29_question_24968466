package com.example;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Test {
    private String encryptedText;  // Usa una stringa per tenere il testo cifrato in Base64

    public String encrypt(String input) {
        try {
            String key = "Bar12345Bar12345Bar12345Bar12345";  // La chiave deve essere di 16, 24, 32 bytes
            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(input.getBytes("UTF-8"));
            encryptedText = Base64.getEncoder().encodeToString(encrypted);  // Converti in Base64
            System.err.println("Encrypted: " + encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    public String decrypt(String input) {
        try {
            String key = "Bar12345Bar12345Bar12345Bar12345";  // Assicurati che la chiave sia la stessa
            SecretKeySpec aesKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(input));  // Decodifica Base64 prima di decifrare
            String decrypted = new String(decryptedBytes, "UTF-8");
            System.err.println("Decrypted: " + decrypted);
            return decrypted;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;  // ritorna null se c'è un errore
    }

    public static void main(String[] args) {
        Test test = new Test();
        String encrypted = test.encrypt("Hello, world!");
        String decrypted = test.decrypt(encrypted);
    }
}
