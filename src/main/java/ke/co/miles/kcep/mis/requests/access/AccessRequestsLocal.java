/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.access;

import java.security.MessageDigest;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.AccessCredentials;

/**
 *
 * @author Anthony Kwaje
 */
@Local
public interface AccessRequestsLocal {

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws MilesException
     */
    public AccessCredentials addCredentials(String username, String password) throws MilesException;

    /**
     *
     * @param messageDigest
     * @param password
     * @return
     */
    public String generateSHAPassword(MessageDigest messageDigest, String password);

    /**
     *
     * @param username
     * @return
     * @throws MilesException
     */
    public String generateAnonymousIdentity(String username) throws MilesException;
}
