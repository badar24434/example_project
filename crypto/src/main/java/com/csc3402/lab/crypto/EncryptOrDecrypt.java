package com.csc3402.lab.crypto;

// Class responsible for performing Vigenère cipher encryption or decryption
public class EncryptOrDecrypt {

    private char[] sentenceKey;
    private char[] codedSentence;
    private char[] charSentence;
    private int charFromSentence;
    private int charFromSentenceKey;
    private int numberOfCharsInAlphabet;
    private int codedSymbol;
    private String encryptedSentence;

    // Getters and setters for private fields
    public char[] getSentenceKey() {
        return sentenceKey;
    }

    public void setSentenceKey(char[] sentenceKey) {
        this.sentenceKey = sentenceKey;
    }

    public char[] getCodedSentence() {
        return codedSentence;
    }

    public void setCodedSentence(char[] codedSentence) {
        this.codedSentence = codedSentence;
    }

    public char[] getCharSentence() {
        return charSentence;
    }

    public void setCharSentence(char[] charSentence) {
        this.charSentence = charSentence;
    }

    public int getCharFromSentence() {
        return charFromSentence;
    }

    public void setCharFromSentence(int charFromSentence) {
        this.charFromSentence = charFromSentence;
    }

    public int getCharFromSentenceKey() {
        return charFromSentenceKey;
    }

    public void setCharFromSentenceKey(int charFromSentenceKey) {
        this.charFromSentenceKey = charFromSentenceKey;
    }

    public int getNumberOfCharsInAlphabet() {
        return numberOfCharsInAlphabet;
    }

    public void setNumberOfCharsInAlphabet(int numberOfCharsInAlphabet) {
        this.numberOfCharsInAlphabet = numberOfCharsInAlphabet;
    }

    public int getCodedSymbol() {
        return codedSymbol;
    }

    public void setCodedSymbol(int codedSymbol) {
        this.codedSymbol = codedSymbol;
    }

    public String getEncryptedSentence() {
        return encryptedSentence;
    }

    public void setEncryptedSentence(String encryptedSentence) {
        this.encryptedSentence = encryptedSentence;
    }

    // Method to perform the Vigenère cipher operation
    public String vigenereOperation(String sentence, String key) {
        TransformCode transformCode = new TransformCode();
        setCodedSentence(new char[sentence.length()]);
        setCharSentence(sentence.toCharArray());
        setSentenceKey(transformCode.transformSentence(sentence, key));

        for (int i = 0; i < sentence.length(); i++) {
            if (getSentenceKey()[i] != ' ') {
                // Calculate Vigenère cipher character
                setCharFromSentence((int) getCharSentence()[i] - 97);
                setCharFromSentenceKey((int) getSentenceKey()[i] - 97);
                setNumberOfCharsInAlphabet(26);
                setCodedSymbol((getCharFromSentence() + getCharFromSentenceKey()) % getNumberOfCharsInAlphabet() + 97);
                getCodedSentence()[i] = (char) getCodedSymbol();
            } else {
                getCodedSentence()[i] = ' ';
            }
        }
        setEncryptedSentence(String.valueOf(getCodedSentence()));
        return getEncryptedSentence();
    }
}
