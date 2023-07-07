package com.clear2pay.labs.json;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class JavaUtils {
    public static void main1(String[] args) {
        System.out.println(Integer.toHexString(0x100 | 42).substring(1));
        System.out.println(String.format("%08X", (0xFF & 1111111111)));

    }

    public static void main(String[] args) {
        SimpleDateFormat formatter = new SimpleDateFormat("MMddYY");
        UUID uid = UUID.randomUUID();
        System.out.println(uid.toString());

        try {
            MessageDigest salt = MessageDigest.getInstance("MD5");
            salt.update(uid.toString().getBytes("UTF-8"));
            String digest = bytesToHex(salt.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }



        String uuid = uid.toString();
        String referenceNum = "586" + formatter.format(new Date()) + uuid.substring(0, 7);

        System.out.println(referenceNum);



    }

    public static String bytesToHex(byte[] ba)
    {
        StringBuilder hex = new StringBuilder(ba.length * 2);

        for (byte b : ba){
            hex.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1).toUpperCase());
        }
        return hex.toString();
    }
}
