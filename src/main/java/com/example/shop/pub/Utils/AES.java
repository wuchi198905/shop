package com.example.shop.pub.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    private static Logger logger = LoggerFactory.getLogger(AES.class);

    /*
     * 已确认 加密用的Key 可以用26个字母和数字组成 此处使用AES-128-CBC加密模式，key需要为16位。
     * 偏移量     L+\\~f4,Ir)b$=pkf
     */
    private static String IV_PARAMETER = "L+\\~f4,Ir)b$=pkf";
    private static String ENCODING = "utf-8";

    // 加密
    public static String encrypt(String sSrc) {
        try {
            String sKey="LdfbAo1S8Dggjj==";
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");

            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            // 使用BASE64做转码
            return encodeBase64(cipher.doFinal(sSrc.getBytes(ENCODING)));
        } catch (Exception e) {
            logger.error("AES加密出错", e);
            return null;
        }
    }

    // 解密
    public static String decrypt(String sSrc) {
        try {
            String sKey="LdfbAo1S8Dggjj==";
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAMETER.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = decodeBase64(sSrc);// 先用base64解密
            //System.out.println("base64解密1：++"+encrypted1);
            byte[] original = cipher.doFinal(encrypted1);
            //System.out.println("base64解密2：++"+original);
            String originalString = new String(original, ENCODING);
            //System.out.println("base64解密3：++"+originalString);
            return originalString;
        } catch (Exception e) {
            logger.error("AES解密出错", e);
            return null;
        }
    }

    public static void main(String[] args) throws Exception {
//        // 需要加密的字串
//        String cSrc = "123456";
//        System.out.println("加密前的字串是：" + cSrc);
////		// 加密
//        String enString = AES.encrypt(cSrc, "");
//        //System.out.println("加密后的字串是：" + enString);
//        //System.out.println("4M8S3C2Rm+IBPe6Yh0Ll8w==".equals(enString));
//
//        // 解密
//        String DeString = AES.decrypt("4M8S3C2Rm+IBPe6Yh0Ll8w==", Constant.AES_KEY);
//        //System.out.println("解密后的字串是：" + DeString);
    }

    /***
     * encode by Base64
     */
    public static String encodeBase64(byte[] input) throws Exception {
        Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("encode", byte[].class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, new Object[] { input });
        return (String) retObj;
    }

    /***
     * decode by Base64
     */
    public static byte[] decodeBase64(String input) throws Exception {
        Class<?> clazz = Class.forName("com.sun.org.apache.xerces.internal.impl.dv.util.Base64");
        Method mainMethod = clazz.getMethod("decode", String.class);
        mainMethod.setAccessible(true);
        Object retObj = mainMethod.invoke(null, input);
        return (byte[]) retObj;
    }

}
