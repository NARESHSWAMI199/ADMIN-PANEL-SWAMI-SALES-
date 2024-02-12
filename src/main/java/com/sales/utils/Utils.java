package com.sales.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class Utils {

    public static Long getCurrentMillis(){
        long millis = new java.util.Date().getTime();
        return millis;
    }


    public static String getMillisToDate(Long millis){
        DateFormat format = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date date = new Date(millis);
        return format.format((date));
    }


    public static boolean isEmpty(String string){
        return (string ==null || string.equals(""));
    }


    public static String aesEncrypt(String Data, String secretKey) {
        try {
            Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES"); // Default uses ECB PKCS5Padding
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = cipher.doFinal(Data.getBytes());
            String encryptedValue = java.util.Base64.getEncoder().encodeToString(encVal);
            return encryptedValue;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while encrypting: " + e.getMessage());
        }
        return null;
    }

    public static String aesDecrypt(String strToDecrypt, String secretKey) {
        try {
            Key key = new SecretKeySpec(secretKey.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error while encrypting: " + e.getMessage());
        }
        return null;
    }

}