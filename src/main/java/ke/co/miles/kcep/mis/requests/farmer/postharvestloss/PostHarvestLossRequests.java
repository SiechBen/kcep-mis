/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.farmer.postharvestloss;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.PostHarvestLoss;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PostHarvestLossDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PostHarvestLossRequests extends EntityRequests implements PostHarvestLossRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">  
    @Override
    public int addPostHarvestLoss(PostHarvestLossDetails postHarvestLossDetails) throws MilesException {

        if (postHarvestLossDetails == null) {
            throw new InvalidArgumentException("error_020_01");
        }

        PostHarvestLoss postHarvestLoss = new PostHarvestLoss();
        postHarvestLoss.setFarmer(em.getReference(Person.class, postHarvestLossDetails.getFarmer().getId()));
        postHarvestLoss.setQuantitySold(postHarvestLossDetails.getQuantitySold());
        postHarvestLoss.setFamilyConsumption(postHarvestLossDetails.getFamilyConsumption());
        postHarvestLoss.setPostHarvestLosses(postHarvestLossDetails.getPostHarvestLosses());
        postHarvestLoss.setQuantityHarvested(postHarvestLossDetails.getQuantityHarvested());

        try {
            em.persist(postHarvestLoss);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return postHarvestLoss.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<PostHarvestLossDetails> retrievePostHarvestLosses(int farmerId) throws MilesException {
        q = em.createNamedQuery("PostHarvestLoss.findByFarmerId");
        q.setParameter("farmerId", farmerId);
        List<PostHarvestLoss> postHarvestLosses;
        try {
            postHarvestLosses = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPostHarvestLossesToPostHarvestLossDetailsList(postHarvestLosses);
    }

    @Override
    public PostHarvestLossDetails retrievePostHarvestLoss(int id) throws MilesException {

        q = em.createNamedQuery("PostHarvestLoss.findById");
        q.setParameter("id", id);
        PostHarvestLoss postHarvestLoss;
        try {
            postHarvestLoss = (PostHarvestLoss) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPostHarvestLossToPostHarvestLossDetails(postHarvestLoss);

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PostHarvestLossDetails> retrievePostHarvestLosses() throws MilesException {

        q = em.createNamedQuery("PostHarvestLoss.findAll");
        List<PostHarvestLoss> postHarvestLosses;
        try {
            postHarvestLosses = q.getResultList();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPostHarvestLossesToPostHarvestLossDetailsList(postHarvestLosses);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editPostHarvestLoss(PostHarvestLossDetails postHarvestLossDetails) throws MilesException {

        if (postHarvestLossDetails == null) {
            throw new InvalidArgumentException("error_020_01");
        } else if (postHarvestLossDetails.getId() == null) {
            throw new InvalidArgumentException("error_020_02");
        }

        PostHarvestLoss postHarvestLoss = em.find(PostHarvestLoss.class, postHarvestLossDetails.getId());
        postHarvestLoss.setFarmer(em.getReference(Person.class, postHarvestLossDetails.getFarmer().getId()));
        postHarvestLoss.setId(postHarvestLossDetails.getId());
        postHarvestLoss.setQuantitySold(postHarvestLossDetails.getQuantitySold());
        postHarvestLoss.setFamilyConsumption(postHarvestLossDetails.getFamilyConsumption());
        postHarvestLoss.setPostHarvestLosses(postHarvestLossDetails.getPostHarvestLosses());
        postHarvestLoss.setQuantityHarvested(postHarvestLossDetails.getQuantityHarvested());

        try {
            em.merge(postHarvestLoss);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removePostHarvestLoss(int id) throws MilesException {

        PostHarvestLoss postHarvestLoss = em.find(PostHarvestLoss.class, id);
        try {
            em.remove(postHarvestLoss);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert"> 
    @Override
    public PostHarvestLossDetails convertPostHarvestLossToPostHarvestLossDetails(PostHarvestLoss postHarvestLoss) {

        PostHarvestLossDetails postHarvestLossDetails = new PostHarvestLossDetails(postHarvestLoss.getId());
        postHarvestLossDetails.setFamilyConsumption(postHarvestLoss.getFamilyConsumption());
        postHarvestLossDetails.setPostHarvestLosses(postHarvestLoss.getPostHarvestLosses());
        postHarvestLossDetails.setQuantityHarvested(postHarvestLoss.getQuantityHarvested());
        postHarvestLossDetails.setQuantitySold(postHarvestLoss.getQuantitySold());

        return postHarvestLossDetails;

    }

    private List<PostHarvestLossDetails> convertPostHarvestLossesToPostHarvestLossDetailsList(List<PostHarvestLoss> postHarvestLosses) {

        List<PostHarvestLossDetails> postHarvestLossDetailsList = new ArrayList<>();
        for (PostHarvestLoss postHarvestLoss : postHarvestLosses) {
            postHarvestLossDetailsList.add(convertPostHarvestLossToPostHarvestLossDetails(postHarvestLoss));
        }

        return postHarvestLossDetailsList;

    }

//</editor-fold>
}
