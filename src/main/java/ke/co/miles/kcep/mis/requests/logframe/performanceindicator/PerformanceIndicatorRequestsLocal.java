/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.logframe.performanceindicator;

import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.PerformanceIndicator;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PerformanceIndicatorDetails;

/**
 *
 * @author siech
 */
@Local
public interface PerformanceIndicatorRequestsLocal {

    /**
     *
     * @param performanceIndicatorDetails details of the performance indicator
     * record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addPerformanceIndicator(PerformanceIndicatorDetails performanceIndicatorDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the performance indicator record to be
     * retrieved
     * @return the details of the performance indicator record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public PerformanceIndicatorDetails retrievePerformanceIndicator(int id) throws MilesException;

    /**
     *
     * @param performanceIndicatorDetails details of the performance indicator
     * record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editPerformanceIndicator(PerformanceIndicatorDetails performanceIndicatorDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the performance indicator record to be
     * removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removePerformanceIndicator(int id) throws MilesException;

    /**
     *
     * @param performanceIndicator the performance indicator record to be
     * converted to performance indicator details
     * @return the result of the conversion
     */
    public PerformanceIndicatorDetails convertPerformanceIndicatorToPerformanceIndicatorDetails(PerformanceIndicator performanceIndicator);
}
