/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.planning.implementingpartner;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ImplementingPartner;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ImplementingPartnerDetails;

/**
 *
 * @author siech
 */
@Local
public interface ImplementingPartnerRequestsLocal {

    /**
     *
     * @param iplementingPartnerDetails details of the iplementing partner
     * record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addImplementingPartner(ImplementingPartnerDetails iplementingPartnerDetails) throws MilesException;

    /**
     *
     * @return the list of iplementing partner record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ImplementingPartnerDetails> retrieveImplementingPartners() throws MilesException;

    /**
     *
     * @param id the unique identifier of the iplementing partner record to be
     * retrieved
     * @return the details of the iplementing partner record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ImplementingPartnerDetails retrieveImplementingPartner(int id) throws MilesException;

    /**
     *
     * @param iplementingPartnerDetails details of the iplementing partner
     * record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editImplementingPartner(ImplementingPartnerDetails iplementingPartnerDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the iplementing partner record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeImplementingPartner(int id) throws MilesException;

    /**
     *
     * @param implementingPartner the iplementing partner to be converted
     * @return the details of the converted iplementing partner
     */
    public ImplementingPartnerDetails convertImplementingPartnerToImplementingPartnerDetails(ImplementingPartner implementingPartner);

}
