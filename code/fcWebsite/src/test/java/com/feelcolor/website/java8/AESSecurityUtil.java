package com.feelcolor.website.java8;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public final class AESSecurityUtil {
    public static final String ALGORITHM_AES = "AES";
    public static final String SHA1PRNG_AES = "SHA1PRNG";
    public static final String CIPHER_ALGORITHM_AES = "AES/ECB/PKCS5Padding";

    public static Key getSecretKey(String key) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM_AES);
        SecureRandom secureRandom = SecureRandom.getInstance(SHA1PRNG_AES);
        secureRandom.setSeed(key.getBytes());
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey;
    }

    public static byte[] encrypt(byte[] data, String key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key secretKey = getSecretKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    public static byte[] decrypt(byte[] data, String key) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Key secretKey = getSecretKey(key);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

}
