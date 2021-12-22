package com.john.javacrypto.asymmetric;

import org.apache.commons.codec.binary.Base64;

import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class NumberCertificateTest {

    public static void main(String[] args) throws Exception {
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        String filePah = "D:/Java/workspace/java-crypto/src/main/resources/test.cer";

        FileInputStream in = new FileInputStream(filePah);
        Certificate certificate = cf.generateCertificate(in);
        PublicKey publicKey = certificate.getPublicKey();
        String base64PublicKey = Base64.encodeBase64String(publicKey.getEncoded());
        //解析数字证书中的公钥为： MHYwEAYHKoZIzj0CAQYFK4EEACIDYgAER0UOlvt9Xb/pOdEh+J8LttV7HpI6SFkc8GIxLcB6KP4ap1yztsyX50XUWPrRd21DosCHZTQKH3rd6zwzocWdTaRvQZU4f8kehOvRnkmSh5SHDDqFSmafnVmTTZdhBoZK
        System.out.println("解析数字证书中的公钥为： " + base64PublicKey);
    }
}
