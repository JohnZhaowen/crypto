package com.john.javacrypto.utils;

public class HexUtils {

    public static String converBytes2HexStr(byte[] bytes) {

        StringBuilder res = new StringBuilder();
        for (byte b : bytes) {
            //获取b的补码的后8位
            String hex = Integer.toHexString((int) b & 0xFF);
            if (hex.length() == 1) {
                res.append("0");
            }
            res.append(hex);
        }
        return res.toString();
    }
}
