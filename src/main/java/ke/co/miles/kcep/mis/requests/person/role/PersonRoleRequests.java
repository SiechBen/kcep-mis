/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.person.role;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.PersonRole;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.utilities.PersonRoleDetail;

/**
 *
 * @author siech
 */
@Stateless
public class PersonRoleRequests extends EntityRequests implements PersonRoleRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addPersonRole(PersonRoleDetail personRoleDetail) throws MilesException {

        if (personRoleDetail == null) {
            throw new InvalidArgumentException("error_005_01");
        } else if (personRoleDetail.getPersonRole() == null) {
            throw new InvalidArgumentException("error_005_02");
        } else if (personRoleDetail.getPersonRole().length() > 200) {
            throw new InvalidArgumentException("error_005_03");
        }

        PersonRole personRole;
        setQ(em.createNamedQuery("PersonRole.findByPersonRole"));
        q.setParameter("personRole", personRoleDetail.getPersonRole());
        try {
            personRole = (PersonRole) q.getSingleResult();
        } catch (Exception e) {
            personRole = null;
        }
        if (personRole != null) {
            throw new InvalidArgumentException("error_005_04");
        }

        personRole = new PersonRole();
        personRole.setPersonRole(personRoleDetail.getPersonRole());

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
    @SuppressWarnings("unchecked")
    public List<PersonRoleDetail> retrievePersonRolesNotAdminOrPcu() throws MilesException {

        List<PersonRole> personRoles = new ArrayList<>();
        setQ(em.createNamedQuery("PersonRole.findNotAdminOrPcu"));
        List<Short> personRoleIds = new ArrayList<>();
        personRoleIds.add(PersonRoleDetail.SYSTEM_ADMIN.getId());
        personRoleIds.add(PersonRoleDetail.NATIONAL_OFFICER.getId());
        q.setParameter("personRoleIds", personRoleIds);
        try {
            personRoles = q.getResultList();
            for (PersonRole pd : personRoles) {
            }
        } catch (Exception e) {
        }

        return convertPersonRolesToPersonRoleDetailList(personRoles);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<PersonRoleDetail> retrievePersonRoles() throws MilesException {
        List<PersonRole> personRoles = new ArrayList<>();
        setQ(em.createNamedQuery("PersonRole.findAll"));
        try {
            personRoles = q.getResultList();
        } catch (Exception e) {
        }

        return convertPersonRolesToPersonRoleDetailList(personRoles);
    }

    @Override
    public PersonRoleDetail retrievePersonRole(int id) throws MilesException {
        PersonRole personRole;
        setQ(em.createNamedQuery("PersonRole.findById"));
        q.setParameter("id", id);
        try {
            personRole = (PersonRole) q.getSingleResult();
        } catch (Exception e) {
            throw new InvalidStateException("error_000_01");
        }

        return convertPersonRoleToPersonRoleDetail(personRole);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">

    @Override
    public void editPersonRole(PersonRoleDetail personRoleDetail) throws MilesException {

        if (personRoleDetail == null) {
            throw new InvalidArgumentException("error_005_01");
        } else if (personRoleDetail.getId() == null) {
            throw new InvalidArgumentException("error_005_05");
        } else if (personRoleDetail.getPersonRole() == null) {
            throw new InvalidArgumentException("error_005_02");
        } else if (personRoleDetail.getPersonRole().length() > 200) {
            throw new InvalidArgumentException("error_005_03");
        }

        PersonRole personRole;
        setQ(em.createNamedQuery("PersonRole.findByPersonRole"));
        q.setParameter("personRole", personRoleDetail.getPersonRole());
        try {
            personRole = (PersonRole) q.getSingleResult();
        } catch (Exception e) {
            personRole = null;
        }
        if (personRole != null) {
            if (personRole.getId().equals(personRoleDetail.getId())) {
                throw new InvalidArgumentException("error_005_04");
            }
        }

        personRole = em.find(PersonRole.class, personRoleDetail.getId());
        personRole.setId(personRoleDetail.getId());
        personRole.setPersonRole(personRoleDetail.getPersonRole());

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
    public PersonRoleDetail convertPersonRoleToPersonRoleDetail(PersonRole personRole) {

        PersonRoleDetail personRoleDetail = PersonRoleDetail.getPersonRoleDetail(personRole.getId());
        return personRoleDetail;

    }

    private List<PersonRoleDetail> convertPersonRolesToPersonRoleDetailList(List<PersonRole> personRoles) {

        List<PersonRoleDetail> personRoleDetailList = new ArrayList<>();
        for (PersonRole personRole : personRoles) {
            personRoleDetailList.add(convertPersonRoleToPersonRoleDetail(personRole));
        }

        return personRoleDetailList;

    }

//</editor-fold>
}
