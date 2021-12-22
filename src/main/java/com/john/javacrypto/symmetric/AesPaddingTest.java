package com.john.javacrypto.symmetric;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

public class AesPaddingTest {
    /**
     * AES的默认填充模式是ECB，加密模式是PKCS5Padding
     */
    private static final String ALGO = "AES/CBC/PKCS5Padding";
    private static final String ALGO_TYPE = "AES";
    private static final String IV = "abcdefghabcdefgh";
    private static final String KEY = "1234567";

    @Test
    public void test() throws Exception {

        String originString = "你好，Hellow World!";
        String encryptString = encrypt(originString);
        //AES加密后结果： YFVHAt/hd7ktgeIT7XO6evHKb3kLtBsbZEF4JwSwJXE=
        System.out.println("AES加密后结果： " + encryptString);
        //解密后结果： 你好，Hellow World!
        System.out.println("解密后结果： " + decrypt(encryptString));

    }

    private String encrypt(String text) throws Exception {
        Cipher cipher = getCipher(Cipher.ENCRYPT_MODE, KEY);
        byte[] encryptBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

        return Base64.encodeBase64String(encryptBytes);
    }

    private String decrypt(String text) throws Exception {

        Cipher cipher = getCipher(Cipher.DECRYPT_MODE, KEY);
        byte[] encryptBytes = cipher.doFinal(Base64.decodeBase64(text.getBytes(StandardCharsets.UTF_8)));

        return new String(encryptBytes, StandardCharsets.UTF_8);
    }


    private Cipher getCipher(int type, String seed) throws Exception {

        //设置生成指定长度key的算法，在这里就是："SHA1PRNG"
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(seed.getBytes(StandardCharsets.UTF_8));

        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGO_TYPE);
        //keySize只能是128, 192 or 256
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        Key keySpec = new SecretKeySpec(secretKey.getEncoded(), ALGO_TYPE);
        Cipher cipher = Cipher.getInstance(ALGO);
        //cbc的时候需要
        IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes(StandardCharsets.UTF_8));

        cipher.init(type, keySpec, ivParameterSpec);
        return cipher;
    }


}
