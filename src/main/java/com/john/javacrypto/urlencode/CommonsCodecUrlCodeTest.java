package com.john.javacrypto.urlencode;

import org.apache.commons.codec.net.URLCodec;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class CommonsCodecUrlCodeTest {

    @Test
    public void testUrlEncode() throws Exception {
        String srcUrl = "我的java加密课程";

        String encodedUrl = urlEncode(srcUrl);
        //encodedUrl: %E6%88%91%E7%9A%84java%E5%8A%A0%E5%AF%86%E8%AF%BE%E7%A8%8B
        System.out.println("encodedUrl: " + encodedUrl);

        String decodedUrl = urlDecode(encodedUrl);
        //decodedUrl: 我的java加密课程
        System.out.println("decodedUrl: " + decodedUrl);
    }

    private String urlEncode(String srcUrl) throws UnsupportedEncodingException {
        URLCodec urlCodec = new URLCodec();
        return urlCodec.encode(srcUrl, StandardCharsets.UTF_8.name());
    }

    private String urlDecode(String encodedUrl) throws Exception {
        URLCodec urlCodec = new URLCodec();
        return urlCodec.decode(encodedUrl, StandardCharsets.UTF_8.name());
    }

}
