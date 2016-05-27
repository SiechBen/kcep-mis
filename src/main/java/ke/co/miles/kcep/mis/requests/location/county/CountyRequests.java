/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.location.county;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.County;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.CountyDetails;

/**
 *
 * @author siech
 */
@Stateless
public class CountyRequests extends EntityRequests implements CountyRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addCounty(CountyDetails countyDetails) throws MilesException {

        if (countyDetails == null) {
            throw new InvalidArgumentException("error_010_01");
        } else if (countyDetails.getName() == null) {
            throw new InvalidArgumentException("error_010_02");
        } else if (countyDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_010_03");
        }

        County county;
        q = em.createNamedQuery("County.findByName");
        q.setParameter("county", countyDetails.getName());
        try {
            county = (County) q.getSingleResult();
        } catch (Exception e) {
            county = null;
        }
        if (county != null) {
            throw new InvalidArgumentException("error_010_04");
        }

        county = new County();
        county.setName(countyDetails.getName());

        try {
            em.persist(county);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return county.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<CountyDetails> retrieveCountys() throws MilesException {
        List<County> countys = new ArrayList<>();
        q = em.createNamedQuery("County.findAll");
        try {
            countys = q.getResultList();
        } catch (Exception e) {
        }

        return convertCountysToCountyDetailsList(countys);
    }

    @Override
    public CountyDetails retrieveCounty(int id) throws MilesException {
        County county;
        q = em.createNamedQuery("County.findById");
        q.setParameter("id", id);
        try {
            county = (County) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertCountyToCountyDetails(county);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editCounty(CountyDetails countyDetails) throws MilesException {

        if (countyDetails == null) {
            throw new InvalidArgumentException("error_010_01");
        } else if (countyDetails.getId() == null) {
            throw new InvalidArgumentException("error_010_05");
        } else if (countyDetails.getName() == null) {
            throw new InvalidArgumentException("error_010_02");
        } else if (countyDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_010_03");
        }

        County county;
        q = em.createNamedQuery("County.findByName");
        q.setParameter("county", countyDetails.getName());
        try {
            county = (County) q.getSingleResult();
        } catch (Exception e) {
            county = null;
        }
        if (county != null) {
            if (county.getId().equals(countyDetails.getId())) {
                throw new InvalidArgumentException("error_010_04");
            }
        }

        county = em.find(County.class, countyDetails.getId());
        county.setName(countyDetails.getName());

        try {
            em.merge(county);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeCounty(int id) throws MilesException {
        County county = em.find(County.class, id);
        try {
            em.remove(county);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public CountyDetails convertCountyToCountyDetails(County county) {

        CountyDetails countyDetails = new CountyDetails(county.getId());
        countyDetails.setName(county.getName());
        return countyDetails;

    }

    private List<CountyDetails> convertCountysToCountyDetailsList(List<County> countys) {

        List<CountyDetails> countyDetailsList = new ArrayList<>();
        countys.stream().forEach((county) -> {
            countyDetailsList.add(convertCountyToCountyDetails(county));
        });
        return countyDetailsList;

    }

//</editor-fold>
}
