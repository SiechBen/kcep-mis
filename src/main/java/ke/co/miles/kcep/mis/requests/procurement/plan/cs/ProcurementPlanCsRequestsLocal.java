/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.procurement.plan.cs;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanCsDetails;

/**
 *
 * @author siech
 */
@Local
public interface ProcurementPlanCsRequestsLocal {

    /**
     *
     * @param procurementPlanCsDetails details of the procurement plan cs record
     * to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addProcurementPlanCs(ProcurementPlanCsDetails procurementPlanCsDetails) throws MilesException;

    /**
     *
     * @return the list of procurement plan cs record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ProcurementPlanCsDetails> retrieveProcurementPlansCs() throws MilesException;

    /**
     *
     * @param id the unique identifier of the procurement plan cs record to be
     * retrieved
     * @return the details of the procurement plan cs record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ProcurementPlanCsDetails retrieveProcurementPlanCs(int id) throws MilesException;

    /**
     *
     * @param procurementPlanCsDetails details of the procurement plan cs record
     * to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editProcurementPlanCs(ProcurementPlanCsDetails procurementPlanCsDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the procurement plan cs record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeProcurementPlanCs(int id) throws MilesException;

}
