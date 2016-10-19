/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.sub;

import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.SubActivity;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ComponentDetails;
import ke.co.miles.kcep.mis.utilities.FinancialPlanDetails;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;

/**
 *
 * @author siech
 */
@Local
public interface SubActivityRequestsLocal {

    /**
     *
     * @param subActivityDetails details of the sub activity record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addSubActivity(SubActivityDetails subActivityDetails) throws MilesException;

    /**
     *
     * @return the list of sub activity record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubActivityDetails> retrieveHeadSubActivities() throws MilesException;

    /**
     *
     * @param countyId the unique identifier of the county of which the county
     * desk officer mans
     * @return the list of sub activity record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubActivityDetails> retrieveCountySubActivities(short countyId) throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub activity record to be
     * retrieved
     * @return the details of the sub activity record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public SubActivityDetails retrieveSubActivity(int id) throws MilesException;

    /**
     *
     * @param subActivityDetails details of the sub activity record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editSubActivity(SubActivityDetails subActivityDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the sub activity record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeSubActivity(int id) throws MilesException;

    /**
     *
     * @param subActivity the record to be converted to activity subActivity
     * details
     * @return the details resulting from the conversion
     */
    public SubActivityDetails convertSubActivityToSubActivityDetails(SubActivity subActivity);

    /**
     *
     * @param financialYearId the unique identifier of the financial year for
     * which the financial plan by expenditure categories report is to be
     * generated
     * @return the map of generated financial plan data
     * @throws MilesException when the database is in an incorrect
     */
    public Map<FinancialPlanDetails, Map<PhenomenonDetails, FinancialPlanDetails>>
            summarizeFinancialPlanByCategories(short financialYearId) throws MilesException;

    /**
     *
     * @param financialYearId the unique identifier of the financial year for
     * which the financial plan by components report is to be generated
     * @return the map of generated financial plan data
     * @throws MilesException when the database is in an incorrect
     */
    public Map<FinancialPlanDetails, Map<ComponentDetails, FinancialPlanDetails>>
            summarizeFinancialPlanByComponents(short financialYearId) throws MilesException;

    /**
     *
     * @param financialYearId the financial year to which the sub-activities to
     * be retrieved belong
     * @return the list of sub-activities retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<SubActivityDetails> retrieveSubActivities(short financialYearId) throws MilesException;

    /**
     *
     * @return the list of awpb reference codes
     * @throws MilesException when the database is in an incorrect state
     */
    public List<String> retrieveReferenceCodes() throws MilesException;

}
