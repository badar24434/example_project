package com.csc3402.lab.crypto;

// Class responsible for running the encryption/decryption process
public class Cipher {

    private String encryptedSentence;
    private String reversedKey;
    private String error;

    // Getters and setters for private fields
    public String getEncryptedSentence() {
        return encryptedSentence;
    }

    public void setEncryptedSentence(String encryptedSentence) {
        this.encryptedSentence = encryptedSentence;
    }

    public String getReversedKey() {
        return reversedKey;
    }

    public void setReversedKey(String reversedKey) {
        this.reversedKey = reversedKey;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    // Method to run the application and perform the chosen operation
    public String runApplication(String sentence, String key, int option) {
        EncryptOrDecrypt encryptVigenere = new EncryptOrDecrypt();
        TransformCode transformCode = new TransformCode();

        if (option == 1) {
            // Perform encryption
            setEncryptedSentence(encryptVigenere.vigenereOperation(sentence, key));
            return getEncryptedSentence();
        } else if (option == 2) {
            // Perform decryption
            setReversedKey(transformCode.transformKey(key));
            setEncryptedSentence(encryptVigenere.vigenereOperation(sentence, getReversedKey()));
            return getEncryptedSentence();
        } else {
            // Handle invalid option
            setError("Please select an option!");
            return getError();
        }
    }
}
