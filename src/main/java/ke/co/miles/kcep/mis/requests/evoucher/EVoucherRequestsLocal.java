/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.evoucher;

import java.util.List;
import javax.ejb.Local;
import ke.co.miles.kcep.mis.entities.EVoucher;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.EVoucherDetails;

/**
 *
 * @author siech
 */
@Local
public interface EVoucherRequestsLocal {

    /**
     *
     * @param eVoucherDetails details of the E-voucher record to be created
     * @return the created record
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public EVoucher addEVoucher(EVoucherDetails eVoucherDetails) throws MilesException;

    /**
     *
     * @return the list of E-voucher record details retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public List<EVoucherDetails> retrieveEVouchers() throws MilesException;

    /**
     *
     * @param id the unique identifier of the E-voucher record to be retrieved
     * @return the details of the E-voucher record retrieved
     * @throws MilesException when the database is in an incorrect state
     */
    public EVoucherDetails retrieveEVoucher(int id) throws MilesException;

    /**
     *
     * @param eVoucherDetails details of the E-voucher record to be edited
     * @return the E-voucher record created
     * @throws MilesException when the database is in an incorrect state or when
     * the details are null or incorrectly specified
     */
    public EVoucher editEVoucher(EVoucherDetails eVoucherDetails) throws MilesException;

    /**
     *
     * @param id the unique identifier of the E-voucher record to be removed
     * @throws MilesException when the database is in an incorrect state
     */
    public void removeEVoucher(int id) throws MilesException;

    /**
     *
     * @param eVoucher the E-voucher to be converted to details
     * @return the details of the converted E-voucher
     */
    public EVoucherDetails convertEVoucherToEVoucherDetails(EVoucher eVoucher);

}
