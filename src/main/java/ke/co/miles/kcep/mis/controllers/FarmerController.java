/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.controllers;

/**
 *
 * @author siech
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import ke.co.miles.kcep.mis.requests.account.AccountRequestsLocal;
import ke.co.miles.kcep.mis.requests.farmer.farmactivity.FarmActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.farmer.inputscollection.InputsCollectionRequestsLocal;
import ke.co.miles.kcep.mis.requests.farmer.loan.LoanRequestsLocal;
import ke.co.miles.kcep.mis.requests.input.staticinput.StaticInputRequestsLocal;
import ke.co.miles.kcep.mis.requests.input.type.InputTypeRequestsLocal;
import ke.co.miles.kcep.mis.requests.input.variety.InputVarietyRequestsLocal;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.AccountDetails;
import ke.co.miles.kcep.mis.utilities.FarmActivityDetails;
import ke.co.miles.kcep.mis.utilities.InputTypeDetails;
import ke.co.miles.kcep.mis.utilities.InputVarietyDetails;
import ke.co.miles.kcep.mis.utilities.InputsCollectionDetails;
import ke.co.miles.kcep.mis.utilities.LoanDetails;
import ke.co.miles.kcep.mis.utilities.PersonDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;
import ke.co.miles.kcep.mis.utilities.StaticInputDetails;

@WebServlet(name = "FarmerController", urlPatterns = {"/farm", "/doAddLoan", "/doAddInputsCollection", "/doAddFarmActivity", "/updateStaticInputs", "/updateInputVarieties"})
public class FarmerController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    @SuppressWarnings("unchecked")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession();

        String path = request.getServletPath();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalOfficerSession":
                    case "equityPersonnelSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddLoan");
                            urlPaths.add("/updateStaticInputs");
                            urlPaths.add("/updateInputVarieties");
                            urlPaths.add("/doAddFarmActivity");
                            urlPaths.add("/doAddInputsCollection");
                            switch (path) {
                                case "/farm":
                                    path = "/head_farm";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "kalroSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddLoan");
                            urlPaths.add("/updateStaticInputs");
                            urlPaths.add("/updateInputVarieties");
                            urlPaths.add("/doAddFarmActivity");
                            urlPaths.add("/doAddInputsCollection");
                            switch (path) {
                                case "/farm":
                                    path = "/kalro_farm";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "regionalCoordinatorSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddLoan");
                            urlPaths.add("/updateStaticInputs");
                            urlPaths.add("/updateInputVarieties");
                            urlPaths.add("/doAddFarmActivity");
                            urlPaths.add("/doAddInputsCollection");
                            switch (path) {
                                case "/farm":
                                    path = "/region_farm";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "countyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddLoan");
                            urlPaths.add("/updateStaticInputs");
                            urlPaths.add("/updateInputVarieties");
                            urlPaths.add("/doAddFarmActivity");
                            urlPaths.add("/doAddInputsCollection");
                            switch (path) {
                                case "/farm":
                                    path = "/county_farm";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "subCountyDeskOfficerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddLoan");
                            urlPaths.add("/updateStaticInputs");
                            urlPaths.add("/updateInputVarieties");
                            urlPaths.add("/doAddFarmActivity");
                            urlPaths.add("/doAddInputsCollection");
                            switch (path) {
                                case "/farm":
                                    path = "/sub_county_farm";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "waoSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/doAddLoan");
                            urlPaths.add("/updateStaticInputs");
                            urlPaths.add("/updateInputVarieties");
                            urlPaths.add("/doAddFarmActivity");
                            urlPaths.add("/doAddInputsCollection");
                            switch (path) {
                                case "/farm":
                                    path = "/ward_farm";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case "agroDealerSession":
                        urlPaths.add("/doAddLoan");
                        urlPaths.add("/updateStaticInputs");
                        urlPaths.add("/updateInputVarieties");
                        urlPaths.add("/doAddFarmActivity");
                        urlPaths.add("/doAddInputsCollection");
                        if (rightsMaps.get(rightsMap)) {
                            switch (path) {
                                case "/farm":
                                    path = "/agro_dealer_farm";
                                    urlPaths.add(path);
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        if (urlPaths.contains(path)) {

            availApplicationAttributes();

            switch (path) {

                case "/updateStaticInputs":

                    List<StaticInputDetails> staticInputs;
                    try {
                        staticInputs = staticInputService
                                .retrieveStaticInputs(Short.valueOf(request.getParameter("inputTypeId")));
                        out.write("<option disabled selected>Select input name</option>");
                        for (StaticInputDetails staticInput : staticInputs) {
                            out.write("<option value=\"" + staticInput.getId() + "\">" + staticInput.getName() + "</option>");
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.WARNING, "An error occurred: {0}", getBundle().getString(ex.getCode()));
                    }

                    return;

                case "/updateInputVarieties":

                    List<InputVarietyDetails> inputVarieties;
                    try {
                        inputVarieties = inputVarietyService
                                .retrieveInputVarieties(Integer.valueOf(request.getParameter("staticInputId")));
                        out.write("<option disabled selected>Select input variety</option>");
                        for (InputVarietyDetails inputVariety : inputVarieties) {
                            out.write("<option value=\"" + inputVariety.getId() + "\">" + inputVariety.getVariety() + "</option>");
                        }
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()));
                        LOGGER.log(Level.WARNING, "An error occurred: {0}", getBundle().getString(ex.getCode()));
                    }

                    return;

                case "/doAddLoan":
                    LoanDetails loan = new LoanDetails();
                    try {
                        loan.setAmount(new BigDecimal(request.getParameter("amount")));
                    } catch (Exception e) {
                        loan.setAmount(null);
                    }
                    loan.setType(request.getParameter("type"));
                    if (loan.getType().equals("null")) {
                        loan.setType(null);
                    }
                    AccountDetails account = (AccountDetails) session.getAttribute("account");
                    try {
                        loan.setAccount(new AccountDetails(account.getId()));
                    } catch (Exception e) {
                        loan.setAccount(null);
                    }

                    List<LoanDetails> loans;
                    try {
                        loanService.addLoan(loan);
                        loans = loanService.retrieveLoans(account.getId());
                        session.setAttribute("loans", loans);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        return;
                    }

                    updateloansTable(request, response, loans);
                    return;

                case "/doAddInputsCollection":
                    InputsCollectionDetails inputsCollection = new InputsCollectionDetails();
                    try {
                        inputsCollection.setQuantity(request.getParameter("quantity"));
                        if (inputsCollection.getQuantity().equals("null")) {
                            inputsCollection.setQuantity(null);
                        }
                    } catch (Exception e) {
                        inputsCollection.setQuantity(null);
                    }
                    try {
                        inputsCollection.setAgroDealer(new PersonDetails(Integer.valueOf(request.getParameter("agroDealerId"))));
                    } catch (Exception e) {
                        inputsCollection.setAgroDealer(null);
                    }
                    PersonDetails farmer = new PersonDetails();
                    try {
                        farmer = (PersonDetails) session.getAttribute("farmer");
                        inputsCollection.setFarmer(farmer);
                    } catch (Exception e) {
                        inputsCollection.setFarmer(null);
                    }
                    try {
                        inputsCollection.setInputType(new InputTypeDetails(Short.valueOf(request.getParameter("inputTypeId"))));
                    } catch (Exception e) {
                        inputsCollection.setInputType(null);
                    }
                    try {
                        inputsCollection.setStaticInput(new StaticInputDetails(Integer.valueOf(request.getParameter("staticInputId"))));
                    } catch (Exception e) {
                        inputsCollection.setInputType(null);
                    }
                    try {
                        inputsCollection.setInputVariety(new InputVarietyDetails(Integer.valueOf(request.getParameter("inputVarietyId"))));
                    } catch (Exception e) {
                        inputsCollection.setInputVariety(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("dateCollected"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        inputsCollection.setDateCollected(date);
                    } catch (Exception e) {
                        inputsCollection.setDateCollected(null);
                    }

                    List<InputsCollectionDetails> inputsCollections;
                    try {
                        inputsCollectionService.addInputsCollection(inputsCollection);
                        inputsCollections = inputsCollectionService.retrieveInputsCollections(farmer.getId());
                        session.setAttribute("inputsCollections", inputsCollections);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        return;
                    }

                    updateInputsCollectionsTable(request, response, inputsCollections);
                    return;

                case "/doAddFarmActivity":
                    FarmActivityDetails farmActivity = new FarmActivityDetails();
                    farmActivity.setName(request.getParameter("farmActivityName"));
                    if (farmActivity.getName().equals("null")) {
                        farmActivity.setName(null);
                    }
                    farmer = new PersonDetails();
                    try {
                        farmer = (PersonDetails) session.getAttribute("farmer");
                        farmActivity.setFarmer(farmer);
                    } catch (Exception e) {
                        farmActivity.setFarmer(null);
                    }
                    try {
                        farmActivity.setYield(new Double(request.getParameter("yield")));
                    } catch (Exception e) {
                        farmActivity.setYield(null);
                    }
                    try {
                        date = userDateFormat.parse(request.getParameter("farmActivityDate"));
                        date = databaseDateFormat.parse(databaseDateFormat.format(date));
                        farmActivity.setDateDone(date);
                    } catch (Exception e) {
                        farmActivity.setDateDone(null);
                    }
                    try {
                        farmActivity.setQuantitySold(new Double(request.getParameter("quantitySold")));
                    } catch (Exception e) {
                        farmActivity.setQuantitySold(null);
                    }
                    try {
                        farmActivity.setQuantityHarvested(new Double(request.getParameter("quantityHarvested")));
                    } catch (Exception e) {
                        farmActivity.setQuantityHarvested(null);
                    }
                    try {
                        farmActivity.setAverageSellingPrice(new BigDecimal(request.getParameter("averageSellingPrice")));
                    } catch (Exception e) {
                        farmActivity.setAverageSellingPrice(null);
                    }

                    List<FarmActivityDetails> farmActivities;
                    try {
                        farmActivityService.addFarmActivity(farmActivity);
                        farmActivities = farmActivityService.retrieveFarmActivities(farmer.getId());
                        session.setAttribute("farmActivities", farmActivities);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        return;
                    }

                    updateFarmActivitiesTable(request, response, farmActivities);
                    return;

                case "/head_farm":
                case "/ward_farm":
                case "/kalro_farm":
                case "/region_farm":
                case "/county_farm":
                case "/sub_county_farm":
                case "/agro_dealer_farm":

                    Integer farmerId;
                    try {
                        farmerId = Integer.valueOf(request.getParameter("farmerId"));
                        farmer = personService.retrievePerson(farmerId);
                        session.setAttribute("farmer", farmer);
                        session.setAttribute("inputsCollections", inputsCollectionService.retrieveInputsCollections(farmer.getId()));
                        session.setAttribute("farmActivities", farmActivityService.retrieveFarmActivities(farmer.getId()));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        return;
                    } catch (NumberFormatException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        LOGGER.log(Level.INFO, ("An error occurred"), ex.getMessage());
                        break;
                    }
                    try {
                        account = accountService.retrieveAccount(farmerId);
                        session.setAttribute("account", account);
                        session.setAttribute("loans", loanService.retrieveLoans(account.getId()));
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                        return;
                    }

                    List<PersonDetails> agroDealers;
                    try {
                        agroDealers = personService.retrievePeople(PersonRoleDetail.AGRO_DEALER);
                        session.setAttribute("agroDealers", agroDealers);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    List<InputTypeDetails> inputTypes;
                    try {
                        inputTypes = inputTypeService.retrieveInputTypes();
                        session.setAttribute("inputTypes", inputTypes);
                    } catch (MilesException ex) {
                        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                        response.getWriter().write(getBundle().getString(ex.getCode()) + "<br>");
                        LOGGER.log(Level.INFO, getBundle().getString(ex.getCode()), ex);
                    }

                    break;

                default:
                    break;
            }
            //Use request dispatcher to foward request internally
            destination = "/WEB-INF/views" + path + ".jsp";

            LOGGER.log(Level.INFO, "Request dispatch to forward to: {0}", destination);
            try {
                request.getRequestDispatcher(destination).forward(request, response);
            } catch (ServletException | IOException e) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write(getBundle().getString("redirection_failed") + "<br>");
                LOGGER.log(Level.INFO, getBundle().getString("redirection_failed"), e);
            }
        } else {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write(getBundle().getString("error_016_02") + "<br>");
            LOGGER.log(Level.INFO, getBundle().getString("error_016_02"));
        }
    }

    //<editor-fold defaultstate="collapsed" desc="Update tables">
    private void updateloansTable(HttpServletRequest request, HttpServletResponse response,
            List<LoanDetails> loans) throws IOException {
        PrintWriter out = response.getWriter();
        int index = 0;
        for (LoanDetails loan : loans) {
            if (index % 2 == 0) {
                out.write("<tr class=\"odd\">");
            } else {
                out.write("<tr>");
            }
            out.write("<td>" + ++index + "</td>");
            out.write("<td>" + loan.getAmount() + "</td>");
            out.write("<td>" + loan.getType() + "</td>");
            out.write("<td>" + loan.getAccount().getAccountNumber() + "</td>");
            out.write("</tr>");
        }
    }

    private void updateInputsCollectionsTable(HttpServletRequest request, HttpServletResponse response,
            List<InputsCollectionDetails> inputsCollections) throws IOException {
        PrintWriter out = response.getWriter();
        int index = 0;
        for (InputsCollectionDetails inputsCollection : inputsCollections) {
            if (index % 2 == 0) {
                out.write("<tr class=\"odd\">");
            } else {
                out.write("<tr>");
            }
            out.write("<td>" + ++index + "</td>");
            out.write("<td>" + inputsCollection.getDateCollected() + "</td>");
            out.write("<td>" + inputsCollection.getAgroDealer().getName() + "</td>");
            out.write("<td>" + inputsCollection.getAgroDealer().getBusinessName() + "</td>");
            try {
                out.write("<td>" + inputsCollection.getInputType().getType() + "</td>");
            } catch (Exception e) {
                out.write("<td></td>");
            }
            try {
                out.write("<td>" + inputsCollection.getStaticInput().getName() + "</td>");
            } catch (Exception e) {
                out.write("<td></td>");
            }
            try {
                out.write("<td>" + inputsCollection.getInputVariety().getVariety() + "</td>");
            } catch (Exception e) {
                out.write("<td></td>");
            }
            out.write("<td>" + inputsCollection.getQuantity() + "</td>");
            out.write("</tr>");
        }
    }

    private void updateFarmActivitiesTable(HttpServletRequest request, HttpServletResponse response,
            List<FarmActivityDetails> farmActivities) throws IOException {
        PrintWriter out = response.getWriter();
        int index = 0;
        for (FarmActivityDetails farmActivity : farmActivities) {
            if (index % 2 == 0) {
                out.write("<tr class=\"odd\">");
            } else {
                out.write("<tr>");
            }
            out.write("<td>" + ++index + "</td>");
            out.write("<td>" + farmActivity.getName() + "</td>");
            out.write("<td>" + farmActivity.getYield() + "</td>");
            out.write("<td>" + farmActivity.getDateDone() + "</td>");
            out.write("<td>" + farmActivity.getQuantitySold() + "</td>");
            out.write("<td>" + farmActivity.getQuantityHarvested() + "</td>");
            out.write("<td>" + farmActivity.getAverageSellingPrice() + "</td>");
            out.write("</tr>");
        }
        //</editor-fold>
    }
    private static final Logger LOGGER = Logger.getLogger(FarmerController.class.getSimpleName());
    @EJB
    private LoanRequestsLocal loanService;
    @EJB
    private PersonRequestsLocal personService;
    @EJB
    private AccountRequestsLocal accountService;
    @EJB
    private InputTypeRequestsLocal inputTypeService;
    @EJB
    private StaticInputRequestsLocal staticInputService;
    @EJB
    private InputVarietyRequestsLocal inputVarietyService;
    @EJB
    private FarmActivityRequestsLocal farmActivityService;
    @EJB
    private InputsCollectionRequestsLocal inputsCollectionService;

}
