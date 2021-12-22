package com.john.javacrypto.asymmetric;

import org.apache.commons.codec.binary.Hex;

import java.security.PrivateKey;
import java.security.Signature;

public class SignatureTest {

    private static final String ALGO = "sha256withrsa";

    public static void main(String[] args) throws Exception{
        String content = "你好，hellow world!";
        byte[] contentBytes = content.getBytes();
        String sign = sign(contentBytes);
        //签名：5c9668d62a0affd74cc036450ac2fe18610f759d60d42bf1234ec4a521b906ecc74694aa71f8ecfb8e92108739290ccd6161ef9e75b1f44d799d1e9ce8d037e12c1c95e14190d54b532f97d3a65d6f06dc5f50ef9af718fb8357f3537458d96cb0368b87b7a878aefd18d923c592449755fce4c1c9b41e3a3303d3403fe7b5ca
        System.out.println("签名：" + sign);
        //验证结果：true
        boolean status = verify(contentBytes, sign);
        System.out.println("验证结果：" + status);
    }

    private static String sign(byte[] data) throws Exception{
        PrivateKey privateKey = RsaTest.getPrivateKey();
        Signature signature = Signature.getInstance(ALGO);

        signature.initSign(privateKey);
        signature.update(data);
        return Hex.encodeHexString(signature.sign());
    }

    private static boolean verify(byte[] data, String sign) throws Exception{
        Signature signature = Signature.getInstance(ALGO);
        signature.initVerify(RsaTest.getPublicKey());
        signature.update(data);
        return signature.verify(Hex.decodeHex(sign));
    }
}
