package com.john.javacrypto.messagedigest;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class CodecMD5 {

    @Test
    public void test() {
        String srcString = "你好，Hellow World!";
        //md5 hex string: 4ff1dfc5c3f2f3ebc03a7747b6453597
        System.out.println("md5 hex string: " + DigestUtils.md5Hex(srcString));
    }

}
