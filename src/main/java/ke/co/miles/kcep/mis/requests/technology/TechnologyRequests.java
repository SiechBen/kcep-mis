/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.technology;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Person;
import ke.co.miles.kcep.mis.entities.Technology;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.person.PersonRequestsLocal;
import ke.co.miles.kcep.mis.utilities.TechnologyDetails;

/**
 *
 * @author siech
 */
@Stateless
public class TechnologyRequests extends EntityRequests implements TechnologyRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addTechnology(TechnologyDetails technologyDetails) throws MilesException {

        if (technologyDetails == null) {
            throw new InvalidArgumentException("error_026_01");
        } else if (technologyDetails.getKalroOfficer() == null) {
            throw new InvalidArgumentException("error_026_02");
        } else if (technologyDetails.getName() != null && technologyDetails.getName().trim().length() > 45) {
            throw new InvalidArgumentException("error_026_03");
        } else if (technologyDetails.getTypePurpose() != null && technologyDetails.getTypePurpose().trim().length() > 45) {
            throw new InvalidArgumentException("error_026_04");
        }

        Technology technology = new Technology();
        technology.setName(technologyDetails.getName());
        technology.setTypePurpose(technologyDetails.getTypePurpose());
        technology.setTargetSubCounties(technologyDetails.getTargetSubCounties());
        technology.setNumberOfStudiesConducted(technologyDetails.getNumberOfStudiesConducted());
        try {
            technology.setKalroOfficer(em.getReference(Person.class, technologyDetails.getKalroOfficer().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            technology.setKalroOfficer(null);
        }

        try {
            em.persist(technology);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return technology.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<TechnologyDetails> retrieveTechnologies() throws MilesException {
        List<Technology> technologies = new ArrayList<>();
        setQ(em.createNamedQuery("Technology.findAll"));
        try {
            technologies = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        return convertTechnologiesToTechnologyDetailsList(technologies);
    }

    @Override
    public TechnologyDetails retrieveTechnology(int id) throws MilesException {
        Technology technology;
        setQ(em.createNamedQuery("Technology.findById"));
        q.setParameter("id", id);
        try {
            technology = (Technology) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertTechnologyToTechnologyDetails(technology);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editTechnology(TechnologyDetails technologyDetails) throws MilesException {

        if (technologyDetails == null) {
            throw new InvalidArgumentException("error_026_01");
        } else if (technologyDetails.getId() == null) {
            throw new InvalidArgumentException("error_026_05");
        } else if (technologyDetails.getKalroOfficer() == null) {
            throw new InvalidArgumentException("error_026_02");
        } else if (technologyDetails.getName() != null && technologyDetails.getName().trim().length() > 45) {
            throw new InvalidArgumentException("error_026_03");
        } else if (technologyDetails.getTypePurpose() != null && technologyDetails.getTypePurpose().trim().length() > 45) {
            throw new InvalidArgumentException("error_026_04");
        }

        Technology technology = em.find(Technology.class, technologyDetails.getId());
        technology.setId(technologyDetails.getId());
        technology.setName(technologyDetails.getName());
        technology.setTypePurpose(technologyDetails.getTypePurpose());
        technology.setTargetSubCounties(technologyDetails.getTargetSubCounties());
        technology.setNumberOfStudiesConducted(technologyDetails.getNumberOfStudiesConducted());
        try {
            technology.setKalroOfficer(em.getReference(Person.class, technologyDetails.getKalroOfficer().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            technology.setKalroOfficer(null);
        }

        try {
            em.merge(technology);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeTechnology(int id) throws MilesException {
        Technology technology = em.find(Technology.class, id);
        try {
            em.remove(technology);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private TechnologyDetails convertTechnologyToTechnologyDetails(Technology technology) {

        TechnologyDetails technologyDetails = new TechnologyDetails(technology.getId());
        technologyDetails.setName(technology.getName());
        technologyDetails.setTypePurpose(technology.getTypePurpose());
        technologyDetails.setTargetSubCounties(technology.getTargetSubCounties());
        technologyDetails.setNumberOfStudiesConducted(technology.getNumberOfStudiesConducted());
        technologyDetails.setKalroOfficer(personService.convertPersonToPersonDetails(technology.getKalroOfficer()));

        return technologyDetails;

    }

    private List<TechnologyDetails> convertTechnologiesToTechnologyDetailsList(List<Technology> technologies) {

        List<TechnologyDetails> technologyDetailsList = new ArrayList<>();
        for (Technology technology : technologies) {
            technologyDetailsList.add(convertTechnologyToTechnologyDetails(technology));
        }

        return technologyDetailsList;

    }

//</editor-fold>
    @EJB
    private PersonRequestsLocal personService;
}
