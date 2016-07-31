/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.financialyear;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.FinancialYear;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.FinancialYearDetails;

/**
 *
 * @author siech
 */
@Stateless
public class FinancialYearRequests extends EntityRequests implements FinancialYearRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addFinancialYear(FinancialYearDetails financialYearDetails) throws MilesException {

        if (financialYearDetails == null) {
            throw new InvalidArgumentException("error_050_01");
        } else if (financialYearDetails.getFinancialYear() == null) {
            throw new InvalidArgumentException("error_050_02");
        } else if (financialYearDetails.getFinancialYear().length() > 45) {
            throw new InvalidArgumentException("error_050_03");
        }

        FinancialYear financialYear;
        q = em.createNamedQuery("FinancialYear.findByFinancialYear");
        q.setParameter("financialYear", financialYearDetails.getFinancialYear());
        try {
            financialYear = (FinancialYear) q.getSingleResult();
        } catch (Exception e) {
            financialYear = null;
        }
        if (financialYear != null) {
            throw new InvalidArgumentException("error_050_04");
        }

        if (financialYear != null && financialYear.getCurrentYear()) {
            q = em.createNamedQuery("FinancialYear.findByCurrentYear");
            q.setParameter("currentYear", financialYearDetails.getCurrentYear());
            try {
                financialYear = (FinancialYear) q.getSingleResult();
            } catch (Exception e) {
                financialYear = null;
            }
            if (financialYear != null) {
                throw new InvalidArgumentException("error_050_05");
            }
        }

        financialYear = new FinancialYear();
        financialYear.setFinancialYear(financialYearDetails.getFinancialYear());
        financialYear.setCurrentYear(financialYearDetails.getCurrentYear());

        try {
            em.persist(financialYear);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return financialYear.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<FinancialYearDetails> retrieveFinancialYears() throws MilesException {
        List<FinancialYear> financialYears = new ArrayList<>();
        q = em.createNamedQuery("FinancialYear.findAll");
        try {
            financialYears = q.getResultList();
        } catch (Exception e) {
        }

        return convertFinancialYearsToFinancialYearDetailsList(financialYears);
    }

    @Override
    @SuppressWarnings("unchecked")
    public FinancialYearDetails retrieveCurrentFinancialYear() throws MilesException {
        FinancialYear financialYear = new FinancialYear();
        q = em.createNamedQuery("FinancialYear.findByCurrentYear");
        q.setParameter("currentYear", Boolean.TRUE);
        try {
            financialYear = (FinancialYear) q.getSingleResult();
        } catch (Exception e) {
        }

        return convertFinancialYearToFinancialYearDetails(financialYear);
    }

    @Override
    public FinancialYearDetails retrieveFinancialYear(int id) throws MilesException {
        FinancialYear financialYear;
        q = em.createNamedQuery("FinancialYear.findById");
        q.setParameter("id", id);
        try {
            financialYear = (FinancialYear) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertFinancialYearToFinancialYearDetails(financialYear);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editFinancialYear(FinancialYearDetails financialYearDetails) throws MilesException {

        if (financialYearDetails == null) {
            throw new InvalidArgumentException("error_050_01");
        } else if (financialYearDetails.getId() == null) {
            throw new InvalidArgumentException("error_050_06");
        } else if (financialYearDetails.getFinancialYear() == null) {
            throw new InvalidArgumentException("error_050_02");
        } else if (financialYearDetails.getFinancialYear().length() > 45) {
            throw new InvalidArgumentException("error_050_03");
        }

        FinancialYear financialYear;
        q = em.createNamedQuery("FinancialYear.findByFinancialYear");
        q.setParameter("financialYear", financialYearDetails.getFinancialYear());
        try {
            financialYear = (FinancialYear) q.getSingleResult();
        } catch (Exception e) {
            financialYear = null;
        }
        if (financialYear != null) {
            if (financialYear.getId().equals(financialYearDetails.getId())) {
                throw new InvalidArgumentException("error_050_04");
            }
        }

        if (financialYear != null && financialYear.getCurrentYear()) {
            q = em.createNamedQuery("FinancialYear.findByCurrentYear");
            q.setParameter("currentYear", financialYearDetails.getCurrentYear());
            try {
                financialYear = (FinancialYear) q.getSingleResult();
            } catch (Exception e) {
                financialYear = null;
            }
            if (financialYear != null) {
                throw new InvalidArgumentException("error_050_05");
            }
        }

        financialYear = em.find(FinancialYear.class, financialYearDetails.getId());
        financialYear.setFinancialYear(financialYearDetails.getFinancialYear());
        financialYear.setCurrentYear(financialYearDetails.getCurrentYear());

        try {
            em.merge(financialYear);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeFinancialYear(int id) throws MilesException {
        FinancialYear financialYear = em.find(FinancialYear.class, id);
        try {
            em.remove(financialYear);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public FinancialYearDetails convertFinancialYearToFinancialYearDetails(FinancialYear financialYear) {

        FinancialYearDetails financialYearDetails = new FinancialYearDetails(financialYear.getId());
        financialYearDetails.setFinancialYear(financialYear.getFinancialYear());
        financialYearDetails.setCurrentYear(financialYear.getCurrentYear());
        return financialYearDetails;

    }

    private List<FinancialYearDetails> convertFinancialYearsToFinancialYearDetailsList(List<FinancialYear> financialYears) {

        List<FinancialYearDetails> financialYearDetailsList = new ArrayList<>();
        for (FinancialYear financialYear : financialYears) {
            financialYearDetailsList.add(convertFinancialYearToFinancialYearDetails(financialYear));
        }

        return financialYearDetailsList;

    }

//</editor-fold>
}
