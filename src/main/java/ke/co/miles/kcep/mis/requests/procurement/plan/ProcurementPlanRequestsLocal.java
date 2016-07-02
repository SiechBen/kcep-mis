/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.procurement.plan;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ProcurementPlanDetails;

/**
 *
 * @author siech
 */
@Local
public interface ProcurementPlanRequestsLocal {

    /**
     *
     * @param procurementPlanDetails details of the procurement plan record to
     * be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addProcurementPlan(ProcurementPlanDetails procurementPlanDetails) throws MilesException;

    /**
     *
     * @return the list of procurement plan record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ProcurementPlanDetails> retrieveProcurementPlans() throws MilesException;

    /**
     *
     * @param id the unique identifier of the procurement plan record to be
     * retrieved
     * @return the details of the procurement plan record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ProcurementPlanDetails retrieveProcurementPlan(int id) throws MilesException;

    /**
     *
     * @param procurementPlanDetails details of the procurement plan record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editProcurementPlan(ProcurementPlanDetails procurementPlanDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the procurement plan record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeProcurementPlan(int id) throws MilesException;

}
