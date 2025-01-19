package com.thinkwise.cryptomachine.interfaces;

public interface Cipher {
    String encrypt(String text, String key);

    String decrypt(String text, String key);

    void cryptoanalysis(String text);
}
