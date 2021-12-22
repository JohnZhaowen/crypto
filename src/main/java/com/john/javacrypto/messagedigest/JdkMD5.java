package com.john.javacrypto.messagedigest;

import com.john.javacrypto.utils.MessageDigestUtils;
import org.junit.Test;

public class JdkMD5 {

    @Test
    public void test() throws Exception {
        String srcString = "你好，Hellow World!";
        //md5 hex string: 4ff1dfc5c3f2f3ebc03a7747b6453597
        System.out.println("md5 hex string: " + MessageDigestUtils.doDigest(srcString, "MD5"));
    }

}
