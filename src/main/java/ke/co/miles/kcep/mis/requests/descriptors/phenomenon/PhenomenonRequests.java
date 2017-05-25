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
import ke.co.miles.debugger.MilesDebugger;
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
    public int addPhenomenon(PhenomenonDetails phenomenonDetails) throws MilesException {

        if (phenomenonDetails == null) {
            throw new InvalidArgumentException("error_028_01");
        } else if (phenomenonDetails.getCategory() == null) {
            throw new InvalidArgumentException("error_028_02");
        } else if (phenomenonDetails.getPhenomenonType() == null) {
            throw new InvalidArgumentException("error_028_03");
        }

        Phenomenon phenomenon = new Phenomenon();
        try {
            phenomenon.setCategory(em.getReference(Category.class, phenomenonDetails.getCategory().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            /* id was not provided, category is new */
            phenomenon.setCategory(em.getReference(Category.class, categoryService.addCategory(phenomenonDetails.getCategory())));
        }
        try {
            phenomenon.setPhenomenonType(em.getReference(PhenomenonType.class, phenomenonDetails.getPhenomenonType().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            /* id was not provided, use name to identify the phenomenon type */
            phenomenon.setPhenomenonType(em.getReference(PhenomenonType.class, phenomenonTypeService.retrievePhenomenonType(phenomenonDetails.getPhenomenonType().getName()).getId()));
        }

        try {
            em.persist(phenomenon);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return phenomenon.getId();

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

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveExpectedOutcomes() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Expected outcome");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveExpenditureCategories() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Expenditure category");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveResponsePCUList() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Response PCU");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveImplementingPartners() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Implementing partner");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveAnnualIndicators() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Annual indicator");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveRatingValues() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Rating value");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveWarehouseTypes() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Warehouse type");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveComponents() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Component");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveSubComponents(short componentId) throws MilesException {

        setQ(em.createNamedQuery("Phenomenon.findByRelativeId"));
        q.setParameter("relativeId", componentId);
        List<Phenomenon> phenomena = new ArrayList<>();
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrievePerformanceIndicatorTypes() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Performance indicator type");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveBanks() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Bank");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveGFSSCodes() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "GFSS code");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveSubComponents() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Sub-component");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveSubComponents(int componentId) throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Sub-component");
        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        setQ(em.createNamedQuery("Phenomenon.findById"));
        q.setParameter("id", componentId);
        Phenomenon phenomenon;
        try {
            phenomenon = (Phenomenon) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeIdAndRelativeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        q.setParameter("relativeId", phenomenon.getCategory().getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveWarehouseOperators() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Warehouse operator");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveTraineeCategories() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Category of trainees");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PhenomenonDetails> retrieveTrainerCategories() throws MilesException {

        setQ(em.createNamedQuery("PhenomenonType.findByName"));
        q.setParameter("name", "Category of trainers");

        PhenomenonType phenomenonType;
        try {
            phenomenonType = (PhenomenonType) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        List<Phenomenon> phenomena = new ArrayList<>();
        setQ(em.createNamedQuery("Phenomenon.findByPhenomenonTypeId"));
        q.setParameter("phenomenonTypeId", phenomenonType.getId());
        try {
            phenomena = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertPhenomenaToPhenomenonaDetails(phenomena);
    }

    @Override
    public PhenomenonDetails retrievePhenomenon(int id) throws MilesException {
        Phenomenon phenomenon;
        setQ(em.createNamedQuery("Phenomenon.findById"));
        q.setParameter("id", id);
        try {
            phenomenon = (Phenomenon) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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

        Phenomenon phenomenon = em.getReference(Phenomenon.class, phenomenonDetails.getId());
        phenomenon.setId(phenomenonDetails.getId());
        phenomenon.setCategory(em.getReference(Category.class, phenomenonDetails.getCategory().getId()));
        phenomenon.setPhenomenonType(em.getReference(PhenomenonType.class, phenomenonDetails.getPhenomenonType().getId()));

        try {
            em.merge(phenomenon);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
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
        phenomenonDetails.setTiedValue(phenomenon.getTiedValue());

        return phenomenonDetails;
    }

    private List<PhenomenonDetails> convertPhenomenaToPhenomenonaDetails(List<Phenomenon> phenomena) {

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
