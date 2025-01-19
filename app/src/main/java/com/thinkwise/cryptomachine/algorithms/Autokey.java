package com.thinkwise.cryptomachine.algorithms;

import com.thinkwise.cryptomachine.interfaces.Cipher;

public class Autokey implements Cipher {

    @Override
    public String encrypt(String text, String key) {
        text = text.toUpperCase();
        key = key.toUpperCase();
        StringBuilder result = new StringBuilder();

        // Extend the key with the plaintext
        String extendedKey = key + text;
        extendedKey = extendedKey.substring(0, text.length());

        for (int i = 0; i < text.length(); i++) {
            char plaintextChar = text.charAt(i);
            if (Character.isLetter(plaintextChar)) {
                char keyChar = extendedKey.charAt(i);
                char encryptedChar = (char) ((plaintextChar - 'A' + (keyChar - 'A')) % 26 + 'A');
                result.append(encryptedChar);
            } else {
                result.append(plaintextChar); // Non-alphabetic characters remain unchanged
            }
        }

        return result.toString();
    }

    @Override
    public String decrypt(String text, String key) {
        text = text.toUpperCase();
        key = key.toUpperCase();
        StringBuilder result = new StringBuilder();

        // Decrypt one character at a time
        for (int i = 0; i < text.length(); i++) {
            char ciphertextChar = text.charAt(i);
            if (Character.isLetter(ciphertextChar)) {
                char keyChar = (i < key.length()) ? key.charAt(i) : result.charAt(i - key.length());
                char decryptedChar = (char) ((ciphertextChar - keyChar + 26) % 26 + 'A');
                result.append(decryptedChar);
            } else {
                result.append(ciphertextChar); // Non-alphabetic characters remain unchanged
            }
        }

        return result.toString();
    }

    @Override
    public void cryptoanalysis(String text) {
        // Placeholder for cryptoanalysis logic
    }
}
