/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.farmactivity;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.FarmActivity;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.FarmActivityDetails;

/**
 *
 * @author siech
 */
@Stateless
public class FarmActivityRequests extends EntityRequests implements FarmActivityRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addFarmActivity(FarmActivityDetails farmActivityDetails) throws MilesException {

        if (farmActivityDetails == null) {
            throw new InvalidArgumentException("error_040_01");
        } else if (farmActivityDetails.getFarmer() == null) {
            throw new InvalidArgumentException("error_040_02");
        } else if (farmActivityDetails.getName() != null && farmActivityDetails.getName().trim().length() > 45) {
            throw new InvalidArgumentException("error_040_03");
        }

        FarmActivity farmActivity = new FarmActivity();
        farmActivity.setDateDone(farmActivityDetails.getDateDone());
        farmActivity.setYield(farmActivityDetails.getYield());
        farmActivity.setName(farmActivityDetails.getName());
        farmActivity.setQuantitySold(farmActivityDetails.getQuantitySold());
        farmActivity.setQuantityHarvested(farmActivityDetails.getQuantityHarvested());
        farmActivity.setAverageSellingPrice(farmActivityDetails.getAverageSellingPrice());
        farmActivity.setFarmer(em.getReference(Person.class, farmActivityDetails.getFarmer().getId()));

        try {
            em.persist(farmActivity);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return farmActivity.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<FarmActivityDetails> retrieveFarmActivities(int farmerId) throws MilesException {
        List<FarmActivity> farmActivities = new ArrayList<>();
        q = em.createNamedQuery("FarmActivity.findByFarmerId");
        q.setParameter("farmerId", farmerId);
        try {
            farmActivities = q.getResultList();
        } catch (Exception e) {
        }

        return convertFarmActivitiesToFarmActivityDetailsList(farmActivities);
    }

    @Override
    public FarmActivityDetails retrieveFarmActivity(int id) throws MilesException {
        FarmActivity farmActivity;
        q = em.createNamedQuery("FarmActivity.findById");
        q.setParameter("id", id);
        try {
            farmActivity = (FarmActivity) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertFarmActivityToFarmActivityDetails(farmActivity);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editFarmActivity(FarmActivityDetails farmActivityDetails) throws MilesException {

        if (farmActivityDetails == null) {
            throw new InvalidArgumentException("error_040_01");
        } else if (farmActivityDetails.getId() == null) {
            throw new InvalidArgumentException("error_040_05");
        } else if (farmActivityDetails.getFarmer() == null) {
            throw new InvalidArgumentException("error_040_02");
        } else if (farmActivityDetails.getName() != null && farmActivityDetails.getName().trim().length() > 45) {
            throw new InvalidArgumentException("error_040_03");
        }

        FarmActivity farmActivity = em.find(FarmActivity.class, farmActivityDetails.getId());
        farmActivity.setId(farmActivityDetails.getId());
        farmActivity.setDateDone(farmActivityDetails.getDateDone());
        farmActivity.setYield(farmActivityDetails.getYield());
        farmActivity.setName(farmActivityDetails.getName());
        farmActivity.setQuantitySold(farmActivityDetails.getQuantitySold());
        farmActivity.setQuantityHarvested(farmActivityDetails.getQuantityHarvested());
        farmActivity.setAverageSellingPrice(farmActivityDetails.getAverageSellingPrice());
        farmActivity.setFarmer(em.getReference(Person.class, farmActivityDetails.getFarmer().getId()));

        try {
            em.merge(farmActivity);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeFarmActivity(int id) throws MilesException {
        FarmActivity farmActivity = em.find(FarmActivity.class, id);
        try {
            em.remove(farmActivity);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public FarmActivityDetails convertFarmActivityToFarmActivityDetails(FarmActivity farmActivity) {

        FarmActivityDetails farmActivityDetails = new FarmActivityDetails(farmActivity.getId());
        farmActivityDetails.setAverageSellingPrice(farmActivity.getAverageSellingPrice());
        farmActivityDetails.setQuantityHarvested(farmActivity.getQuantityHarvested());
        farmActivityDetails.setQuantitySold(farmActivity.getQuantitySold());
        farmActivityDetails.setName(farmActivity.getName());
        farmActivityDetails.setYield(farmActivity.getYield());
        farmActivityDetails.setDateDone(farmActivity.getDateDone());
        farmActivityDetails.setFarmer(personService.convertPersonToPersonDetails(farmActivity.getFarmer()));

        return farmActivityDetails;

    }

    private List<FarmActivityDetails> convertFarmActivitiesToFarmActivityDetailsList(List<FarmActivity> farmActivities) {

        List<FarmActivityDetails> farmActivityDetailsList = new ArrayList<>();
        for (FarmActivity farmActivity : farmActivities) {
            farmActivityDetailsList.add(convertFarmActivityToFarmActivityDetails(farmActivity));

        }

        return farmActivityDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
}
