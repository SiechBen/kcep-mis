/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.programme;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.Programme;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.ProgrammeDetails;

/**
 *
 * @author siech
 */
@Stateless
public class ProgrammeRequests extends EntityRequests implements ProgrammeRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addProgramme(ProgrammeDetails programmeDetails) throws MilesException {

        if (programmeDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        }

        Programme programme = new Programme();
        programme.setUnit(programmeDetails.getUnit());
        programme.setActivity(programmeDetails.getActivity());
        programme.setEndPeriod(programmeDetails.getEndPeriod());
        programme.setAwpTarget(programmeDetails.getAwpTarget());
        programme.setStartPeriod(programmeDetails.getStartPeriod());
        programme.setValueAchieved(programmeDetails.getValueAchieved());
        programme.setRequestedBudget(programmeDetails.getRequestedBudget());
        programme.setProgrammeTarget(programmeDetails.getProgrammeTarget());
        programme.setActualExpenditure(programmeDetails.getActualExpenditure());

        try {
            em.persist(programme);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return programme.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<ProgrammeDetails> retrieveProgrammes() throws MilesException {
        List<Programme> programmes = new ArrayList<>();
        q = em.createNamedQuery("Programme.findAll");
        try {
            programmes = q.getResultList();
        } catch (Exception e) {
        }

        return convertProgrammesToProgrammeDetailsList(programmes);
    }

    @Override
    public ProgrammeDetails retrieveProgramme(int id) throws MilesException {
        Programme programme;
        q = em.createNamedQuery("Programme.findById");
        q.setParameter("id", id);
        try {
            programme = (Programme) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertProgrammeToProgrammeDetails(programme);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editProgramme(ProgrammeDetails programmeDetails) throws MilesException {

        if (programmeDetails == null) {
            throw new InvalidArgumentException("error_017_01");
        } else if (programmeDetails.getId() == null) {
            throw new InvalidArgumentException("error_017_02");
        }

        Programme programme = em.find(Programme.class, programmeDetails.getId());
        programme.setId(programmeDetails.getId());
        programme.setUnit(programmeDetails.getUnit());
        programme.setActivity(programmeDetails.getActivity());
        programme.setEndPeriod(programmeDetails.getEndPeriod());
        programme.setAwpTarget(programmeDetails.getAwpTarget());
        programme.setStartPeriod(programmeDetails.getStartPeriod());
        programme.setValueAchieved(programmeDetails.getValueAchieved());
        programme.setRequestedBudget(programmeDetails.getRequestedBudget());
        programme.setProgrammeTarget(programmeDetails.getProgrammeTarget());
        programme.setActualExpenditure(programmeDetails.getActualExpenditure());

        try {
            em.merge(programme);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeProgramme(int id) throws MilesException {
        Programme programme = em.find(Programme.class, id);
        try {
            em.remove(programme);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    private ProgrammeDetails convertProgrammeToProgrammeDetails(Programme programme) {

        ProgrammeDetails programmeDetails = new ProgrammeDetails(programme.getId());
        programmeDetails.setUnit(programme.getUnit());
        programmeDetails.setActivity(programme.getActivity());
        programmeDetails.setEndPeriod(programme.getEndPeriod());
        programmeDetails.setAwpTarget(programme.getAwpTarget());
        programmeDetails.setStartPeriod(programme.getStartPeriod());
        programmeDetails.setValueAchieved(programme.getValueAchieved());
        programmeDetails.setRequestedBudget(programme.getRequestedBudget());
        programmeDetails.setProgrammeTarget(programme.getProgrammeTarget());
        programmeDetails.setActualExpenditure(programme.getActualExpenditure());

        return programmeDetails;

    }

    private List<ProgrammeDetails> convertProgrammesToProgrammeDetailsList(List<Programme> programmes) {

        List<ProgrammeDetails> programmeDetailsList = new ArrayList<>();
        for (Programme programme : programmes) {
            programmeDetailsList.add(convertProgrammeToProgrammeDetails(programme));
        }

        return programmeDetailsList;

    }

//</editor-fold>
}
