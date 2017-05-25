///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ke.co.miles.kcep.mis.requests.technology.targettechnologyTargetTechnologyTargetCounty;
//
//import java.util.ArrayList;
//import java.util.List;
//import javax.ejb.Stateless;
//import ke.co.miles.kcep.mis.defaults.EntityRequests;
//import ke.co.miles.kcep.mis.entities.TechnologyTargetCounty;
//import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
//import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
//import ke.co.miles.kcep.mis.exceptions.MilesException;
//import ke.co.miles.kcep.mis.requests.technology.targetcounty.TechnologyTargetCountyRequestsLocal;
//import ke.co.miles.kcep.mis.utilities.TechnologyTargetCountyDetails;
//
///**
// *
// * @author siech
// */
//@Stateless
//public class TechnologyTargetCountyRequests extends EntityRequests implements TechnologyTargetCountyRequestsLocal {
//
////<editor-fold defaultstate="collapsed" desc="Create">
//    @Override
//    public int addTechnologyTargetCounty(TechnologyTargetCountyDetails technologyTargetTechnologyTargetCountyDetails) throws MilesException {
//
//        if (technologyTargetTechnologyTargetCountyDetails == null) {
//            throw new InvalidArgumentException("error_027_01");
//        } else if (technologyTargetTechnologyTargetCountyDetails.getName() == null) {
//            throw new InvalidArgumentException("error_027_02");
//        } else if (technologyTargetTechnologyTargetCountyDetails.getName().length() > 45) {
//            throw new InvalidArgumentException("error_027_03");
//        }
//
//        TechnologyTargetCounty technologyTargetTechnologyTargetCounty;
//        q = em.createNamedQuery("TechnologyTargetCounty.findByName");
//        q.setParameter("technologyTargetTechnologyTargetCounty", technologyTargetTechnologyTargetCountyDetails.getName());
//        try {
//            technologyTargetTechnologyTargetCounty = (TechnologyTargetCounty) q.getSingleResult();
//        } catch (Exception e) {MilesDebugger.debug(e);
//            technologyTargetTechnologyTargetCounty = null;
//        }
//        if (technologyTargetTechnologyTargetCounty != null) {
//            throw new InvalidArgumentException("error_027_04");
//        }
//
//        technologyTargetTechnologyTargetCounty = new TechnologyTargetCounty();
//        technologyTargetTechnologyTargetCounty.setName(technologyTargetTechnologyTargetCountyDetails.getName());
//
//        try {
//            em.persist(technologyTargetTechnologyTargetCounty);
//            em.flush();
//        } catch (Exception e) {MilesDebugger.debug(e);
//            throw new InvalidStateException("error_000_01");
//        }
//
//        return technologyTargetTechnologyTargetCounty.getId();
//
//    }
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="Read">
//
//    @Override
//    public List<TechnologyTargetCountyDetails> retrieveCounties() throws MilesException {
//        List<TechnologyTargetCounty> technologyTargetTechnologyTargetCountys = new ArrayList<>();
//        q = em.createNamedQuery("TechnologyTargetCounty.findAll");
//        try {
//            technologyTargetTechnologyTargetCountys = q.getResultList();
//        } catch (Exception e) {MilesDebugger.debug(e);
//        }
//
//        return convertCountiesToTechnologyTargetCountyDetailsList(technologyTargetTechnologyTargetCountys);
//    }
//
//    @Override
//    public TechnologyTargetCountyDetails retrieveTechnologyTargetCounty(int id) throws MilesException {
//        TechnologyTargetCounty technologyTargetTechnologyTargetCounty;
//        q = em.createNamedQuery("TechnologyTargetCounty.findById");
//        q.setParameter("id", id);
//        try {
//            technologyTargetTechnologyTargetCounty = (TechnologyTargetCounty) q.getSingleResult();
//        } catch (Exception e) {MilesDebugger.debug(e);
//            throw new InvalidStateException("error_000_01");
//        }
//
//        return convertTechnologyTargetCountyToTechnologyTargetCountyDetails(technologyTargetTechnologyTargetCounty);
//    }
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="Update">
//
//    @Override
//    public void editTechnologyTargetCounty(TechnologyTargetCountyDetails technologyTargetTechnologyTargetCountyDetails) throws MilesException {
//
//        if (technologyTargetTechnologyTargetCountyDetails == null) {
//            throw new InvalidArgumentException("error_027_01");
//        } else if (technologyTargetTechnologyTargetCountyDetails.getId() == null) {
//            throw new InvalidArgumentException("error_027_05");
//        } else if (technologyTargetTechnologyTargetCountyDetails.getName() == null) {
//            throw new InvalidArgumentException("error_027_02");
//        } else if (technologyTargetTechnologyTargetCountyDetails.getName().length() > 45) {
//            throw new InvalidArgumentException("error_027_03");
//        }
//
//        TechnologyTargetCounty technologyTargetTechnologyTargetCounty;
//        q = em.createNamedQuery("TechnologyTargetCounty.findByName");
//        q.setParameter("technologyTargetTechnologyTargetCounty", technologyTargetTechnologyTargetCountyDetails.getName());
//        try {
//            technologyTargetTechnologyTargetCounty = (TechnologyTargetCounty) q.getSingleResult();
//        } catch (Exception e) {MilesDebugger.debug(e);
//            technologyTargetTechnologyTargetCounty = null;
//        }
//        if (technologyTargetTechnologyTargetCounty != null) {
//            if (technologyTargetTechnologyTargetCounty.getId().equals(technologyTargetTechnologyTargetCountyDetails.getId())) {
//                throw new InvalidArgumentException("error_027_04");
//            }
//        }
//
//        technologyTargetTechnologyTargetCounty = em.find(TechnologyTargetCounty.class, technologyTargetTechnologyTargetCountyDetails.getId());
//        technologyTargetTechnologyTargetCounty.setId(technologyTargetTechnologyTargetCountyDetails.getId());
//        technologyTargetTechnologyTargetCounty.setName(technologyTargetTechnologyTargetCountyDetails.getName());
//
//        try {
//            em.merge(technologyTargetTechnologyTargetCounty);
//            em.flush();
//        } catch (Exception e) {MilesDebugger.debug(e);
//            throw new InvalidStateException("error_000_01");
//        }
//
//    }
//
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="Delete">
//    @Override
//    public void removeTechnologyTargetCounty(int id) throws MilesException {
//        TechnologyTargetCounty technologyTargetTechnologyTargetCounty = em.find(TechnologyTargetCounty.class, id);
//        try {
//            em.remove(technologyTargetTechnologyTargetCounty);
//        } catch (Exception e) {MilesDebugger.debug(e);
//            throw new InvalidStateException("error_000_01");
//        }
//    }
////</editor-fold>
////<editor-fold defaultstate="collapsed" desc="Convert">
//
//    @Override
//    public TechnologyTargetCountyDetails convertTechnologyTargetCountyToTechnologyTargetCountyDetails(TechnologyTargetCounty technologyTargetTechnologyTargetCounty) {
//
//        TechnologyTargetCountyDetails technologyTargetTechnologyTargetCountyDetails = new TechnologyTargetCountyDetails();
//        try {
//            technologyTargetTechnologyTargetCountyDetails.setId(technologyTargetTechnologyTargetCounty.getId());
//        } catch (Exception e) {MilesDebugger.debug(e);
//        }
//        try {
//            technologyTargetTechnologyTargetCountyDetails.setName(technologyTargetTechnologyTargetCounty.getName());
//        } catch (Exception e) {MilesDebugger.debug(e);
//        }
//        return technologyTargetTechnologyTargetCountyDetails;
//
//    }
//
//    private List<TechnologyTargetCountyDetails> convertCountiesToTechnologyTargetCountyDetailsList(List<TechnologyTargetCounty> technologyTargetTechnologyTargetCountys) {
//
//        List<TechnologyTargetCountyDetails> technologyTargetTechnologyTargetCountyDetailsList = new ArrayList<>();
//        for (TechnologyTargetCounty technologyTargetTechnologyTargetCounty : technologyTargetTechnologyTargetCountys) {
//            technologyTargetTechnologyTargetCountyDetailsList.add(convertTechnologyTargetCountyToTechnologyTargetCountyDetails(technologyTargetTechnologyTargetCounty));
//        }
//
//        return technologyTargetTechnologyTargetCountyDetailsList;
//
//    }
//
////</editor-fold>
//}
