package com.john.javacrypto.sha;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class CodecSHA512 {

    @Test
    public void test() {
        String srcString = "你好，Hellow World!";
        //sha256 hex string: 87e7644b3913cb4c98735dde05520e7340989a5cf47294d7ba33d2e80f13b7fd09a8971bd440d4b58a02b12533815442efa12efc4a1dc2153a8c7d32be07d1a1
        System.out.println("sha256 hex string: " + DigestUtils.sha512Hex(srcString));
    }

}
