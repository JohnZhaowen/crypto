package com.john.javacrypto.sha;

import com.john.javacrypto.utils.MessageDigestUtils;
import org.junit.Test;

public class SHA256Test {

    @Test
    public void test() throws Exception {
        String srcString = "你好，Hellow World!";
        //SHA-256 hex string: 422b4d37714568aade2818bf6ae0ea41bb9d89f36b13cc101986c4440b15019d
        System.out.println("SHA-256 hex string: " + MessageDigestUtils.doDigest(srcString, "SHA-256"));
    }
}
