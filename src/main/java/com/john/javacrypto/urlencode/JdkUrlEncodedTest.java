package com.john.javacrypto.urlencode;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class JdkUrlEncodedTest {

    @Test
    public void testUrlEncode() throws UnsupportedEncodingException {
        String srcUrl = "我的java加密课程";

        String encodedUrl = urlEncode(srcUrl);
        //encodedUrl: %E6%88%91%E7%9A%84java%E5%8A%A0%E5%AF%86%E8%AF%BE%E7%A8%8B
        System.out.println("encodedUrl: " + encodedUrl);

        String decodedUrl = urlDecode(encodedUrl);
        //decodedUrl: 我的java加密课程
        System.out.println("decodedUrl: " + decodedUrl);
    }

    private String urlEncode(String srcUrl) throws UnsupportedEncodingException {
        return URLEncoder.encode(srcUrl, StandardCharsets.UTF_8.name());
    }

    private String urlDecode(String encodedUrl) throws UnsupportedEncodingException {
        return URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8.name());
    }
}
