package com.john.javacrypto.symmetric;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

public class DesTest {

    private static final String ALGO = "DES";
    private static final String KEY = "12345678";

    @Test
    public void test() throws Exception {

        String originString = "你好，Hellow World!";
        String encryptString = encrypt(originString);
        //DES加密后结果： bVI453QkPGSL9kLjZJZmMgyFomSEPn9V
        System.out.println("DES加密后结果： " + encryptString);
        //解密后结果： 你好，Hellow World!
        System.out.println("解密后结果： " + decrypt(encryptString));

    }

    private String encrypt(String text) throws Exception {
        Key keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGO);

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        byte[] encryptBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeBase64String(encryptBytes);
    }

    private String decrypt(String text) throws Exception {
        Key keySpec = new SecretKeySpec(KEY.getBytes(StandardCharsets.UTF_8), ALGO);

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, keySpec);
        byte[] encryptBytes = cipher.doFinal(Base64.decodeBase64(text.getBytes(StandardCharsets.UTF_8)));

        return new String(encryptBytes, StandardCharsets.UTF_8);
    }
}
