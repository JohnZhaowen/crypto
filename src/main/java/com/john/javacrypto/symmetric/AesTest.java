package com.john.javacrypto.symmetric;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.SecureRandom;

public class AesTest {

    private static final String ALGO = "AES";
    //支持的Key的长度：AESConstants.AES_KEYSIZES 16， 24， 32
    private static final String KEY = "1234567";

    @Test
    public void test() throws Exception {

        String originString = "你好，Hellow World!";
        String encryptString = encrypt(originString);
        //AES加密后结果： 3Nqo7tqj314tCXoqVDPoAjJOmxzwZTlnsBHzxFf7qpk=
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

    /**
     * seed的长度需要进行控制
     *
     * @param type
     * @param seed
     * @return
     * @throws Exception
     */
    private Cipher getCipherSimple(int type, String seed) throws Exception {
        Key keySpec = new SecretKeySpec(seed.getBytes(StandardCharsets.UTF_8), ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(type, keySpec);
        return cipher;
    }

    private Cipher getCipher(int type, String seed) throws Exception {

        //设置生成指定长度key的算法，在这里就是："SHA1PRNG"
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(seed.getBytes(StandardCharsets.UTF_8));

        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGO);
        //keySize只能是128, 192 or 256
        keyGenerator.init(128, secureRandom);
        SecretKey secretKey = keyGenerator.generateKey();

        Key keySpec = new SecretKeySpec(secretKey.getEncoded(), ALGO);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(type, keySpec);
        return cipher;
    }


}
