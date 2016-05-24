/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.contact;

import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Contact;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ContactDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ContactRequests extends EntityRequests implements ContactRequestsLocal {

    @Override
    public Contact addContact(ContactDetails contactDetails) throws MilesException {

        Contact contact = new Contact();
        contact.setEmail(contactDetails.getEmail());
        contact.setPhone(contactDetails.getPhone());
        contact.setPostalAddress(contactDetails.getPostalAddress());

        try {
            em.persist(contact);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return contact;
    }

}
