package com.thinkwise.cryptomachine.algorithms;

import android.util.Log;

import com.thinkwise.cryptomachine.interfaces.Cipher;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TwoSquare implements Cipher {

    private char[][] matrix1;
    private char[][] matrix2;

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
        matrix1 = createMatrix(key1);
        matrix2 = createMatrix(key2);
    }

    private char[][] createMatrix(String key) {
        key = key.toUpperCase().replace("J", "I");
        Set<Character> usedChars = new HashSet<>();
        char[][] matrix = new char[5][5];

        int row = 0, col = 0;
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

        // Divide the text into digraphs
        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length()) ? text.charAt(i + 1) : 'X';

            int[] pos1 = findPosition(first, matrix1);
            int[] pos2 = findPosition(second, matrix2);

            if (pos1 != null && pos2 != null) {
                if (pos1[0] == pos2[0]) {
                    // Same row (No swapping needed for encryption/decryption)
                    processedText.append(matrix1[pos1[0]][(pos1[1] + (encrypt ? 1 : 4)) % 5]);
                    processedText.append(matrix2[pos2[0]][(pos2[1] + (encrypt ? 1 : 4)) % 5]);
                }
                else {
                    // Swap columns
                    processedText.append(matrix1[pos1[0]][pos2[1]]);
                    processedText.append(matrix2[pos2[0]][pos1[1]]);
                }
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
