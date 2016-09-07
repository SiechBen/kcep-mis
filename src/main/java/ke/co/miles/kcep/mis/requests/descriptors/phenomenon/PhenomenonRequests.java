/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.descriptors.phenomenon;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Category;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.PhenomenonType;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.descriptors.category.CategoryRequestsLocal;
import ke.co.miles.kcep.mis.requests.descriptors.phenomenon.type.PhenomenonTypeRequestsLocal;
import ke.co.miles.kcep.mis.utilities.PhenomenonDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PhenomenonRequests extends EntityRequests implements PhenomenonRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public void addPhenomenon(PhenomenonDetails phenomenonDetails) throws MilesException {

        if (phenomenonDetails == null) {
            throw new InvalidArgumentException("error_028_01");
        } else if (phenomenonDetails.getCategory() == null) {
            throw new InvalidArgumentException("error_028_02");
        } else if (phenomenonDetails.getPhenomenonType() == null) {
            throw new InvalidArgumentException("error_028_03");
        }

        Phenomenon phenomenon = new Phenomenon();
        phenomenon.setCategory(em.getReference(Category.class, phenomenonDetails.getCategory().getId()));
        phenomenon.setPhenomenonType(em.getReference(PhenomenonType.class, phenomenonDetails.getPhenomenonType().getId()));

        try {
            em.persist(phenomenon);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

    @Override
    public void addPhenomena(List<PhenomenonDetails> phenomenonDetailsList) throws MilesException {
        for (PhenomenonDetails phenomenonDetails : phenomenonDetailsList) {
            addPhenomenon(phenomenonDetails);
        }
        em.flush();
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @SuppressWarnings("unchecked")
    private List<PhenomenonDetails> retrievePhenomena(int phenomenonTypeId) throws MilesException {
        List<Phenomenon> phenomena = new ArrayList<>();
        q = em.createNamedQuery("Phenomenon.findByPhenomenonTypeId");
        q.setParameter("phenomenonTypeId", phenomenonTypeId);
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
        }

        return convertPhenomenaToPhenomenonDetailsList(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveWarehouseOperators() throws MilesException {

        PhenomenonType phenomenonType;
        q = em.createNamedQuery("PhenomenonType.findByName");
        q.setParameter("name", "Warehouse operator");
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return retrievePhenomena(phenomenonType.getId());
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveTraineeCategories() throws MilesException {

        PhenomenonType phenomenonType;
        q = em.createNamedQuery("PhenomenonType.findByName");
        q.setParameter("name", "Category of trainees");
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return retrievePhenomena(phenomenonType.getId());
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveTrainerCategories() throws MilesException {

        PhenomenonType phenomenonType;
        q = em.createNamedQuery("PhenomenonType.findByName");
        q.setParameter("name", "Category of trainers");
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return retrievePhenomena(phenomenonType.getId());
    }

    @Override
    public PhenomenonDetails retrievePhenomenon(int id) throws MilesException {
        Phenomenon phenomenon;
        q = em.createNamedQuery("Phenomenon.findById");
        q.setParameter("id", id);
        try {
            phenomenon = (Phenomenon) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPhenomenonToPhenomenonDetails(phenomenon);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPhenomenon(PhenomenonDetails phenomenonDetails) throws MilesException {

        if (phenomenonDetails == null) {
            throw new InvalidArgumentException("error_028_01");
        } else if (phenomenonDetails.getId() == null) {
            throw new InvalidArgumentException("error_028_04");
        } else if (phenomenonDetails.getCategory() == null) {
            throw new InvalidArgumentException("error_028_02");
        } else if (phenomenonDetails.getPhenomenonType() == null) {
            throw new InvalidArgumentException("error_028_03");
        }

        Phenomenon phenomenon = new Phenomenon();
        phenomenon.setId(phenomenonDetails.getId());
        phenomenon.setCategory(em.getReference(Category.class, phenomenonDetails.getCategory().getId()));
        phenomenon.setPhenomenonType(em.getReference(PhenomenonType.class, phenomenonDetails.getPhenomenonType().getId()));

        try {
            em.merge(phenomenon);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removePhenomenon(int id) throws MilesException {
        Phenomenon phenomenon = em.find(Phenomenon.class, id);
        try {
            em.remove(phenomenon);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public PhenomenonDetails convertPhenomenonToPhenomenonDetails(Phenomenon phenomenon) {

        PhenomenonDetails phenomenonDetails = new PhenomenonDetails(phenomenon.getId());
        phenomenonDetails.setCategory(categoryService.convertCategoryToCategoryDetails(phenomenon.getCategory()));
        phenomenonDetails.setPhenomenonType(phenomenonTypeService.convertPhenomenonTypeToPhenomenonTypeDetails(phenomenon.getPhenomenonType()));

        return phenomenonDetails;

    }

    private List<PhenomenonDetails> convertPhenomenaToPhenomenonDetailsList(List<Phenomenon> phenomena) {

        List<PhenomenonDetails> phenomenonDetailsList = new ArrayList<>();
        for (Phenomenon phenomenon : phenomena) {
            phenomenonDetailsList.add(convertPhenomenonToPhenomenonDetails(phenomenon));
        }

        return phenomenonDetailsList;

    }

//</editor-fold>
    @EJB
    private CategoryRequestsLocal categoryService;
    @EJB
    private PhenomenonTypeRequestsLocal phenomenonTypeService;

}