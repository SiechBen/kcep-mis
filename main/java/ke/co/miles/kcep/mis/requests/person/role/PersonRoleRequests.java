/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person.role;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetails;

/**
 *
 * @author siech
 */
@Stateless
public class PersonRoleRequests extends EntityRequests implements PersonRoleRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addPersonRole(PersonRoleDetails personRoleDetails) throws MilesException {

        if (personRoleDetails == null) {
            throw new InvalidArgumentException("error_005_01");
        } else if (personRoleDetails.getPersonRole() == null) {
            throw new InvalidArgumentException("error_005_02");
        } else if (personRoleDetails.getPersonRole().length() > 200) {
            throw new InvalidArgumentException("error_005_03");
        }

        PersonRole personRole;
        q = em.createNamedQuery("PersonRole.findByPersonRole");
        q.setParameter("personRole", personRoleDetails.getPersonRole());
        try {
            personRole = (PersonRole) q.getSingleResult();
        } catch (Exception e) {
            personRole = null;
        }
        if (personRole != null) {
            throw new InvalidArgumentException("error_005_04");
        }

        personRole = new PersonRole();
        personRole.setPersonRole(personRoleDetails.getPersonRole());

        try {
            em.persist(personRole);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return personRole.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @Override
    public List<PersonRoleDetails> retrievePersonRoles() throws MilesException {
        List<PersonRole> personRoles = new ArrayList<>();
        q = em.createNamedQuery("PersonRole.findAll");
        try {
            personRoles = q.getResultList();
        } catch (Exception e) {
        }

        return convertPersonRolesToPersonRoleDetailsList(personRoles);
    }

    @Override
    public PersonRoleDetails retrievePersonRole(int id) throws MilesException {
        PersonRole personRole;
        q = em.createNamedQuery("PersonRole.findById");
        q.setParameter("id", id);
        try {
            personRole = (PersonRole) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPersonRoleToPersonRoleDetails(personRole);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPersonRole(PersonRoleDetails personRoleDetails) throws MilesException {

        if (personRoleDetails == null) {
            throw new InvalidArgumentException("error_005_01");
        } else if (personRoleDetails.getId() == null) {
            throw new InvalidArgumentException("error_005_05");
        } else if (personRoleDetails.getPersonRole() == null) {
            throw new InvalidArgumentException("error_005_02");
        } else if (personRoleDetails.getPersonRole().length() > 200) {
            throw new InvalidArgumentException("error_005_03");
        }

        PersonRole personRole;
        q = em.createNamedQuery("PersonRole.findByPersonRole");
        q.setParameter("personRole", personRoleDetails.getPersonRole());
        try {
            personRole = (PersonRole) q.getSingleResult();
        } catch (Exception e) {
            personRole = null;
        }
        if (personRole != null) {
            if (personRole.getId().equals(personRoleDetails.getId())) {
                throw new InvalidArgumentException("error_005_04");
            }
        }

        personRole = em.find(PersonRole.class, personRoleDetails.getId());
        personRole.setPersonRole(personRoleDetails.getPersonRole());

        try {
            em.merge(personRole);
            em.flush();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removePersonRole(int id) throws MilesException {
        PersonRole personRole = em.find(PersonRole.class, id);
        try {
            em.remove(personRole);
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public PersonRoleDetails convertPersonRoleToPersonRoleDetails(PersonRole personRole) {

        PersonRoleDetails personRoleDetails = new PersonRoleDetails(personRole.getId());
        personRoleDetails.setPersonRole(personRole.getPersonRole());
        return personRoleDetails;

    }

    private List<PersonRoleDetails> convertPersonRolesToPersonRoleDetailsList(List<PersonRole> personRoles) {

        List<PersonRoleDetails> personRoleDetailsList = new ArrayList<>();
        personRoles.stream().forEach((personRole) -> {
            personRoleDetailsList.add(convertPersonRoleToPersonRoleDetails(personRole));
        });
        return personRoleDetailsList;

    }

//</editor-fold>
    
}
