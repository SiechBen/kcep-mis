/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmergroup;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.FarmerGroup;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.FarmerGroupDetails;

/**
 *
 * @author siech
 */
@Stateless
public class FarmerGroupRequests extends EntityRequests implements FarmerGroupRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addFarmerGroup(FarmerGroupDetails farmerGroupDetails) throws MilesException {

        if (farmerGroupDetails == null) {
            throw new InvalidArgumentException("error_004_01");
        } else if (farmerGroupDetails.getName() == null) {
            throw new InvalidArgumentException("error_004_02");
        } else if (farmerGroupDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_004_03");
        }

        FarmerGroup farmerGroup = new FarmerGroup();
        farmerGroup.setName(farmerGroupDetails.getName());

        try {
            em.persist(farmerGroup);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return farmerGroup.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<FarmerGroupDetails> retrieveFarmerGroups() throws MilesException {
        List<FarmerGroup> farmerGroups = new ArrayList<>();
        q = em.createNamedQuery("FarmerGroup.findAll");
        try {
            farmerGroups = q.getResultList();
        } catch (Exception e) {
        }

        return convertFarmerGroupsToFarmerGroupDetailsList(farmerGroups);
    }

    @Override
    public FarmerGroupDetails retrieveFarmerGroup(int id) throws MilesException {
        FarmerGroup farmerGroup;
        q = em.createNamedQuery("FarmerGroup.findById");
        q.setParameter("id", id);
        try {
            farmerGroup = (FarmerGroup) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertFarmerGroupToFarmerGroupDetails(farmerGroup);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editFarmerGroup(FarmerGroupDetails farmerGroupDetails) throws MilesException {

        if (farmerGroupDetails == null) {
            throw new InvalidArgumentException("error_004_01");
        } else if (farmerGroupDetails.getId() == null) {
            throw new InvalidArgumentException("error_004_04");
        } else if (farmerGroupDetails.getName() == null) {
            throw new InvalidArgumentException("error_004_02");
        } else if (farmerGroupDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_004_03");
        }

        FarmerGroup farmerGroup = em.find(FarmerGroup.class, farmerGroupDetails.getId());
        farmerGroup.setName(farmerGroupDetails.getName());

        try {
            em.merge(farmerGroup);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeFarmerGroup(int id) throws MilesException {
        FarmerGroup farmerGroup = em.find(FarmerGroup.class, id);
        try {
            em.remove(farmerGroup);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public FarmerGroupDetails convertFarmerGroupToFarmerGroupDetails(FarmerGroup farmerGroup) {

        FarmerGroupDetails farmerGroupDetails = new FarmerGroupDetails(farmerGroup.getId());
        farmerGroupDetails.setName(farmerGroup.getName());
        return farmerGroupDetails;

    }

    private List<FarmerGroupDetails> convertFarmerGroupsToFarmerGroupDetailsList(List<FarmerGroup> farmerGroups) {

        List<FarmerGroupDetails> farmerGroupDetailsList = new ArrayList<>();
        farmerGroups.stream().forEach((farmerGroup) -> {
            farmerGroupDetailsList.add(convertFarmerGroupToFarmerGroupDetails(farmerGroup));
        });
        return farmerGroupDetailsList;

    }

//</editor-fold>
}
