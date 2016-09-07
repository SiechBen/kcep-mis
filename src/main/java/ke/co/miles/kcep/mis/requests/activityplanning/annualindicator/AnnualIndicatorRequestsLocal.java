/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.annualindicator;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.AnnualIndicator;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.AnnualIndicatorDetails;
import ke.co.miles.kcep.mis.utilities.SubActivityDetails;

/**
 *
 * @author siech
 */
@Local
public interface AnnualIndicatorRequestsLocal {

    /**
     *
     * @param annualIndicatorDetails details of the annual indicator record to
     * be created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void addAnnualIndicator(AnnualIndicatorDetails annualIndicatorDetails) throws MilesException;

    /**
     *
     * @param annualIndicatorsDetailsList list of details of the annual
     * indicator records to be created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void addAnnualIndicators(List<AnnualIndicatorDetails> annualIndicatorsDetailsList) throws MilesException;

    /**
     *
     * @param subActivityId the unique identifier of the sub activity of which
     * the annualIndicators to be retrieved were involved
     * @return the list of annual indicator record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<AnnualIndicatorDetails> retrieveAnnualIndicators(int subActivityId) throws MilesException;

    /**
     *
     * @return the retrieved map of sub activity record details to
     * annualIndicators
     * @throws MilesException when the database is in an incorrect state
     */
    public HashMap<SubActivityDetails, List<AnnualIndicatorDetails>> retrieveSubActivities() throws MilesException;

    /**
     *
     * @param id the unique identifier of the annual indicator record to be
     * retrieved
     * @return the details of the annual indicator record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public AnnualIndicatorDetails retrieveAnnualIndicator(int id) throws MilesException;

    /**
     *
     * @param annualIndicatorDetails details of the annual indicator record to
     * be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editAnnualIndicator(AnnualIndicatorDetails annualIndicatorDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the annual indicator record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeAnnualIndicator(int id) throws MilesException;

    /**
     *
     * @param annualIndicator the annualIndicator to be converted
     * @return the details of the converted annualIndicator
     */
    public AnnualIndicatorDetails convertAnnualIndicatorToAnnualIndicatorDetails(AnnualIndicator annualIndicator);

}
