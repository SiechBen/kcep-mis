/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.expenditurecategory;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ExpenditureCategory;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ExpenditureCategoryDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ExpenditureCategoryRequests extends EntityRequests implements ExpenditureCategoryRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addExpenditureCategory(ExpenditureCategoryDetails expenditureCategoryDetails) throws MilesException {

        if (expenditureCategoryDetails == null) {
            throw new InvalidArgumentException("error_029_01");
        } else if (expenditureCategoryDetails.getName() == null) {
            throw new InvalidArgumentException("error_029_02");
        } else if (expenditureCategoryDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_029_03");
        }

        ExpenditureCategory expenditureCategory;
        setQ(em.createNamedQuery("ExpenditureCategory.findByName"));
        q.setParameter("name", expenditureCategoryDetails.getName());
        try {
            expenditureCategory = (ExpenditureCategory) q.getSingleResult();
        } catch (Exception e) {
            expenditureCategory = null;
        }
        if (expenditureCategory != null) {
            throw new InvalidArgumentException("error_029_04");
        }

        expenditureCategory = new ExpenditureCategory();
        expenditureCategory.setName(expenditureCategoryDetails.getName());

        try {
            em.persist(expenditureCategory);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return expenditureCategory.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ExpenditureCategoryDetails> retrieveExpenditureCategories() throws MilesException {
        List<ExpenditureCategory> expenditureCategories = new ArrayList<>();
        setQ(em.createNamedQuery("ExpenditureCategory.findAll", ExpenditureCategory.class));
        try {
            expenditureCategories = q.getResultList();
        } catch (Exception e) {
        }

        return convertExpenditureCategoriesToExpenditureCategoryDetailsList(expenditureCategories);
    }

    @Override
    public ExpenditureCategoryDetails retrieveExpenditureCategory(int id) throws MilesException {
        ExpenditureCategory expenditureCategory;
        setQ(em.createNamedQuery("ExpenditureCategory.findById"));
        q.setParameter("id", id);
        try {
            expenditureCategory = (ExpenditureCategory) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertExpenditureCategoryToExpenditureCategoryDetails(expenditureCategory);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editExpenditureCategory(ExpenditureCategoryDetails expenditureCategoryDetails) throws MilesException {

        if (expenditureCategoryDetails == null) {
            throw new InvalidArgumentException("error_029_01");
        } else if (expenditureCategoryDetails.getId() == null) {
            throw new InvalidArgumentException("error_029_05");
        } else if (expenditureCategoryDetails.getName() == null) {
            throw new InvalidArgumentException("error_029_02");
        } else if (expenditureCategoryDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_029_03");
        }

        ExpenditureCategory expenditureCategory;
        setQ(em.createNamedQuery("ExpenditureCategory.findByName"));
        q.setParameter("name", expenditureCategoryDetails.getName());
        try {
            expenditureCategory = (ExpenditureCategory) q.getSingleResult();
        } catch (Exception e) {
            expenditureCategory = null;
        }
        if (expenditureCategory != null) {
            if (expenditureCategory.getId().equals(expenditureCategoryDetails.getId())) {
                throw new InvalidArgumentException("error_029_04");
            }
        }

        expenditureCategory = em.find(ExpenditureCategory.class, expenditureCategoryDetails.getId());
        expenditureCategory.setId(expenditureCategoryDetails.getId());
        expenditureCategory.setName(expenditureCategoryDetails.getName());

        try {
            em.merge(expenditureCategory);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeExpenditureCategory(int id) throws MilesException {
        ExpenditureCategory expenditureCategory = em.find(ExpenditureCategory.class, id);
        try {
            em.remove(expenditureCategory);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ExpenditureCategoryDetails convertExpenditureCategoryToExpenditureCategoryDetails(ExpenditureCategory expenditureCategory) {

        ExpenditureCategoryDetails expenditureCategoryDetails = new ExpenditureCategoryDetails();
        try {
            expenditureCategoryDetails.setId(expenditureCategory.getId());
        } catch (Exception e) {
        }
        try {
            expenditureCategoryDetails.setName(expenditureCategory.getName());
        } catch (Exception e) {
        }
        return expenditureCategoryDetails;

    }

    private List<ExpenditureCategoryDetails> convertExpenditureCategoriesToExpenditureCategoryDetailsList(List<ExpenditureCategory> expenditureCategories) {

        List<ExpenditureCategoryDetails> expenditureCategoryDetailsList = new ArrayList<>();
        for (ExpenditureCategory expenditureCategory : expenditureCategories) {
            expenditureCategoryDetailsList.add(convertExpenditureCategoryToExpenditureCategoryDetails(expenditureCategory));
        }

        return expenditureCategoryDetailsList;

    }

//</editor-fold>
}
