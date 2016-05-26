/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.contact;

import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Contact;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ContactDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ContactRequests extends EntityRequests implements ContactRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public Contact addContact(ContactDetails contactDetails) throws MilesException {

        if (contactDetails == null) {
            throw new InvalidArgumentException("error_002_01");
        }

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
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editContact(ContactDetails contactDetails) throws MilesException {

        if (contactDetails == null) {
            throw new InvalidArgumentException("error_002_01");
        }

        Contact contact = em.find(Contact.class, contactDetails.getId());
        contact.setId(contactDetails.getId());
        contact.setEmail(contactDetails.getEmail());
        contact.setPhone(contactDetails.getPhone());
        contact.setPostalAddress(contactDetails.getPostalAddress());

        try {
            em.merge(contact);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeContact(int id) throws MilesException {
        Contact contact = em.find(Contact.class, id);
        try {
            em.remove(contact);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
}
