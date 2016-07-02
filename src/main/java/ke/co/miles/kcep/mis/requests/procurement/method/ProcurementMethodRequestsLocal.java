/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.procurement.method;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ProcurementMethod;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ProcurementMethodDetails;

/**
 *
 * @author siech
 */
@Local
public interface ProcurementMethodRequestsLocal {

    /**
     *
     * @param procurementMethodDetails details of the procurement method record
     * to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addProcurementMethod(ProcurementMethodDetails procurementMethodDetails) throws MilesException;

    /**
     *
     * @return the list of procurement method record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ProcurementMethodDetails> retrieveProcurementMethods() throws MilesException;

    /**
     *
     * @param id the unique identifier of the procurement method record to be
     * retrieved
     * @return the details of the procurement method record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ProcurementMethodDetails retrieveProcurementMethod(int id) throws MilesException;

    /**
     *
     * @param procurementMethodDetails details of the procurement method record
     * to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editProcurementMethod(ProcurementMethodDetails procurementMethodDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the procurement method record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeProcurementMethod(int id) throws MilesException;

    /**
     *
     * @param procurementMethod the procurement method record to be converted to
     * record details
     * @return the resulting details
     */
    public ProcurementMethodDetails
            convertProcurementMethodToProcurementMethodDetails(ProcurementMethod procurementMethod);

}
