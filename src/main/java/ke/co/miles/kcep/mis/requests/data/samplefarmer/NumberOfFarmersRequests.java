/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.data.samplefarmer;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.AgeBracket;
import ke.co.miles.kcep.mis.entities.NumberDescription;
import ke.co.miles.kcep.mis.entities.NumberOfFarmers;
import ke.co.miles.kcep.mis.entities.SampledFarmerData;
import ke.co.miles.kcep.mis.entities.Sex;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.agebracket.AgeBracketRequestsLocal;
import ke.co.miles.kcep.mis.requests.location.LocationRequestsLocal;
import ke.co.miles.kcep.mis.utilities.AgeBracketDetails;
import ke.co.miles.kcep.mis.utilities.NumberDescriptionDetails;
import ke.co.miles.kcep.mis.utilities.NumberOfFarmersDetails;
import ke.co.miles.kcep.mis.utilities.SampledFarmerDataDetails;
import ke.co.miles.kcep.mis.utilities.SexDetail;

/**
 *
 * @author siech
 */
@Stateless
public class NumberOfFarmersRequests extends EntityRequests implements NumberOfFarmersRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addNumberOfFarmers(NumberOfFarmersDetails numberOfFarmersDetails) throws MilesException {

        if (numberOfFarmersDetails == null) {
            throw new InvalidArgumentException("error_001_01");
        } else if (numberOfFarmersDetails.getNumberDescription() == null) {
            throw new InvalidArgumentException("error_001_02");
        } else if (numberOfFarmersDetails.getSampledFarmerData() == null) {
            throw new InvalidArgumentException("error_001_03");
        }

        NumberOfFarmers numberOfFarmers = new NumberOfFarmers();
        numberOfFarmers.setNumber(numberOfFarmersDetails.getNumber());
        if (numberOfFarmersDetails.getSex().getId() != null) {
            numberOfFarmers.setSex(em.getReference(Sex.class, numberOfFarmersDetails.getSex().getId()));
        }
        if (numberOfFarmersDetails.getAgeBracket().getId() != null) {
            numberOfFarmers.setAgeBracket(em.getReference(AgeBracket.class, numberOfFarmersDetails.getAgeBracket().getId()));
        }
        if (numberOfFarmersDetails.getNumberDescription().getId() != null) {
            numberOfFarmers.setNumberDescription(em.getReference(NumberDescription.class, numberOfFarmersDetails.getNumberDescription().getId()));
        }
        if (numberOfFarmersDetails.getSampledFarmerData().getId() != null) {
            numberOfFarmers.setSampledFarmerData(em.getReference(SampledFarmerData.class, numberOfFarmersDetails.getSampledFarmerData().getId()));
        }
        try {
            em.persist(numberOfFarmers);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return numberOfFarmers.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public NumberOfFarmersDetails retrieveNumberOfFarmers(int id) throws MilesException {

        setQ(em.createNamedQuery("NumberOfFarmers.findById"));
        q.setParameter("id", id);
        NumberOfFarmers numberOfFarmers;
        try {
            numberOfFarmers = (NumberOfFarmers) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertNumberOfFarmersToNumberOfFarmersDetails(numberOfFarmers);

    }

    @Override
    public List<NumberOfFarmersDetails> retrievePeople() throws MilesException {

        setQ(em.createNamedQuery("NumberOfFarmers.findAll"));
        List<NumberOfFarmers> people;
        try {
            people = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertPeopleToNumberOfFarmersDetailsList(people);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editNumberOfFarmers(NumberOfFarmersDetails numberOfFarmersDetails) throws MilesException {

        if (numberOfFarmersDetails == null) {
            throw new InvalidArgumentException("error_001_01");
        } else if (numberOfFarmersDetails.getId() == null) {
            throw new InvalidStateException("error_001_04");
        } else if (numberOfFarmersDetails.getNumberDescription() == null) {
            throw new InvalidArgumentException("error_001_02");
        } else if (numberOfFarmersDetails.getSampledFarmerData() == null) {
            throw new InvalidArgumentException("error_001_03");
        }

        NumberOfFarmers numberOfFarmers = em.find(NumberOfFarmers.class, numberOfFarmersDetails.getId());
        numberOfFarmers.setId(numberOfFarmersDetails.getId());
        numberOfFarmers.setNumber(numberOfFarmersDetails.getNumber());
        if (numberOfFarmersDetails.getSex().getId() != null) {
            numberOfFarmers.setSex(em.getReference(Sex.class, numberOfFarmersDetails.getSex().getId()));
        }
        if (numberOfFarmersDetails.getAgeBracket().getId() != null) {
            numberOfFarmers.setAgeBracket(em.getReference(AgeBracket.class, numberOfFarmersDetails.getAgeBracket().getId()));
        }
        if (numberOfFarmersDetails.getNumberDescription().getId() != null) {
            numberOfFarmers.setNumberDescription(em.getReference(NumberDescription.class, numberOfFarmersDetails.getNumberDescription().getId()));
        }
        if (numberOfFarmersDetails.getSampledFarmerData().getId() != null) {
            numberOfFarmers.setSampledFarmerData(em.getReference(SampledFarmerData.class, numberOfFarmersDetails.getSampledFarmerData().getId()));
        }

        try {
            em.merge(numberOfFarmers);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">

    @Override
    public void removeNumberOfFarmers(int id) throws MilesException {

        NumberOfFarmers numberOfFarmers = em.find(NumberOfFarmers.class, id);
        try {
            em.remove(numberOfFarmers);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">
    @Override
    public NumberOfFarmersDetails convertNumberOfFarmersToNumberOfFarmersDetails(NumberOfFarmers numberOfFarmers) {

        SampledFarmerDataDetails sampledFarmerDataDetails = null;
        if (numberOfFarmers.getSampledFarmerData().getId() != null) {
            sampledFarmerDataDetails = sampledFarmerDataService.convertSampledFarmerDataToSampledFarmerDataDetails(numberOfFarmers.getSampledFarmerData());
        }

        NumberDescriptionDetails numberDescriptionDetails = null;
        if (numberOfFarmers.getNumberDescription().getId() != null) {
            numberDescriptionDetails = numberDescriptionService.convertNumberDescriptionToNumberDescriptionDetails(numberOfFarmers.getNumberDescription());
        }

        AgeBracketDetails ageBracketDetails = null;
        if (numberOfFarmers.getAgeBracket().getId() != null) {
            ageBracketDetails = numberOfFarmersRoleService.convertAgeBracketToAgeBracketDetails(numberOfFarmers.getAgeBracket());
        }

        NumberOfFarmersDetails numberOfFarmersDetails = new NumberOfFarmersDetails(numberOfFarmers.getId());
        numberOfFarmersDetails.setAgeBracket(ageBracketDetails);
        numberOfFarmersDetails.setNumber(numberOfFarmers.getNumber());
        numberOfFarmersDetails.setNumberDescription(numberDescriptionDetails);
        numberOfFarmersDetails.setSampledFarmerData(sampledFarmerDataDetails);
        numberOfFarmersDetails.setSex(SexDetail.getSexDetail(numberOfFarmers.getSex().getId()));

        return numberOfFarmersDetails;

    }

    private List<NumberOfFarmersDetails> convertPeopleToNumberOfFarmersDetailsList(List<NumberOfFarmers> people) {

        List<NumberOfFarmersDetails> numberOfFarmersDetailsList = new ArrayList<>();
        for (NumberOfFarmers numberOfFarmers : people) {
            numberOfFarmersDetailsList.add(convertNumberOfFarmersToNumberOfFarmersDetails(numberOfFarmers));

        }

        return numberOfFarmersDetailsList;

    }

//</editor-fold>
    @EJB
    private SampledFarmerDataRequestsLocal sampledFarmerDataService;
    @EJB
    private LocationRequestsLocal locationService;
    @EJB
    private AgeBracketRequestsLocal numberOfFarmersRoleService;
    @EJB
    private NumberDescriptionRequestsLocal numberDescriptionService;
}
