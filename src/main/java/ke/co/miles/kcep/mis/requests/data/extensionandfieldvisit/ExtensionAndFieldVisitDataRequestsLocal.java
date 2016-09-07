/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.data.extensionandfieldvisit;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ExtensionAndFieldVisitDataDetails;

/**
 *
 * @author siech
 */
@Local
public interface ExtensionAndFieldVisitDataRequestsLocal {

    /**
     *
     * @param extensionAndFieldVisitData details of the extension and field visit data record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addData(ExtensionAndFieldVisitDataDetails extensionAndFieldVisitData) throws MilesException;

    /**
     *
     * @return the list of extension and field visit data record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ExtensionAndFieldVisitDataDetails> retrieveData() throws MilesException;

    /**
     *
     * @param id the unique identifier of the extension and field visit data record to be retrieved
     * @return the details of the extension and field visit data record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ExtensionAndFieldVisitDataDetails retrieveData(int id) throws MilesException;

    /**
     *
     * @param extensionAndFieldVisitData details of the extension and field visit data record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editData(ExtensionAndFieldVisitDataDetails extensionAndFieldVisitData) throws MilesException;

    /**
     *
     * @param id the unique identifier of the extension and field visit data record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeData(int id) throws MilesException;

}
