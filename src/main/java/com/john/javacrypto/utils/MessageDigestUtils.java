package com.john.javacrypto.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtils {

    public static String doDigest(String srcString, String algo) throws NoSuchAlgorithmException {

        byte[] srcBytes = srcString.getBytes(StandardCharsets.UTF_8);

        MessageDigest md5 = MessageDigest.getInstance(algo);
        byte[] digestBytes = md5.digest(srcBytes);

        return HexUtils.converBytes2HexStr(digestBytes);
    }

    public static String doMacDigest(String srcString, String key, String algo) throws Exception {

        byte[] srcBytes = srcString.getBytes(StandardCharsets.UTF_8);

        Mac mac = Mac.getInstance(algo);
        Key secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), algo);
        mac.init(secretKey);
        byte[] bytes = mac.doFinal(srcBytes);

        return HexUtils.converBytes2HexStr(bytes);
    }
}
