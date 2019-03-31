package com.niuzj.md5;

import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Test {

    private static final String[] hexDegits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * Java自带工具实现MD5加密
     */
    @Test
    public void test01() throws Exception {
        //创建一个消息摘要实例
        MessageDigest digest = MessageDigest.getInstance("MD5");
        //把要加密的数据设置进实例
        digest.update("Niuzheju".getBytes("utf-8"));
        //获取加密的二进制数据
        byte[] bytes = digest.digest();
        //利用BigInteger把加密后的二进制数据转换为字符串
        //把一个字节数组构造为BigInteger,符号为正
        BigInteger bigInteger = new BigInteger(1, bytes);
        System.out.println(bigInteger);
        System.out.println(bigInteger.toString(16));
    }

    /**
     * 自己把加密后的字节转换为十六进制
     */
    @Test
    public void test02() throws Exception {
        String md5 = getMD5("Niuzheju", "");
        System.out.println(md5);
        System.out.println(md5.length());
    }

    @Test
    public void test03(){
        byte a = (byte) (-123 + 251);
        byte b = -123, c = (byte) 256;
//        if (b + c > 127){
//            -128 + (c - (127 - b) - 1)
//                     ((c - 127 + b) % 256 - 129)
//        }
        System.out.println(a);
    }

    /**
     * 对指定字符串进行加密
     * @param str 原字符串
     * @param charset 原字符串编码
     * @return 加密后的md5字符串
     */
    public String getMD5(String str, String charset) throws Exception {
        if (str == null || "".equals(str)){
            return null;
        }
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        String md5;
        if (charset == null || "".equals(charset)){
            md5 = byteArrayToHexString(messageDigest.digest(str.getBytes()));
        } else {
            md5 = byteArrayToHexString(messageDigest.digest(str.getBytes(charset)));
        }
        return md5;
    }

    /**
     * 把一个字节数组转换为十六进制字符串
     */
    private String byteArrayToHexString(byte[] bytes){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(byteToHexString(bytes[i]));
        }
        return sb.toString();
    }


    /**
     * 把一个字节变为两个十六进制字符串
     * byte范围-128 127,注意运算时不要超出范围
     */
    private String byteToHexString(byte by){
        byte b = by;
        if (b < 0){
            b *= -1;
        }
        int i = b / 8;
        int j = b % 8;
        return hexDegits[i] + hexDegits[j];
    }
}
