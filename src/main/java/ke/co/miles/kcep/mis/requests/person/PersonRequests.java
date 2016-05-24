/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person;

import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PersonDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PersonRequests implements PersonRequestsLocal {

    @Override
    public PersonDetails retrievePerson(int personId) throws MilesException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PersonDetails> retrievePeople() throws MilesException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editPerson(PersonDetails personDetails) throws MilesException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removePerson(int personId) throws MilesException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//<editor-fold defaultstate="collapsed" desc="Create">  
    @Override
    public int addPerson(PersonDetails personDetails) throws MilesException {
        
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">
//</editor-fold>
}
