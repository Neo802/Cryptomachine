package com.thinkwise.cryptomachine.algorithms;

import com.thinkwise.cryptomachine.interfaces.Cipher;

public class Caesar implements Cipher {

    @Override
    public String encrypt(String text, String key) {
        int intKey = Integer.parseInt(key);
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (Character.isUpperCase(character)) {
                char encryptedChar = (char) (((character - 'A' + intKey) % 26) + 'A');
                result.append(encryptedChar);
            } else if (Character.isLowerCase(character)) {
                char encryptedChar = (char) (((character - 'a' + intKey) % 26) + 'a');
                result.append(encryptedChar);
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    public String decrypt(String text, String key) {
        int intKey = Integer.parseInt(key);
        return encrypt(text, String.valueOf(26 - (intKey % 26)));
    }

    @Override
    public void cryptoanalysis(String text) {
        // Implement cryptoanalysis logic here (optional for Android app)
    }
}

