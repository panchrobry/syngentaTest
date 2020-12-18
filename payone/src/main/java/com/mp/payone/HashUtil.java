package com.mp.payone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HashUtil {
    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA384";

    public static String sha384(String message) throws NoSuchAlgorithmException, InvalidKeyException {
        String sharedKey = "y6SxHodRrtrKYBzQ";
        return sha384(message, sharedKey);
    }

    private static String sha384(String message, String sharedKey) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKeySpec signingKey = new SecretKeySpec(sharedKey.getBytes(), HMAC_SHA1_ALGORITHM);
        final Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        mac.init(signingKey);
        return bytesToHex(mac.doFinal(message.getBytes()));
    }

    private static String bytesToHex(final byte[] hash) {
        final StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            final String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

/*    public static void main(final String... args) throws NoSuchAlgorithmException, InvalidKeyException {
        final String message = "4757814993.11ccEURJurassicUTF-8147552test1203463414991234567preauthorization19";
        System.out.println(sha384(message));
    }*/
}
