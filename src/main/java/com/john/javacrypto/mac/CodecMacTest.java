package com.john.javacrypto.mac;

import org.apache.commons.codec.digest.HmacUtils;
import org.junit.Test;

public class CodecMacTest {

    private static final String srcString = "你好，Hellow World!";
    private static final String key = "123";

    @Test
    public void testHmacMD5() {
        //8c53b339bf5a34113aa4273d9ee224f9
        System.out.println(HmacUtils.hmacMd5Hex(key, srcString));
    }

    @Test
    public void testHmacSHA256() throws Exception {
        //bb693cfa6f80f73e79a3149e09c59fa6a02c8d0c28de4414e8bb9d316c9b82ba
        System.out.println(HmacUtils.hmacSha256Hex(key, srcString));
    }

    @Test
    public void testHmacSHA512() throws Exception {
        //1e0458f2502a0ca993ff345ee7695e02388c2dda49cc6f64630a18d4e9512e5afe10e814875efe9517116afab4e89a21156f05a87f722d3538ebcd6fc47a96b9
        System.out.println(HmacUtils.hmacSha512Hex(key, srcString));
    }

}
