package com.thinkwise.cryptomachine.algorithms;

import com.thinkwise.cryptomachine.interfaces.Cipher;

public class Beaufort implements Cipher {

    @Override
    public String encrypt(String text, String key) {
        return performBeaufort(text, key);
    }

    @Override
    public String decrypt(String text, String key) {
        // Decryption in the Beaufort cipher is the same as encryption
        return performBeaufort(text, key);
    }

    @Override
    public void cryptoanalysis(String text) {
        // Cryptoanalysis logic (optional for now)
    }

    private String performBeaufort(String text, String key) {
        StringBuilder result = new StringBuilder();
        key = key.toUpperCase();
        int keyIndex = 0;

        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char encryptedChar = (char) (((key.charAt(keyIndex) - character + 26) % 26) + 'A');
                result.append(encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else if (Character.isLowerCase(character)) {
                char encryptedChar = (char) (((key.charAt(keyIndex) - Character.toUpperCase(character) + 26) % 26) + 'A');
                result.append(encryptedChar);
                keyIndex = (keyIndex + 1) % key.length();
            } else {
                result.append(character); // Non-alphabetic characters remain unchanged
            }
        }
        return result.toString();
    }
}
