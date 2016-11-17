package com.hgt.common.utils;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/******************************************************************************
 * Created by  Yao  on  2016/7/31
 * README:安全处理类，包括密码散列
 * ============================================================================
 * CHANGELOG：
 ******************************************************************************/
public class SecurityUtil {

    /**
     * 使用SHA-256加密
     * @param text
     * @return
     */
    public static String SHA256String(String text) {
        String input = text;
        String output = "";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes("UTF-8"));
            output = Hex.encodeHexString(hash);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return output;
    }
}
