package com.example.shop.base.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.*;
import java.util.logging.Logger;

public class MD5 {
    private static final Logger logger = Logger.getLogger("MD5");
    public static final String ALGORITHM = "MD5";

    public MD5() {
    }

    public static String toMD5(String plainText) {
        StringBuffer rlt = new StringBuffer();

        try {
            rlt.append(md5String(plainText.getBytes(BambooConstants.UTF_8)));
        } catch (UnsupportedEncodingException var3) {
            logger.severe(" CipherHelper toMD5 exception.");
            var3.printStackTrace();
        }

        return rlt.toString();
    }

    public static String getSignature(HashMap<String, String> params, String secret) {
        Map<String, String> sortedParams = new TreeMap(params);
        Set<Map.Entry<String, String>> entrys = sortedParams.entrySet();
        StringBuilder basestring = new StringBuilder();
        Iterator i$ = entrys.iterator();

        while (i$.hasNext()) {
            Map.Entry<String, String> param = (Map.Entry) i$.next();
            basestring.append((String) param.getKey()).append("=").append((String) param.getValue());
        }

        return getSignature(basestring.toString(), secret);
    }

    public static String getSignature(String sigstr, String secret) {
        StringBuilder basestring = new StringBuilder(sigstr);
        basestring.append("#");
        basestring.append(toMD5(secret));
        return toMD5(basestring.toString());
    }

    public static byte[] md5Raw(byte[] data) {
        Object var1 = null;

        byte[] md5buf;
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5buf = md5.digest(data);
        } catch (Exception var3) {
            md5buf = null;
            logger.severe("md5Raw error.");
            var3.printStackTrace();
        }

        return md5buf;
    }

    public static String md5String(byte[] data) {
        String md5Str = "";

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] buf = md5.digest(data);

            for (int i = 0; i < buf.length; ++i) {
                md5Str = md5Str + byte2Hex(buf[i]);
            }
        } catch (Exception var5) {
            md5Str = null;
            logger.severe("md5String error.");
            var5.printStackTrace();
        }

        return md5Str;
    }
    public static String byte2Hex(byte b) {
        String hex = Integer.toHexString(b);
        if (hex.length() > 2) {
            hex = hex.substring(hex.length() - 2);
        }

        while(hex.length() < 2) {
            hex = "0" + hex;
        }

        return hex;
    }

    public static String byte2Hex(byte[] bytes) {
        Formatter formatter = new Formatter();
        byte[] arr$ = bytes;
        int len$ = bytes.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            byte b = arr$[i$];
            formatter.format("%02x", b);
        }

        String hash = formatter.toString();
        formatter.close();
        return hash;
    }
}