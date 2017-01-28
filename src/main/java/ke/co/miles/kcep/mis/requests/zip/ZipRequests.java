/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.ejb.Stateless;

/**
 *
 * @author siech
 */
@Stateless
public class ZipRequests implements ZipRequestsLocal {

    //<editor-fold defaultstate="collapsed" desc="Zip folder">
    @Override
    public void zipFolder(String sourceFolder, String destinationZipFile) throws FileNotFoundException, Exception {
        LOGGER.log(Level.INFO, "Inside the method for zipping a folder");

        LOGGER.log(Level.INFO, "Creating required objects for zipping");
        ZipOutputStream zip;
        FileOutputStream fileWriter;

        fileWriter = new FileOutputStream(destinationZipFile);
        zip = new ZipOutputStream(fileWriter);

        LOGGER.log(Level.INFO, "Zipping the contents specified");
        addFolderToZip("", sourceFolder, zip);

        LOGGER.log(Level.INFO, "Commiting the zipping done");
        zip.flush();
        zip.close();
        LOGGER.log(Level.INFO, "\n\n\033[32;3m Zipping completed successfully\n\n");

    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Add files to zip">

    private void addFileToZip(String path, String sourceFile, ZipOutputStream zipper) throws Exception {
        LOGGER.log(Level.FINE, "Inside the method for adding a file to a zip");

        LOGGER.log(Level.FINE, "Creating required objects for zipping");
        File folder = new File(sourceFile);
        if (folder.isDirectory()) {
            LOGGER.log(Level.INFO, "Adding this directory to zip: {0}", folder.getName());
            addFolderToZip(path, sourceFile, zipper);
        } else {
            LOGGER.log(Level.INFO, "Adding this file to zip: {0}", folder.getName());
            byte[] buffer = new byte[1024];
            int bytesRead;
            FileInputStream in = new FileInputStream(sourceFile);
            zipper.putNextEntry(new ZipEntry(path + "/" + folder.getName()));
            while ((bytesRead = in.read(buffer)) > 0) {
                zipper.write(buffer, 0, bytesRead);
            }
            LOGGER.log(Level.INFO, "File zipped successfully: {0}", folder.getName());
        }
    }

    private void addFolderToZip(String path, String srcFolder, ZipOutputStream zipper) throws Exception {
        LOGGER.log(Level.FINE, "Inside the method for adding a folder to a zip");

        LOGGER.log(Level.INFO, "Obtaining the contents of this directory: {0}", srcFolder);
        File folder = new File(srcFolder);

        for (String fileName : folder.list()) {
            if (path.equals("")) {
                LOGGER.log(Level.INFO, "Adding this content to a zip: {0}", fileName);
                addFileToZip(folder.getName(), srcFolder + "/" + fileName, zipper);
            } else {
                LOGGER.log(Level.INFO, "Adding this content to a zip: {0}", fileName);
                addFileToZip(path + "/" + folder.getName(), srcFolder + "/" + fileName, zipper);
            }
        }
    }
    //</editor-fold>

    private static final Logger LOGGER = Logger.getLogger(ZipRequests.class.getSimpleName());

}
