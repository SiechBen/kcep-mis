/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.contact;

import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.Contact;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ContactDetails;

/**
 *
 * @author siech
 */
@Local
public interface ContactRequestsLocal {

    /**
     *
     * @param contactDetails details of the contact record to be created
     * @return unique identifier of the new record added
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public Contact addContact(ContactDetails contactDetails) throws MilesException;

}
