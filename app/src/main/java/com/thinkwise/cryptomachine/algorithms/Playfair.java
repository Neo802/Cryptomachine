package com.thinkwise.cryptomachine.algorithms;

import com.thinkwise.cryptomachine.interfaces.Cipher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class Playfair implements Cipher {

    private char[][] keyMatrix;

    @Override
    public String encrypt(String text, String key) {
        prepareKeyMatrix(key);
        return processText(text, true);
    }

    @Override
    public String decrypt(String text, String key) {
        prepareKeyMatrix(key);
        return processText(text, false);
    }

    @Override
    public void cryptoanalysis(String text) {
        // Placeholder for cryptoanalysis logic
    }

    private void prepareKeyMatrix(String key) {
        key = key.toUpperCase().replace("J", "I");
        Set<Character> usedChars = new HashSet<>();
        keyMatrix = new char[5][5];

        // Add key characters to the matrix
        int row = 0, col = 0;
        for (char c : key.toCharArray()) {
            if (Character.isLetter(c) && !usedChars.contains(c)) {
                keyMatrix[row][col++] = c;
                usedChars.add(c);
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }

        // Add remaining letters to the matrix
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue; // Skip 'J'
            if (!usedChars.contains(c)) {
                keyMatrix[row][col++] = c;
                usedChars.add(c);
                if (col == 5) {
                    row++;
                    col = 0;
                }
            }
        }
    }

    private String processText(String text, boolean encrypt) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder processedText = new StringBuilder();

        // Divide the text into pairs (digraphs)
        List<String> digraphs = createDigraphs(text);

        for (String pair : digraphs) {
            char first = pair.charAt(0);
            char second = pair.charAt(1);
            int[] pos1 = findPosition(first);
            int[] pos2 = findPosition(second);

            if (pos1[0] == pos2[0]) {
                // Same row
                processedText.append(keyMatrix[pos1[0]][(pos1[1] + (encrypt ? 1 : 4)) % 5]);
                processedText.append(keyMatrix[pos2[0]][(pos2[1] + (encrypt ? 1 : 4)) % 5]);
            } else if (pos1[1] == pos2[1]) {
                // Same column
                processedText.append(keyMatrix[(pos1[0] + (encrypt ? 1 : 4)) % 5][pos1[1]]);
                processedText.append(keyMatrix[(pos2[0] + (encrypt ? 1 : 4)) % 5][pos2[1]]);
            } else {
                // Rectangle rule
                processedText.append(keyMatrix[pos1[0]][pos2[1]]);
                processedText.append(keyMatrix[pos2[0]][pos1[1]]);
            }
        }

        return processedText.toString();
    }

    private List<String> createDigraphs(String text) {
        List<String> digraphs = new ArrayList<>();
        for (int i = 0; i < text.length(); i += 2) {
            char first = text.charAt(i);
            char second = (i + 1 < text.length() && text.charAt(i + 1) != first) ? text.charAt(i + 1) : 'X';
            if (i + 1 < text.length() && text.charAt(i + 1) == first) {
                digraphs.add("" + first + 'X');
            } else {
                digraphs.add("" + first + second);
                i += second == 'X' ? -1 : 0;
            }
        }
        return digraphs;
    }

    private int[] findPosition(char c) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (keyMatrix[i][j] == c) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
