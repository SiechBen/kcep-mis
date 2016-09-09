/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.defaults;

import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author siech
 */
public class Default {

    public static String generateRandomPhoneNumber(Integer id) {
        String reversedTime = new StringBuilder(String.valueOf(new Date().getTime())).reverse().toString();
        Random random = new Random();
        String randomPhoneNumber = "0" + "7" + id + reversedTime.substring(random.nextInt(3), random.nextInt(7) + 7) + random.nextInt(id) + random.nextInt(id);

        return randomPhoneNumber.length() > 11 ? randomPhoneNumber.substring(0, 11) : randomPhoneNumber;
    }

    public static String generateSHAPassword(MessageDigest messageDigest, String password) {
        messageDigest.update(password.getBytes());
        byte byteData[] = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            stringBuilder.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //Return the sha password
        return stringBuilder.toString();
    }

    public static String generateRandomEmailAddress(String likelyEmail) {
        String reversedTime = new StringBuilder(String.valueOf(new Date().getTime())).reverse().toString();
        String randomEmail;
        if (likelyEmail != null) {
            randomEmail = likelyEmail.replace("@", reversedTime.substring(2, 4) + "@").replace("'", "");
        } else {
            randomEmail = generateRandomPassword() + reversedTime.substring(2, 4) + "@".replace(" ", "");
        }

        return randomEmail;
    }

    private static String generateRandomPassword() {

        // Pick from some letters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L, s S and 5.
        String letters = "abcdefghjkmnpqrtuvwxyzABCDEFGHJKMNPQRTUVWXYZ2346789+@";

        String password = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (new Random().nextDouble() * letters.length());
            password += letters.substring(index, index + 1);
        }
        return password;
    }

    private static final int PASSWORD_LENGTH = 8;

}
