/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.village;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Village;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.VillageDetails;

/**
 *
 * @author siech
 */
@Stateless
public class VillageRequests extends EntityRequests implements VillageRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addVillage(VillageDetails villageDetails) throws MilesException {

        if (villageDetails == null) {
            throw new InvalidArgumentException("error_042_01");
        } else if (villageDetails.getName() == null) {
            throw new InvalidArgumentException("error_042_02");
        } else if (villageDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_042_03");
        }

        Village village;
        q = em.createNamedQuery("Village.findByName");
        q.setParameter("name", villageDetails.getName());
        try {
            village = (Village) q.getSingleResult();
        } catch (Exception e) {
            village = null;
        }
        if (village != null) {
            throw new InvalidArgumentException("error_042_04");
        }

        village = new Village();
        village.setName(villageDetails.getName());

        try {
            em.persist(village);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return village.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<VillageDetails> retrieveVillages() throws MilesException {
        List<Village> villages = new ArrayList<>();
        q = em.createNamedQuery("Village.findAll");
        try {
            villages = q.getResultList();
        } catch (Exception e) {
        }

        return convertVillagesToVillageDetailsList(villages);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<VillageDetails> retrieveVillages(short countyId) throws MilesException {
        List<Village> villages = new ArrayList<>();
        q = em.createNamedQuery("Village.findByCountyId");
        q.setParameter("countyId", countyId);
        try {
            villages = q.getResultList();
        } catch (Exception e) {
        }

        return convertVillagesToVillageDetailsList(villages);
    }

    @Override
    public VillageDetails retrieveVillage(int id) throws MilesException {
        Village village;
        q = em.createNamedQuery("Village.findById");
        q.setParameter("id", id);
        try {
            village = (Village) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertVillageToVillageDetails(village);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editVillage(VillageDetails villageDetails) throws MilesException {

        if (villageDetails == null) {
            throw new InvalidArgumentException("error_042_01");
        } else if (villageDetails.getId() == null) {
            throw new InvalidArgumentException("error_042_05");
        } else if (villageDetails.getName() == null) {
            throw new InvalidArgumentException("error_042_02");
        } else if (villageDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_042_03");
        }

        Village village;
        q = em.createNamedQuery("Village.findByName");
        q.setParameter("name", villageDetails.getName());
        try {
            village = (Village) q.getSingleResult();
        } catch (Exception e) {
            village = null;
        }
        if (village != null) {
            if (village.getId().equals(villageDetails.getId())) {
                throw new InvalidArgumentException("error_042_04");
            }
        }

        village = em.find(Village.class, villageDetails.getId());
        village.setId(villageDetails.getId());
        village.setName(villageDetails.getName());

        try {
            em.merge(village);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeVillage(int id) throws MilesException {
        Village village = em.find(Village.class, id);
        try {
            em.remove(village);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public VillageDetails convertVillageToVillageDetails(Village village) {

        VillageDetails villageDetails = new VillageDetails();
        try {
            villageDetails.setId(village.getId());
        } catch (Exception e) {
        }
        try {
            villageDetails.setName(village.getName());
        } catch (Exception e) {
        }

        return villageDetails;

    }

    private List<VillageDetails> convertVillagesToVillageDetailsList(List<Village> villages) {

        List<VillageDetails> villageDetailsList = new ArrayList<>();
        for (Village village : villages) {
            villageDetailsList.add(convertVillageToVillageDetails(village));
        }

        return villageDetailsList;

    }

//</editor-fold>
}
