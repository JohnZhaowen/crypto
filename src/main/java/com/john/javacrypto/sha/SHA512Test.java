package com.john.javacrypto.sha;

import com.john.javacrypto.utils.MessageDigestUtils;
import org.junit.Test;

public class SHA512Test {

    @Test
    public void test() throws Exception {
        String srcString = "你好，Hellow World!";
        //SHA-256 hex string: 87e7644b3913cb4c98735dde05520e7340989a5cf47294d7ba33d2e80f13b7fd09a8971bd440d4b58a02b12533815442efa12efc4a1dc2153a8c7d32be07d1a1
        System.out.println("SHA-256 hex string: " + MessageDigestUtils.doDigest(srcString, "SHA-512"));
    }
}
