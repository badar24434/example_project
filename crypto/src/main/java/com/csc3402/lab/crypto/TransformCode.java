package com.csc3402.lab.crypto;

// Class responsible for transforming the sentence and key for Vigenère cipher
public class TransformCode extends EncryptOrDecrypt {

    private char[] charKey;
    private char[] transformedSentence;
    private char[] transformedKey;
    private int keyNumber;
    private int charFromKey;
    private String reversedKey;

    // Getters and setters for private fields
    public char[] getCharKey() {
        return charKey;
    }

    public void setCharKey(char[] charKey) {
        this.charKey = charKey;
    }

    public char[] getTransformedSentence() {
        return transformedSentence;
    }

    public void setTransformedSentence(char[] transformedSentence) {
        this.transformedSentence = transformedSentence;
    }

    public char[] getTransformedKey() {
        return transformedKey;
    }

    public void setTransformedKey(char[] transformedKey) {
        this.transformedKey = transformedKey;
    }

    public int getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(int keyNumber) {
        this.keyNumber = keyNumber;
    }

    public int getCharFromKey() {
        return charFromKey;
    }

    public void setCharFromKey(int charFromKey) {
        this.charFromKey = charFromKey;
    }

    public String getReversedKey() {
        return reversedKey;
    }

    public void setReversedKey(String reversedKey) {
        this.reversedKey = reversedKey;
    }

    // Method to transform the sentence based on the key
    public char[] transformSentence(String sentence, String key) {
        setTransformedSentence(new char[sentence.length()]);
        setCharKey(key.toCharArray());

        for (int i = 0, j = 0; i < sentence.length(); i++, j++) {
            if (j >= key.length()) {
                j = 0;
            }
            if (sentence.charAt(i) == ' ') {
                getTransformedSentence()[i] = ' ';
                j--;
            } else {
                getTransformedSentence()[i] = getCharKey()[j];
            }
        }
        return getTransformedSentence();
    }
    // Method to reverse the key for decryption
    public String transformKey(String key) {
        setTransformedKey(new char[key.length()]);

        for (int i = 0; i < key.length(); i++) {
            setCharFromKey((int) key.charAt(i) - 97);
            setKeyNumber(26);
            setCharFromKey((getKeyNumber() - getCharFromKey()) % getKeyNumber() + 97);
            getTransformedKey()[i] = (char) getCharFromKey();
        }
        setReversedKey(String.valueOf(getTransformedKey()));
        return getReversedKey();
    }
}
