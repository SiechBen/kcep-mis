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
@WebServlet(name = "PopulationController", urlPatterns = {"/uploadPeople"})
public class PopulationController extends Controller {

    private static final long serialVersionUID = 1L;

    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Locale locale = request.getLocale();
        setBundle(ResourceBundle.getBundle("text", locale));

        HttpSession session = request.getSession(false);
        String path = request.getServletPath();
        String destination;

        @SuppressWarnings("unchecked")
        HashMap<String, Boolean> rightsMaps = (HashMap<String, Boolean>) session.getAttribute("rightsMaps");
        ArrayList<String> urlPaths = new ArrayList<>();
        if (rightsMaps != null) {
            for (String rightsMap : rightsMaps.keySet()) {
                switch (rightsMap) {
                    case "systemAdminSession":
                    case "nationalPopulationSession":
                    case "equityPersonnelSession":
                    case "kalroSession":
                    case "agmarkSession":
                    case "regionalCoordinatorSession":
                    case "countyDeskOfficerSession":
                    case "subCountyDeskOfficerSession":
                    case "waoSession":
                    case "agroDealerSession":
                        if (rightsMaps.get(rightsMap)) {
                            urlPaths.add("/uploadPeople");
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
                case "/uploadPeople":
                    path = "/ward_officer";
                    break;
            }
            destination = "/WEB-INF/views/pages" + path + ".jsp";

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

    private static final Logger LOGGER = Logger.getLogger(PopulationController.class.getSimpleName());

}
