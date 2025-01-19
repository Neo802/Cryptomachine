package com.thinkwise.cryptomachine.algorithms;

import com.thinkwise.cryptomachine.interfaces.Cipher;

public class Vigenere implements Cipher {

    @Override
    public String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char encryptedChar = (char) (((character - 'A' + (key.charAt(keyIndex) - 'A')) % 26) + 'A');
                result.append(encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else if (Character.isLowerCase(character)) {
                char encryptedChar = (char) (((character - 'a' + (key.charAt(keyIndex) - 'A')) % 26) + 'a');
                result.append(encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char decryptedChar = (char) (((character - 'A' - (key.charAt(keyIndex) - 'A') + 26) % 26) + 'A');
                result.append(decryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else if (Character.isLowerCase(character)) {
                char decryptedChar = (char) (((character - 'a' - (key.charAt(keyIndex) - 'A') + 26) % 26) + 'a');
                result.append(decryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    public void cryptoanalysis(String text) {
        // Cryptoanalysis logic for Vigenère Cipher (optional for Android app)
    }
}
