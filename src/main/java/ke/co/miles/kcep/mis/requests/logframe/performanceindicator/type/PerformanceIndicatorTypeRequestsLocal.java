/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.performanceindicator.type;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.PerformanceIndicatorType;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorTypeDetails;

/**
 *
 * @author siech
 */
@Local
public interface PerformanceIndicatorTypeRequestsLocal {

    /**
     *
     * @param performanceIndicatorTypeDetails details of the performanceIndicator type record to be
     * created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPerformanceIndicatorType(PerformanceIndicatorTypeDetails performanceIndicatorTypeDetails) throws MilesException;

    /**
     *
     * @return the list of performanceIndicator type record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<PerformanceIndicatorTypeDetails> retrievePerformanceIndicatorTypes() throws MilesException;

    /**
     *
     * @param id the unique identifier of the performanceIndicator type record to be
     * retrieved
     * @return the details of the performanceIndicator type record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public PerformanceIndicatorTypeDetails retrievePerformanceIndicatorType(int id) throws MilesException;

    /**
     *
     * @param performanceIndicatorTypeDetails details of the performanceIndicator type record to be
     * edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPerformanceIndicatorType(PerformanceIndicatorTypeDetails performanceIndicatorTypeDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the performanceIndicator type record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removePerformanceIndicatorType(int id) throws MilesException;

    /**
     *
     * @param performanceIndicatorType the performanceIndicatorType to be converted
     * @return the details of the converted performanceIndicatorType
     */
    public PerformanceIndicatorTypeDetails convertPerformanceIndicatorTypeToPerformanceIndicatorTypeDetails(PerformanceIndicatorType performanceIndicatorType);

}
