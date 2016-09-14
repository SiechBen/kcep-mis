/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.contact;

import java.util.ArrayList;
import java.util.List;
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
            getEm().persist(contact);
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

        Contact contact = getEm().find(Contact.class, contactDetails.getId());
        contact.setEmail(contactDetails.getEmail());
        contact.setPhone(contactDetails.getPhone());
        contact.setPostalAddress(contactDetails.getPostalAddress());

        try {
            getEm().merge(contact);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeContact(int id) throws MilesException {
        Contact contact = getEm().find(Contact.class, id);
        try {
            getEm().remove(contact);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ContactDetails convertContactToContactDetails(Contact contact) {

        ContactDetails contactDetails = new ContactDetails(contact.getId());
        contactDetails.setEmail(contact.getEmail());
        contactDetails.setPhone(contact.getPhone());
        contactDetails.setPostalAddress(contact.getPostalAddress());
        return contactDetails;

    }

    private List<ContactDetails> convertContactsToContactDetailsList(List<Contact> contacts) {

        List<ContactDetails> contactDetailsList = new ArrayList<>();
        for (Contact contact : contacts) {
            contactDetailsList.add(convertContactToContactDetails(contact));

        }
        return contactDetailsList;

    }

//</editor-fold>
}
