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
import ke.co.miles.kcep.mis.entities.Region;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.CountyDetails;
import ke.co.miles.kcep.mis.utilities.RegionDetail;

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
        } else if (countyDetails.getRegion() == null) {
            throw new InvalidArgumentException("error_010_04");
        }

        County county;
        setQ(getEm().createNamedQuery("County.findByName"));
        getQ().setParameter("county", countyDetails.getName());
        try {
            county = (County) getQ().getSingleResult();
        } catch (Exception e) {
            county = null;
        }
        if (county != null) {
            throw new InvalidArgumentException("error_010_05");
        }

        county = new County();
        county.setName(countyDetails.getName());
        county.setRegion(getEm().getReference(Region.class, countyDetails.getRegion().getId()));

        try {
            getEm().persist(county);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return county.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<CountyDetails> retrieveCounties() throws MilesException {
        List<County> countys = new ArrayList<>();
        setQ(getEm().createNamedQuery("County.findAll"));
        try {
            countys = getQ().getResultList();
        } catch (Exception e) {
        }

        return convertCountiesToCountyDetailsList(countys);
    }

    @Override
    public CountyDetails retrieveCounty(int id) throws MilesException {
        County county;
        setQ(getEm().createNamedQuery("County.findById"));
        getQ().setParameter("id", id);
        try {
            county = (County) getQ().getSingleResult();
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
            throw new InvalidArgumentException("error_010_06");
        } else if (countyDetails.getName() == null) {
            throw new InvalidArgumentException("error_010_02");
        } else if (countyDetails.getName().length() > 45) {
            throw new InvalidArgumentException("error_010_03");
        } else if (countyDetails.getRegion() == null) {
            throw new InvalidArgumentException("error_010_04");
        }

        County county;
        setQ(getEm().createNamedQuery("County.findByName"));
        getQ().setParameter("county", countyDetails.getName());
        try {
            county = (County) getQ().getSingleResult();
        } catch (Exception e) {
            county = null;
        }
        if (county != null) {
            if (county.getId().equals(countyDetails.getId())) {
                throw new InvalidArgumentException("error_010_05");
            }
        }

        county = getEm().find(County.class, countyDetails.getId());
        county.setId(countyDetails.getId());
        county.setName(countyDetails.getName());
        county.setRegion(getEm().getReference(Region.class, countyDetails.getRegion().getId()));

        try {
            getEm().merge(county);
            getEm().flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeCounty(int id) throws MilesException {
        County county = getEm().find(County.class, id);
        try {
            getEm().remove(county);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public CountyDetails convertCountyToCountyDetails(County county) {

        CountyDetails countyDetails = new CountyDetails();
        try {
            countyDetails.setId(county.getId());
        } catch (Exception e) {
        }
        try {
            countyDetails.setName(county.getName());
        } catch (Exception e) {
        }
        try {
            countyDetails.setRegion(RegionDetail.getRegionDetail(county.getRegion().getId()));
        } catch (Exception e) {
        }
        return countyDetails;

    }

    private List<CountyDetails> convertCountiesToCountyDetailsList(List<County> countys) {

        List<CountyDetails> countyDetailsList = new ArrayList<>();
        for (County county : countys) {
            countyDetailsList.add(convertCountyToCountyDetails(county));
        }

        return countyDetailsList;

    }

//</editor-fold>
}
