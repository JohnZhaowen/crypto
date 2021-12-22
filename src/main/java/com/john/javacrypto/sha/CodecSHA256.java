package com.john.javacrypto.sha;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class CodecSHA256 {

    @Test
    public void test() {
        String srcString = "你好，Hellow World!";
        //sha256 hex string: 422b4d37714568aade2818bf6ae0ea41bb9d89f36b13cc101986c4440b15019d
        System.out.println("sha256 hex string: " + DigestUtils.sha256Hex(srcString));
    }

}
