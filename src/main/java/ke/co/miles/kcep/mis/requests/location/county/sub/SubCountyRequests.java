/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.county.sub;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.County;
import ke.co.miles.kcep.mis.entities.SubCounty;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.location.county.CountyRequestsLocal;
import ke.co.miles.kcep.mis.utilities.SubCountyDetails;

/**
 *
 * @author siech
 */
@Stateless
public class SubCountyRequests extends EntityRequests implements SubCountyRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addSubCounty(SubCountyDetails subCountyDetails) throws MilesException {

        if (subCountyDetails == null) {
            throw new InvalidArgumentException("error_023_01");
        } else if (subCountyDetails.getName() == null) {
            throw new InvalidArgumentException("error_023_02");
        } else if (subCountyDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_023_03");
        } else if (subCountyDetails.getCounty() == null) {
            throw new InvalidArgumentException("error_023_04");
        }

        SubCounty subCounty;
        q = em.createNamedQuery("SubCounty.findByNameAndCountyId");
        q.setParameter("name", subCountyDetails.getName());
        q.setParameter("countyId", subCountyDetails.getCounty().getId());
        try {
            subCounty = (SubCounty) q.getSingleResult();
        } catch (Exception e) {
            subCounty = null;
        }
        if (subCounty != null) {
            throw new InvalidArgumentException("error_023_05");
        }

        subCounty = new SubCounty();
        subCounty.setName(subCountyDetails.getName());
        subCounty.setCounty(em.find(County.class, subCountyDetails.getCounty().getId()));

        try {
            em.persist(subCounty);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return subCounty.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<SubCountyDetails> retrieveSubCounties() throws MilesException {
        List<SubCounty> subCountys = new ArrayList<>();
        q = em.createNamedQuery("SubCounty.findAll");
        try {
            subCountys = q.getResultList();
        } catch (Exception e) {
        }

        return convertSubCountysToSubCountyDetailsList(subCountys);
    }

    @Override
    public SubCountyDetails retrieveSubCounty(int id) throws MilesException {
        SubCounty subCounty;
        q = em.createNamedQuery("SubCounty.findById");
        q.setParameter("id", id);
        try {
            subCounty = (SubCounty) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSubCountyToSubCountyDetails(subCounty);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editSubCounty(SubCountyDetails subCountyDetails) throws MilesException {

        if (subCountyDetails == null) {
            throw new InvalidArgumentException("error_023_01");
        } else if (subCountyDetails.getId() == null) {
            throw new InvalidArgumentException("error_023_06");
        } else if (subCountyDetails.getName() == null) {
            throw new InvalidArgumentException("error_023_02");
        } else if (subCountyDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_023_03");
        } else if (subCountyDetails.getCounty() == null) {
            throw new InvalidArgumentException("error_023_04");
        }

        SubCounty subCounty;
        q = em.createNamedQuery("SubCounty.findByNameAndCountyId");
        q.setParameter("name", subCountyDetails.getName());
        q.setParameter("countyId", subCountyDetails.getCounty().getId());
        try {
            subCounty = (SubCounty) q.getSingleResult();
        } catch (Exception e) {
            subCounty = null;
        }
        if (subCounty != null) {
            if (subCounty.getId().equals(subCountyDetails.getId())) {
                throw new InvalidArgumentException("error_023_05");
            }
        }

        subCounty = em.find(SubCounty.class, subCountyDetails.getId());
        subCounty.setId(subCountyDetails.getId());
        subCounty.setName(subCountyDetails.getName());
        subCounty.setCounty(em.find(County.class, subCountyDetails.getCounty().getId()));

        try {
            em.merge(subCounty);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeSubCounty(int id) throws MilesException {
        SubCounty subCounty = em.find(SubCounty.class, id);
        try {
            em.remove(subCounty);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public SubCountyDetails convertSubCountyToSubCountyDetails(SubCounty subCounty) {

        SubCountyDetails subCountyDetails = new SubCountyDetails();
        try {
            subCountyDetails.setId(subCounty.getId());
        } catch (Exception e) {
        }
        try {
            subCountyDetails.setName(subCounty.getName());
        } catch (Exception e) {
        }
        try {
            subCountyDetails.setCounty(countyService.convertCountyToCountyDetails(subCounty.getCounty()));
        } catch (Exception e) {
        }
        return subCountyDetails;

    }

    private List<SubCountyDetails> convertSubCountysToSubCountyDetailsList(List<SubCounty> subCountys) {

        List<SubCountyDetails> subCountyDetailsList = new ArrayList<>();
        for (SubCounty subCounty : subCountys) {
            subCountyDetailsList.add(convertSubCountyToSubCountyDetails(subCounty));
        }

        return subCountyDetailsList;

    }

//</editor-fold>
    @EJB
    private CountyRequestsLocal countyService;
}
