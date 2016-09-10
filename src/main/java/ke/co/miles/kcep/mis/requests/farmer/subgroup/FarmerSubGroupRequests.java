/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.subgroup;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.FarmerGroup;
import ke.co.miles.kcep.mis.entities.FarmerSubGroup;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.farmer.group.FarmerGroupRequestsLocal;
import ke.co.miles.kcep.mis.utilities.FarmerSubGroupDetails;

/**
 *
 * @author siech
 */
@Stateless
public class FarmerSubGroupRequests extends EntityRequests implements FarmerSubGroupRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addFarmerSubGroup(FarmerSubGroupDetails farmerSubGroupDetails) throws MilesException {

        if (farmerSubGroupDetails == null) {
            throw new InvalidArgumentException("error_035_01");
        } else if (farmerSubGroupDetails.getName() == null) {
            throw new InvalidArgumentException("error_035_02");
        } else if (farmerSubGroupDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_035_03");
        }

        FarmerSubGroup farmerSubGroup;
        setQ(getEm().createNamedQuery("FarmerSubGroup.findByNameAndFarmerGroupId"));
        getQ().setParameter("name", farmerSubGroupDetails.getName());
        getQ().setParameter("farmerGroupId", farmerSubGroupDetails.getFarmerGroup().getId());
        try {
            farmerSubGroup = (FarmerSubGroup) getQ().getSingleResult();
        } catch (Exception e) {
            farmerSubGroup = null;
        }
        if (farmerSubGroup != null) {
            throw new InvalidArgumentException("error_035_04");
        }

        farmerSubGroup = new FarmerSubGroup();
        farmerSubGroup.setName(farmerSubGroupDetails.getName());
        if (farmerSubGroupDetails.getFarmerGroup() != null) {
            farmerSubGroup.setFarmerGroup(getEm().getReference(FarmerGroup.class, farmerSubGroupDetails.getFarmerGroup().getId()));
        }

        try {
            getEm().persist(farmerSubGroup);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return farmerSubGroup.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<FarmerSubGroupDetails> retrieveCounties() throws MilesException {
        List<FarmerSubGroup> farmerSubGroups = new ArrayList<>();
        setQ(getEm().createNamedQuery("FarmerSubGroup.findAll"));
        try {
            farmerSubGroups = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertCountiesToFarmerSubGroupDetailsList(farmerSubGroups);
    }

    @Override
    public FarmerSubGroupDetails retrieveFarmerSubGroup(int id) throws MilesException {
        FarmerSubGroup farmerSubGroup;
        setQ(getEm().createNamedQuery("FarmerSubGroup.findById"));
        getQ().setParameter("id", id);
        try {
            farmerSubGroup = (FarmerSubGroup) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertFarmerSubGroupToFarmerSubGroupDetails(farmerSubGroup);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editFarmerSubGroup(FarmerSubGroupDetails farmerSubGroupDetails) throws MilesException {

        if (farmerSubGroupDetails == null) {
            throw new InvalidArgumentException("error_035_01");
        } else if (farmerSubGroupDetails.getId() == null) {
            throw new InvalidArgumentException("error_035_05");
        } else if (farmerSubGroupDetails.getName() == null) {
            throw new InvalidArgumentException("error_035_02");
        } else if (farmerSubGroupDetails.getName().length() > 200) {
            throw new InvalidArgumentException("error_035_03");
        }

        FarmerSubGroup farmerSubGroup;
        setQ(getEm().createNamedQuery("FarmerSubGroup.findByNameAndFarmerGroupId"));
        getQ().setParameter("name", farmerSubGroupDetails.getName());
        getQ().setParameter("farmerGroupId", farmerSubGroupDetails.getFarmerGroup().getId());
        try {
            farmerSubGroup = (FarmerSubGroup) getQ().getSingleResult();
        } catch (Exception e) {
            farmerSubGroup = null;
        }
        if (farmerSubGroup != null) {
            if (farmerSubGroup.getId().equals(farmerSubGroupDetails.getId())) {
                throw new InvalidArgumentException("error_035_04");
            }
        }

        farmerSubGroup = getEm().find(FarmerSubGroup.class, farmerSubGroupDetails.getId());
        farmerSubGroup.setId(farmerSubGroupDetails.getId());
        farmerSubGroup.setName(farmerSubGroupDetails.getName());
        if (farmerSubGroupDetails.getFarmerGroup() != null) {
            farmerSubGroup.setFarmerGroup(getEm().getReference(FarmerGroup.class, farmerSubGroupDetails.getFarmerGroup().getId()));
        }

        try {
            getEm().merge(farmerSubGroup);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeFarmerSubGroup(int id) throws MilesException {
        FarmerSubGroup farmerSubGroup = getEm().find(FarmerSubGroup.class, id);
        try {
            getEm().remove(farmerSubGroup);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public FarmerSubGroupDetails convertFarmerSubGroupToFarmerSubGroupDetails(FarmerSubGroup farmerSubGroup) {

        FarmerSubGroupDetails farmerSubGroupDetails = new FarmerSubGroupDetails();
        try {
            farmerSubGroupDetails.setId(farmerSubGroup.getId());
        } catch (Exception e) {
        }
        try {
            farmerSubGroupDetails.setName(farmerSubGroup.getName());
        } catch (Exception e) {
        }
        try {
            farmerSubGroupDetails.setFarmerGroup(farmerGroupService.convertFarmerGroupToFarmerGroupDetails(farmerSubGroup.getFarmerGroup()));
        } catch (Exception e) {
        }
        return farmerSubGroupDetails;

    }

    private List<FarmerSubGroupDetails> convertCountiesToFarmerSubGroupDetailsList(List<FarmerSubGroup> farmerSubGroups) {

        List<FarmerSubGroupDetails> farmerSubGroupDetailsList = new ArrayList<>();
        for (FarmerSubGroup farmerSubGroup : farmerSubGroups) {
            farmerSubGroupDetailsList.add(convertFarmerSubGroupToFarmerSubGroupDetails(farmerSubGroup));
        }

        return farmerSubGroupDetailsList;

    }

//</editor-fold>
   
    @EJB
    private FarmerGroupRequestsLocal farmerGroupService;
}
