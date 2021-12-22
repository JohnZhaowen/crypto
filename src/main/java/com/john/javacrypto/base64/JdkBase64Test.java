package com.john.javacrypto.base64;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class JdkBase64Test {

    @Test
    public void jdkBase64Encode() {

        String srcStr = "我的java加密课程";

        String encodedStr = jdkBase64Encode(srcStr);
        System.out.println("jdk base64 encoded str: " + encodedStr);

        String decodedStr = jdkBase64Decode(encodedStr);
        System.out.println("jdk base64 decoded str: " + decodedStr);

    }


    private String jdkBase64Encode(String srcStr){
        return Base64.getEncoder().encodeToString(srcStr.getBytes(StandardCharsets.UTF_8));
    }

    private String jdkBase64Decode(String encodedString){
        byte[] decodeBytes = Base64.getDecoder().decode(encodedString.getBytes(StandardCharsets.UTF_8));
        return new String(decodeBytes, StandardCharsets.UTF_8);
    }

}
