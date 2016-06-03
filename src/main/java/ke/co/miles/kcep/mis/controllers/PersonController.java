/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ke.co.miles.kcep.mis.defaults.Controller;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ContactDetails;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.FarmerGroupDetails;
import ke.co.miles.kcep.mis.utilities.LocationDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetails;
import ke.co.miles.kcep.mis.utilities.SexDetail;

/**
 *
 * @author siech
 */
@WebServlet(name = "PersonController", urlPatterns = {"/addPerson"})
public class PersonController extends Controller {

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        bundle = ResourceBundle.getBundle("text", locale);

        //Get the user session
        HttpSession session = request.getSession(false);

        //Get the user path
        String path = request.getServletPath();
        String destination;

        switch (path) {

            case "/addPerson":

                ContactDetails contact = new ContactDetails();
                contact.setEmail(String.valueOf(request.getParameter("emailAddress")));
                contact.setPhone(String.valueOf(request.getParameter("phoneNumber")));
                contact.setPostalAddress(String.valueOf(request.getParameter("postalAddress")));

                FarmerGroupDetails farmerGroup = new FarmerGroupDetails();
                try {
                    farmerGroup.setId(Integer.valueOf(String.valueOf(request.getParameter("farmerGroup"))));
                } catch (Exception e) {
                    farmerGroup = null;
                }

                CountyDetails county = new CountyDetails();
                try {
                    county.setId(Integer.valueOf(String.valueOf(request.getParameter("county"))));
                } catch (Exception e) {
                    county = null;
                }

                LocationDetails location = new LocationDetails();
                location.setCounty(county);
                location.setSubCounty(String.valueOf(request.getParameter("subCounty")));
                location.setWard(String.valueOf(request.getParameter("ward")));
                LOGGER.log(Level.INFO, location.getWard());

                PersonRoleDetails personRole = new PersonRoleDetails();
                try {
                    personRole.setId(Integer.valueOf(String.valueOf(request.getParameter("personRole"))));
                } catch (Exception e) {
                    personRole = null;
                }

                PersonDetails person = new PersonDetails();
                person.setBusinessName(String.valueOf(request.getParameter("businessName")));

                person.setContact(contact);
                person.setFarmerGroup(farmerGroup);
                person.setLocation(location);
                person.setPersonRole(personRole);
                person.setName(String.valueOf(request.getParameter("name")));
                System.out.println(person.getName());
                person.setNationalId(String.valueOf(request.getParameter("nationalId")));
                if (person.getBusinessName().equals("null")) {
                    person.setBusinessName(null);
                }
                if (person.getNationalId().equals("null")) {
                    person.setNationalId(null);
                }
                if (person.getName().equals("null")) {
                    person.setName(null);
                }
                try {
                    person.setSex(SexDetail.getSexDetail(Short.valueOf(String.valueOf(request.getParameter("sex")))));
                } catch (Exception e) {
                    person.setSex(null);
                }

                try {
                    personService.addPerson(person);
                } catch (MilesException e) {
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.setContentType("text/html;charset=UTF-8");
                    response.getWriter().write(bundle.getString(e.getMessage()));
                    LOGGER.log(Level.INFO, bundle.getString(""), e);
                }

                return;
        }
        //Use request dispatcher to foward request internally
        destination = "/WEB-INF/views" + path + ".jsp";

        LOGGER.log(Level.INFO,
                "Request dispatch to forward to: {0}", destination);
        try {
            request.getRequestDispatcher(destination).forward(request, response);
        } catch (ServletException | IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(bundle.getString("redirection_failed"));
            LOGGER.log(Level.INFO, bundle.getString("redirection_failed"), e);

        }
    }

    private static final Logger LOGGER = Logger.getLogger(PersonController.class.getSimpleName());
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private CountyRequestsLocal countyService;

}
