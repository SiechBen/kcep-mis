/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.implementingpartner;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ImplementingPartner;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ImplementingPartnerDetails;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;

/**
 *
 * @author siech
 */
@Stateless
public class ImplementingPartnerRequests extends EntityRequests implements ImplementingPartnerRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addImplementingPartner(ImplementingPartnerDetails implementingPartnerDetails) throws MilesException {

        if (implementingPartnerDetails == null) {
            throw new InvalidArgumentException("error_022_01");
        } else if (implementingPartnerDetails.getPersonRole() == null) {
            throw new InvalidArgumentException("error_022_02");
        }

        ImplementingPartner implementingPartner;
        setQ(em.createNamedQuery("ImplementingPartner.findByPersonRoleId"));
        q.setParameter("personRoleId", implementingPartnerDetails.getPersonRole().getId());
        try {
            implementingPartner = (ImplementingPartner) q.getSingleResult();
        } catch (Exception e) {
            implementingPartner = null;
        }
        if (implementingPartner != null) {
            throw new InvalidArgumentException("error_022_03");
        }

        implementingPartner = new ImplementingPartner();
        implementingPartner.setPersonRole(em.getReference(PersonRole.class, implementingPartnerDetails.getPersonRole().getId()));

        try {
            em.persist(implementingPartner);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return implementingPartner.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    @SuppressWarnings("unchecked")
    public List<ImplementingPartnerDetails> retrieveImplementingPartners() throws MilesException {
        List<ImplementingPartner> implementingPartners = new ArrayList<>();
        setQ(em.createNamedQuery("ImplementingPartner.findAll"));
        try {
            implementingPartners = q.getResultList();
        } catch (Exception e) {
        }

        return convertImplementingPartnersToImplementingPartnerDetailsList(implementingPartners);
    }

    @Override
    public ImplementingPartnerDetails retrieveImplementingPartner(int id) throws MilesException {
        ImplementingPartner implementingPartner;
        setQ(em.createNamedQuery("ImplementingPartner.findById"));
        q.setParameter("id", id);
        try {
            implementingPartner = (ImplementingPartner) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertImplementingPartnerToImplementingPartnerDetails(implementingPartner);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editImplementingPartner(ImplementingPartnerDetails implementingPartnerDetails) throws MilesException {

        if (implementingPartnerDetails == null) {
            throw new InvalidArgumentException("error_022_01");
        } else if (implementingPartnerDetails.getId() == null) {
            throw new InvalidArgumentException("error_022_04");
        } else if (implementingPartnerDetails.getPersonRole() == null) {
            throw new InvalidArgumentException("error_022_02");
        }

        ImplementingPartner implementingPartner;
        setQ(em.createNamedQuery("ImplementingPartner.findByPersonRoleId"));
        q.setParameter("personRoleId", implementingPartnerDetails.getPersonRole().getId());
        try {
            implementingPartner = (ImplementingPartner) q.getSingleResult();
        } catch (Exception e) {
            implementingPartner = null;
        }
        if (implementingPartner != null) {
            if (implementingPartner.getId().equals(implementingPartnerDetails.getId())) {
                throw new InvalidArgumentException("error_022_03");
            }
        }

        implementingPartner = em.find(ImplementingPartner.class, implementingPartnerDetails.getId());
        implementingPartner.setId(implementingPartnerDetails.getId());
        implementingPartner.setPersonRole(em.getReference(PersonRole.class, implementingPartnerDetails.getPersonRole().getId()));

        try {
            em.merge(implementingPartner);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeImplementingPartner(int id) throws MilesException {
        ImplementingPartner implementingPartner = em.find(ImplementingPartner.class, id);
        try {
            em.remove(implementingPartner);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ImplementingPartnerDetails convertImplementingPartnerToImplementingPartnerDetails(ImplementingPartner implementingPartner) {

        ImplementingPartnerDetails implementingPartnerDetails = new ImplementingPartnerDetails();
        try {
            implementingPartnerDetails.setId(implementingPartner.getId());
        } catch (Exception e) {
        }
        try {
            implementingPartnerDetails.setPersonRole(PersonRoleDetail.getPersonRoleDetail(implementingPartner.getPersonRole().getId()));
        } catch (Exception e) {
        }
        return implementingPartnerDetails;

    }

    private List<ImplementingPartnerDetails> convertImplementingPartnersToImplementingPartnerDetailsList(List<ImplementingPartner> implementingPartners) {

        List<ImplementingPartnerDetails> implementingPartnerDetailsList = new ArrayList<>();
        for (ImplementingPartner implementingPartner : implementingPartners) {
            implementingPartnerDetailsList.add(convertImplementingPartnerToImplementingPartnerDetails(implementingPartner));
        }

        return implementingPartnerDetailsList;

    }

//</editor-fold>
}
