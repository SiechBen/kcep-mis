/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "OfficerController", urlPatterns = {"/ward", "/kalro"})
public class OfficerController extends Controller {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = request.getLocale();
        bundle = ResourceBundle.getBundle("text", locale);

        //Get the user session
        HttpSession session = request.getSession(false);

        //Get the user path
        String path = request.getServletPath();
        String destination;

        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                if (rightsMap.equals("systemAdminSession") || rightsMap.equals("nationalOfficerSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/ward");
                        urlPaths.add("/kalro");
                    }
                } else if (rightsMap.equals("waoSession")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/ward");
                    }
                } else if (rightsMap.equals("kalroOfficer")) {
                    if (rightsMaps.get(rightsMap)) {
                        urlPaths.add("/kalro");
                    }
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {
                case "/ward":
                    path = "/ward_officer";
                    break;
                case "/kalro":
                    path = "/kalro_officer";
                    break;
            }
            //Use request dispatcher to foward request internally
            destination = "/WEB-INF/views" + path + ".jsp";

            LOGGER.log(Level.INFO, "Request dispatch to forward to: {0}", destination);
            try {
                request.getRequestDispatcher(destination).forward(request, response);
            } catch (ServletException | IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(bundle.getString("redirection_failed") + "<br>");
                LOGGER.log(Level.INFO, bundle.getString("redirection_failed"), e);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(bundle.getString("error_016_02") + "<br>");
            LOGGER.log(Level.INFO, bundle.getString("error_016_02"));
        }
    }

    private static final Logger LOGGER = Logger.getLogger(OfficerController.class.getSimpleName());

}
