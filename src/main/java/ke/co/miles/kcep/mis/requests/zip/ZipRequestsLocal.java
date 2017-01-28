/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.zip;

import java.io.FileNotFoundException;
import javax.ejb.Local;

/**
 *
 * @author siech
 */
@Local
public interface ZipRequestsLocal {

    /**
     *
     * @param sourceFolder
     * @param destinationZipFile
     * @throws FileNotFoundException
     * @throws Exception
     */
    public void zipFolder(String sourceFolder, String destinationZipFile) throws FileNotFoundException, Exception;

}
