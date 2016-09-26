/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.ward;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.SubCounty;
import ke.co.miles.kcep.mis.entities.Ward;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.location.county.sub.SubCountyRequestsLocal;
import ke.co.miles.kcep.mis.utilities.WardDetails;

/**
 *
 * @author siech
 */
@Stateless
public class WardRequests extends EntityRequests implements WardRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addWard(WardDetails wardDetails) throws MilesException {

        if (wardDetails == null) {
            throw new InvalidArgumentException("error_022_01");
        } else if (wardDetails.getName() == null) {
            throw new InvalidArgumentException("error_022_02");
        } else if (wardDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_022_03");
        } else if (wardDetails.getSubCounty() == null) {
            throw new InvalidArgumentException("error_022_04");
        }

        Ward ward;
        setQ(em.createNamedQuery("Ward.findByNameAndSubCountyId"));
        q.setParameter("name", wardDetails.getName());
        q.setParameter("subCountyId", wardDetails.getSubCounty().getId());
        try {
            ward = (Ward) q.getSingleResult();
        } catch (Exception e) {
            ward = null;
        }
        if (ward != null) {
            throw new InvalidArgumentException("error_022_05");
        }

        ward = new Ward();
        ward.setName(wardDetails.getName());
        ward.setSubCounty(em.getReference(SubCounty.class, wardDetails.getSubCounty().getId()));

        try {
            em.persist(ward);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return ward.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<WardDetails> retrieveWards() throws MilesException {
        List<Ward> wards = new ArrayList<>();
        setQ(em.createNamedQuery("Ward.findAll"));
        try {
            wards = q.getResultList();
        } catch (Exception e) {
        }

        return convertWardsToWardDetailsList(wards);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<WardDetails> retrieveWards(short subCountyId) throws MilesException {
        List<Ward> wards = new ArrayList<>();
        setQ(em.createNamedQuery("Ward.findBySubCountyId"));
        q.setParameter("subCountyId", subCountyId);
        try {
            wards = q.getResultList();
        } catch (Exception e) {
        }

        return convertWardsToWardDetailsList(wards);
    }

    @Override
    public WardDetails retrieveWard(int id) throws MilesException {
        Ward ward;
        setQ(em.createNamedQuery("Ward.findById"));
        q.setParameter("id", id);
        try {
            ward = (Ward) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertWardToWardDetails(ward);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editWard(WardDetails wardDetails) throws MilesException {

        if (wardDetails == null) {
            throw new InvalidArgumentException("error_022_01");
        } else if (wardDetails.getId() == null) {
            throw new InvalidArgumentException("error_022_06");
        } else if (wardDetails.getName() == null) {
            throw new InvalidArgumentException("error_022_02");
        } else if (wardDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_022_03");
        } else if (wardDetails.getSubCounty() == null) {
            throw new InvalidArgumentException("error_022_04");
        }

        Ward ward;
        setQ(em.createNamedQuery("Ward.findByNameAndSubCountyId"));
        q.setParameter("name", wardDetails.getName());
        q.setParameter("subCountyId", wardDetails.getSubCounty().getId());
        try {
            ward = (Ward) q.getSingleResult();
        } catch (Exception e) {
            ward = null;
        }
        if (ward != null) {
            if (ward.getId().equals(wardDetails.getId())) {
                throw new InvalidArgumentException("error_022_05");
            }
        }

        ward = em.find(Ward.class, wardDetails.getId());
        ward.setId(wardDetails.getId());
        ward.setName(wardDetails.getName());
        ward.setSubCounty(em.getReference(SubCounty.class, wardDetails.getSubCounty().getId()));

        try {
            em.merge(ward);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeWard(int id) throws MilesException {
        Ward ward = em.find(Ward.class, id);
        try {
            em.remove(ward);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public WardDetails convertWardToWardDetails(Ward ward) {

        WardDetails wardDetails = new WardDetails();
        try {
            wardDetails.setId(ward.getId());
        } catch (Exception e) {
        }
        try {
            wardDetails.setName(ward.getName());
        } catch (Exception e) {
        }
        try {
            wardDetails.setSubCounty(subCountyService.convertSubCountyToSubCountyDetails(ward.getSubCounty()));
        } catch (Exception e) {
        }
        return wardDetails;

    }

    private List<WardDetails> convertWardsToWardDetailsList(List<Ward> wards) {

        List<WardDetails> wardDetailsList = new ArrayList<>();
        for (Ward ward : wards) {
            wardDetailsList.add(convertWardToWardDetails(ward));
        }

        return wardDetailsList;

    }

//</editor-fold>
    @EJB
    private SubCountyRequestsLocal subCountyService;
}
