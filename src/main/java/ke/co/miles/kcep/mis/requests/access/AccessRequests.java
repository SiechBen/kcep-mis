/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.access;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.AccessCredentials;

/**
 *
 * @author Anthony Kwaje
 * @author -editor Ben Siech
 */
@Stateless
public class AccessRequests extends EntityRequests implements AccessRequestsLocal {

    @Override
    public AccessCredentials addCredentials(String username, String password) throws MilesException {

        //Hash the password
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            throw new MilesException("error_016_01");
        }
        String hashedPassword = generateSHAPassword(messageDigest, password);

        //Create and populate the access credential data
        AccessCredentials credentials = new AccessCredentials();
        credentials.setUsername(username);
        credentials.setPassword(password);
        credentials.setHashedPassword(hashedPassword);

        //Return
        return credentials;

    }

    /**
     * Generate a random String suitable for use as a temporary password.
     *
     * @return String suitable for use as a temporary password
     */
    private String generateRandomPassword() {

        // Pick from some characters that won't be easily mistaken for each
        // other. So, for example, omit o O and 0, 1 l and L.
        String characters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

        String password = "";
        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = (int) (new Random().nextDouble() * characters.length());
            password += characters.substring(index, index + 1);
        }
        return password;
    }

    private String generateSaltedSHAPassword(MessageDigest messageDigest, String password, String salt) {
        String generatedPassword;
        messageDigest.update(salt.getBytes());
        byte[] bytes = messageDigest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        generatedPassword = sb.toString();
        return generatedPassword;
    }

    @Override
    public String generateSHAPassword(MessageDigest messageDigest, String password) {
        messageDigest.update(password.getBytes());
        byte byteData[] = messageDigest.digest();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            stringBuilder.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        //Return
        return stringBuilder.toString();
    }

    @Override
    public String generateAnonymousIdentity(String username) throws MilesException {
        //Define the hashing algorithm
        LOGGER.log(Level.INFO, "Defining the hashing algorithm");
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
        } catch (Exception e) {
            throw new MilesException("error_016_01");
        }

        //Generate the anonymous identity
        LOGGER.log(Level.INFO, "Generating the anonymous identity");
        messageDigest.update(username.getBytes());
        byte byteData[] = messageDigest.digest();
        StringBuilder anonymousIdentity = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            anonymousIdentity.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        //State characters that will be used to generate the anonymous identity
        LOGGER.log(Level.INFO, "Stating characters that will be used to generate the anonymous identity");

        //Return the anonymous identity
        LOGGER.log(Level.INFO, "Returning the anonymous identity");
        return anonymousIdentity.toString();
    }

    private String generateSalt() throws NoSuchAlgorithmException {

        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Arrays.toString(salt);
    }

    private final int PASSWORD_LENGTH = 8;
    private static final Logger LOGGER = Logger.getLogger(AccessRequests.class.getSimpleName());

}
