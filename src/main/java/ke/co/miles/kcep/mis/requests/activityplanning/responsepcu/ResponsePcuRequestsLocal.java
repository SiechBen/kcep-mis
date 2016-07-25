/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.responsepcu;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.ResponsePcu;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ResponsePcuDetails;

/**
 *
 * @author siech
 */
@Local
public interface ResponsePcuRequestsLocal {

    /**
     *
     * @param responsePcuDetails details of the response pcu record to be created
     * @return the unique identifier of the new record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public int addResponsePcu(ResponsePcuDetails responsePcuDetails) throws MilesException;

    /**
     *
     * @return the list of response pcu record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<ResponsePcuDetails> retrieveResponsePcuList() throws MilesException;

    /**
     *
     * @param id the unique identifier of the response pcu record to be retrieved
     * @return the details of the response pcu record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public ResponsePcuDetails retrieveResponsePcu(int id) throws MilesException;

    /**
     *
     * @param responsePcuDetails details of the response pcu record to be edited
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public void editResponsePcu(ResponsePcuDetails responsePcuDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the response pcu record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeResponsePcu(int id) throws MilesException;

    /**
     *
     * @param responsePcu the responsePcu to be converted
     * @return the details of the converted responsePcu
     */
    public ResponsePcuDetails convertResponsePcuToResponsePcuDetails(ResponsePcu responsePcu);

}
