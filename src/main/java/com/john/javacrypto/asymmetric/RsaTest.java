package com.john.javacrypto.asymmetric;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RsaTest {

    private static final String ALGO = "RSA";
    private static String PUBLICKEY_PATH;
    private static String PRIVATEKEY_PATH;

    /**
     * RSAd按此加密的明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;

    static {
        PUBLICKEY_PATH = "D:/Java/workspace/java-crypto/src/main/resources/rsa.pub";
        PRIVATEKEY_PATH = "D:/Java/workspace/java-crypto/src/main/resources/rsa.pri";
    }

    @Test
    public void testGenerateKeyFile() throws Exception {
        writeKey2File();
    }

    @Test
    public void testRsa() throws Exception {

        String msg = "你好，hellow world!";

        //公钥加密，私钥解密
        String encryptedByPublicKey = encrypt(msg, getPublicKey());
        //D8udHGoFH0Gk0apeYMOYg/ZmSkwYabekc0HaIoOd1XsDKombghn8qLSmCW1bzpkRW1Z8cLxc3ymgaQ4B5klPR0LrXhV5rp2Td9I1TB0Z/kKa7cEUyDnDroCScGTo8e/gZYJ/h6ycuQQqffSwp64UJXdTyMPRXPz5pnI2sIVLeho=
        System.out.println("公钥加密后的数据: " + encryptedByPublicKey);
        //你好，hellow world!
        String decryptedByPrivateKey = decrypt(encryptedByPublicKey, getPrivateKey());
        System.out.println("私钥解密后的数据：" + decryptedByPrivateKey);

        //私钥加密，公钥解密
        String encryptedByPrivateKey = encrypt(msg, getPrivateKey());
        //H7ihIEiQXFuJXPG/CNuzEnYhL8ELHhHIw165P9A6iOARoChsaccwM/quhbTVMofAIwOJxpfxDgau8M22/dm6aaNpor5+qr9CaC4RztFagHT7AOa9YTc7E+aFxEf2/lfw77FYt3OP4xNlZ5dJmxHJZovfQviosFlPejjzr+57dfY=
        System.out.println("私钥加密后的数据: " + encryptedByPublicKey);
        String decryptedByPublicKey = decrypt(encryptedByPrivateKey, getPublicKey());
        //你好，hellow world!
        System.out.println("公钥解密后的数据：" + decryptedByPrivateKey);

    }

    public String encrypt(String srcString, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = doCodec(cipher, srcString.getBytes(StandardCharsets.UTF_8), MAX_ENCRYPT_BLOCK);
        return Base64.encodeBase64String(encryptedBytes);
    }

    public String decrypt(String encryptedStr, Key key) throws Exception {
        byte[] encryptedBytes = Base64.decodeBase64(encryptedStr);

        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = doCodec(cipher, encryptedBytes, MAX_DECRYPT_BLOCK);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    private byte[] doCodec(Cipher cipher, byte[] bytes, int maxBlockSize) throws Exception {
        int inputlen = bytes.length;
        int offset = 0;
        byte[] cache;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //循环分段处理
        while (inputlen > offset) {
            if ((inputlen - offset) > maxBlockSize) {
                cache = cipher.doFinal(bytes, offset, maxBlockSize);
            } else {
                cache = cipher.doFinal(bytes, offset, (inputlen - offset));
            }
            byteArrayOutputStream.write(cache, 0, cache.length);
            offset = (++i) * maxBlockSize;
        }

        byte[] res = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return res;

    }

    /**
     * 从生成好的公钥文件rsa.pub（经Base64编码后存储的）中获取公钥
     *
     * @return
     */
    public static PublicKey getPublicKey() throws Exception {
        String publicKeyBase64String = FileUtils.readFileToString(new File(PUBLICKEY_PATH), StandardCharsets.UTF_8);
        byte[] pubKeyBytes = Base64.decodeBase64(publicKeyBase64String);

        //公钥的规则就是X509
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(pubKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGO);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    /**
     * 从生成好的私钥文件rsa.pri（经Base64编码后存储的）中获取公钥
     *
     * @return
     */
    public static PrivateKey getPrivateKey() throws Exception {
        String privateKeyBase64String = FileUtils.readFileToString(new File(PRIVATEKEY_PATH), StandardCharsets.UTF_8);
        byte[] priKeyBytes = Base64.decodeBase64(privateKeyBase64String);

        //私钥的规则就是PKCS8
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(priKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGO);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 生成公私钥文件
     */
    private void writeKey2File() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGO);
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        byte[] publicKeyEncoded = publicKey.getEncoded();
        String publicKeyBase64String = Base64.encodeBase64String(publicKeyEncoded);
        FileUtils.writeStringToFile(new File(PUBLICKEY_PATH), publicKeyBase64String, StandardCharsets.UTF_8);

        PrivateKey privateKey = keyPair.getPrivate();
        byte[] privateKeyEncoded = privateKey.getEncoded();
        String privateKeyBase64String = Base64.encodeBase64String(privateKeyEncoded);
        FileUtils.writeStringToFile(new File(PRIVATEKEY_PATH), privateKeyBase64String, StandardCharsets.UTF_8);

    }
}
