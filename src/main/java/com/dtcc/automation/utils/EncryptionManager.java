package com.dtcc.automation.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class EncryptionManager {
    private EncryptionManager() {}

    public static String encryptString(String plainText) {
        return Base64.getEncoder().encodeToString(plainText.getBytes(StandardCharsets.UTF_8));
    }

    public static String decryptString(String encodedText) {
        if (encodedText == null || encodedText.isBlank()) {
            throw new IllegalArgumentException("Encoded value cannot be blank.");
        }
        return new String(Base64.getDecoder().decode(encodedText), StandardCharsets.UTF_8);
    }
}
