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
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.SampledFarmerData;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.SampledFarmerDataDetails;

/**
 *
 * @author siech
 */
@Stateless
public class SampledFarmerDataRequests extends EntityRequests implements SampledFarmerDataRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addData(SampledFarmerDataDetails sampledFarmerDataDetails) throws MilesException {

        if (sampledFarmerDataDetails == null) {
            throw new InvalidArgumentException("error_012_01");
        } else if (sampledFarmerDataDetails.getWardExtensionOfficer() == null) {
            throw new InvalidArgumentException("error_012_02");
        }

        SampledFarmerData sampledFarmerData = new SampledFarmerData();
        sampledFarmerData.setPostHarvestLosses(sampledFarmerDataDetails.getPostHarvestLosses());
        sampledFarmerData.setSeason(sampledFarmerDataDetails.getSeason());
        sampledFarmerData.setProductivityPerCropPerFarmer(sampledFarmerDataDetails.getProductivityPerCropPerFarmer());
        sampledFarmerData.setWardExtensionOfficer(getEm().getReference(Person.class, sampledFarmerDataDetails.getWardExtensionOfficer().getId()));
        if (sampledFarmerData.getWardExtensionOfficer().getId() != null) {
            sampledFarmerData.setWardExtensionOfficer(getEm().getReference(Person.class, sampledFarmerDataDetails.getWardExtensionOfficer().getId()));
        }

        try {
            getEm().persist(sampledFarmerData);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return sampledFarmerData.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<SampledFarmerDataDetails> retrieveData() throws MilesException {
        List<SampledFarmerData> sampledFarmerDatas = new ArrayList<>();
        setQ(getEm().createNamedQuery("SampledFarmerData.findAll"));
        try {
            sampledFarmerDatas = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertSampledFarmerDatasToSampledFarmerDataDetailsList(sampledFarmerDatas);
    }

    @Override
    public SampledFarmerDataDetails retrieveData(int id) throws MilesException {
        SampledFarmerData sampledFarmerData;
        setQ(getEm().createNamedQuery("SampledFarmerData.findById"));
        getQ().setParameter("id", id);
        try {
            sampledFarmerData = (SampledFarmerData) getQ().getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertSampledFarmerDataToSampledFarmerDataDetails(sampledFarmerData);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editData(SampledFarmerDataDetails sampledFarmerDataDetails) throws MilesException {

        if (sampledFarmerDataDetails == null) {
            throw new InvalidArgumentException("error_012_01");
        } else if (sampledFarmerDataDetails.getId() == null) {
            throw new InvalidArgumentException("error_012_03");
        } else if (sampledFarmerDataDetails.getWardExtensionOfficer() == null) {
            throw new InvalidArgumentException("error_012_02");
        }

        SampledFarmerData sampledFarmerData = getEm().find(SampledFarmerData.class, sampledFarmerDataDetails.getId());
        sampledFarmerData.setId(sampledFarmerDataDetails.getId());
        sampledFarmerData.setSeason(sampledFarmerDataDetails.getSeason());
        sampledFarmerData.setPostHarvestLosses(sampledFarmerDataDetails.getPostHarvestLosses());
        sampledFarmerData.setProductivityPerCropPerFarmer(sampledFarmerDataDetails.getProductivityPerCropPerFarmer());
        if (sampledFarmerData.getWardExtensionOfficer().getId() != null) {
            sampledFarmerData.setWardExtensionOfficer(getEm().getReference(Person.class, sampledFarmerDataDetails.getWardExtensionOfficer().getId()));
        }

        try {
            getEm().merge(sampledFarmerData);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeData(int id) throws MilesException {
        SampledFarmerData sampledFarmerData = getEm().find(SampledFarmerData.class, id);
        try {
            getEm().remove(sampledFarmerData);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public SampledFarmerDataDetails convertSampledFarmerDataToSampledFarmerDataDetails(SampledFarmerData sampledFarmerData) {

        SampledFarmerDataDetails sampledFarmerDataDetails = new SampledFarmerDataDetails(sampledFarmerData.getId());

        sampledFarmerDataDetails.setPostHarvestLosses(sampledFarmerData.getPostHarvestLosses());
        sampledFarmerDataDetails.setSeason(sampledFarmerData.getSeason());
        sampledFarmerDataDetails.setProductivityPerCropPerFarmer(sampledFarmerData.getProductivityPerCropPerFarmer());
        sampledFarmerDataDetails.setWardExtensionOfficer(personService.convertPersonToPersonDetails(sampledFarmerData.getWardExtensionOfficer()));

        return sampledFarmerDataDetails;

    }

    private List<SampledFarmerDataDetails> convertSampledFarmerDatasToSampledFarmerDataDetailsList(List<SampledFarmerData> sampledFarmerDatas) {

        List<SampledFarmerDataDetails> sampledFarmerDataDetailsList = new ArrayList<>();
        for (SampledFarmerData sampledFarmerData : sampledFarmerDatas) {
            sampledFarmerDataDetailsList.add(convertSampledFarmerDataToSampledFarmerDataDetails(sampledFarmerData));
        }

        return sampledFarmerDataDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;

}
