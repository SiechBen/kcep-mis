/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ke.co.miles.kcep.mis.defaults.Controller;

/**
 *
 * @author siech
 */
@WebServlet(name = "FileController", urlPatterns = {"/download", "/downloadPeopleUploadFile"})
public class FileController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(false);
        setBundle(ResourceBundle.getBundle("text", request.getLocale()));

        OutputStream outStream = response.getOutputStream();
        ServletContext context = getServletContext();
        String mimeType, headerKey, headerValue;
        String path = request.getServletPath();
        FileInputStream inStream;
        String filePath = "";
        File downloadFile;

        switch (path) {
            case "/download":

                filePath = request.getParameter("filePath");

                break;

            case "/downloadPeopleUploadFile":

                filePath = getServletContext().getRealPath("/") + "documents"
                        + fileSeparator + "population" + fileSeparator + "download_files"
                        + fileSeparator + "people-upload-file.xlsx";

        }

        try {
            downloadFile = new File(filePath);
            inStream = new FileInputStream(downloadFile);

            mimeType = context.getMimeType(filePath);
            if (mimeType == null) {
                mimeType = "application/octet-stream";
            }

//                    response.setHeader("Content-Length", String.valueOf(downloadFile.length()));
            response.setContentLength((int) downloadFile.length());
            response.setContentType(mimeType);

            headerKey = "Content-Disposition";
            /* restricts browser to downloading the file */
//                    headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            /* allows browser to open the file for viewing */
            headerValue = String.format("inline; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            final int startOffset = 0;
            final byte[] buffer = new byte[4096];
            while (inStream.read(buffer) != -1) {
                outStream.write(buffer, startOffset, buffer.length);
            }

            outStream.flush();
            outStream.close();
            inStream.close();

        } catch (FileNotFoundException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            outStream.write((getBundle().getString("file_not_found_error") + "<br>").getBytes());
            LOGGER.log(Level.INFO, getBundle().getString("file_not_found_error"));
        }

    }

    private static final Logger LOGGER = Logger.getLogger(FileController.class.getSimpleName());

}
