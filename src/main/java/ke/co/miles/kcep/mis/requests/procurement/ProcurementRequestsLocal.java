/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.procurement;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ProcurementDetails;

/**
 *
 * @author siech
 */
@Local
public interface ProcurementRequestsLocal {

    /**
     *
     * @param procurementDetails details of the procurement record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addProcurement(ProcurementDetails procurementDetails) throws MilesException;

    /**
     *
     * @return the list of procurement record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ProcurementDetails> retrieveProcurements() throws MilesException;

    /**
     *
     * @param id the unique identifier of the procurement record to be retrieved
     * @return the details of the procurement record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ProcurementDetails retrieveProcurement(int id) throws MilesException;

    /**
     *
     * @param procurementDetails details of the procurement record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editProcurement(ProcurementDetails procurementDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the procurement record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeProcurement(int id) throws MilesException;

}
