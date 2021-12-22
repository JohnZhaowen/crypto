package com.john.javacrypto.base64;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.nio.charset.StandardCharsets;

public class CommonsCodecBase64Test {

    @Test
    public void commonsCodecBase64Encode() {

        String srcStr = "我的java加密课程";

        String encodedStr = commonsCodecBase64Encode(srcStr);
        System.out.println("commonsCodec base64 encoded str: " + encodedStr);

        String decodedStr = commonsCodecBase64Decode(encodedStr);
        System.out.println("commonsCodec base64 decoded str: " + decodedStr);

    }


    private String commonsCodecBase64Encode(String srcStr){
        return Base64.encodeBase64String(srcStr.getBytes(StandardCharsets.UTF_8));
    }

    private String commonsCodecBase64Decode(String encodedString){
        byte[] decodeBytes = Base64.decodeBase64(encodedString.getBytes(StandardCharsets.UTF_8));
        return new String(decodeBytes, StandardCharsets.UTF_8);
    }

}
