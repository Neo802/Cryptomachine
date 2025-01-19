package com.thinkwise.cryptomachine.algorithms;

import com.thinkwise.cryptomachine.interfaces.Cipher;

public class Vatsayana implements Cipher {

    private String keyMapping;

    public Vatsayana(String keyMapping) {
        this.keyMapping = keyMapping;
    }

    @Override
    public String encrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                int index = (character - base);
                encryptedText.append(keyMapping.charAt(index));
            } else {
                encryptedText.append(character);
            }
        }
        return encryptedText.toString();
    }

    @Override
    public String decrypt(String text, String key) {
        StringBuilder decryptedText = new StringBuilder();
        for (char character : text.toCharArray()) {
            int index = keyMapping.indexOf(character);
            if (index != -1) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                decryptedText.append((char) (index + base));
            } else {
                decryptedText.append(character);
            }
        }
        return decryptedText.toString();
    }

    @Override
    public void cryptoanalysis(String text) {
        // Cryptoanalysis logic for Vatsayana Cipher (optional for Android app)
    }
}
