package com.thinkwise.cryptomachine.algorithms;

import com.thinkwise.cryptomachine.interfaces.Cipher;

import java.util.HashSet;
import java.util.Set;

public class FourSquare implements Cipher {

    private char[][] plainMatrix1; // Top-left plaintext matrix
    private char[][] plainMatrix2; // Bottom-right plaintext matrix
    private char[][] keyMatrix1;   // Top-right key matrix
    private char[][] keyMatrix2;   // Bottom-left key matrix

    @Override
    public String encrypt(String text, String key) {
        String[] keys = key.split(";");
        if (keys.length != 2) {
            throw new IllegalArgumentException("Two keys are required, separated by a semicolon (;).");
        }
        prepareMatrices(keys[0], keys[1]);
        return processText(text, true);
    }

    @Override
    public String decrypt(String text, String key) {
        String[] keys = key.split(";");
        if (keys.length != 2) {
            throw new IllegalArgumentException("Two keys are required, separated by a semicolon (;).");
        }
        prepareMatrices(keys[0], keys[1]);
        return processText(text, false);
    }

    @Override
    public void cryptoanalysis(String text) {
        // Placeholder for cryptoanalysis logic
    }

    private void prepareMatrices(String key1, String key2) {
        plainMatrix1 = createStandardMatrix();
        plainMatrix2 = createStandardMatrix();
        keyMatrix1 = createKeyMatrix(key1);
        keyMatrix2 = createKeyMatrix(key2);
    }

    private char[][] createStandardMatrix() {
        char[][] matrix = new char[5][5];
        int index = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            matrix[index / 5][index % 5] = c;
            index++;
        }
        return matrix;
    }

    private char[][] createKeyMatrix(String key) {
        key = key.toUpperCase().replace("J", "I");
        Set<Character> usedChars = new HashSet<>();
        char[][] matrix = new char[5][5];
        int row = 0, col = 0;

        // Add key characters to the matrix
        for (char c : key.toCharArray()) {
            if (Character.isLetter(c) && !usedChars.contains(c)) {
                matrix[row][col++] = c;
                usedChars.add(c);
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }

        // Fill remaining spaces with the rest of the alphabet
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            if (!usedChars.contains(c)) {
                matrix[row][col++] = c;
                usedChars.add(c);
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }

        return matrix;
    }

    private String processText(String text, boolean encrypt) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder processedText = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            if (encrypt) {
                // Encryption
                int[] pos1 = findPosition(first, plainMatrix1);
                int[] pos2 = findPosition(second, plainMatrix2);

                processedText.append(keyMatrix1[pos1[0]][pos2[1]]);
                processedText.append(keyMatrix2[pos2[0]][pos1[1]]);
            } else {
                // Decryption
                int[] pos1 = findPosition(first, keyMatrix1);
                int[] pos2 = findPosition(second, keyMatrix2);

                processedText.append(plainMatrix1[pos1[0]][pos2[1]]);
                processedText.append(plainMatrix2[pos2[0]][pos1[1]]);
            }
        }

        return processedText.toString();
    }

    private int[] findPosition(char c, char[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}