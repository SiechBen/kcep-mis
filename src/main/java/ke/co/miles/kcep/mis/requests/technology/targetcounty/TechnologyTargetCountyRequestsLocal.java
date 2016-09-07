///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package ke.co.miles.kcep.mis.requests.technology.targetcounty;
//
//import java.util.List;
//import javax.ejb.Local;
//import ke.co.miles.kcep.mis.entities.TechnologyTargetCounty;
//import ke.co.miles.kcep.mis.exceptions.MilesException;
//import ke.co.miles.kcep.mis.utilities.TechnologyTargetCountyDetails;
//
///**
// *
// * @author siech
// */
//@Local
//public interface TechnologyTargetCountyRequestsLocal {
//
//    /**
//     *
//     * @param technologyTargetCountyDetails details of the technology target
//     * county record to be created
//     * @return the unique identifier of the new record created
//     * @throws MilesException when the database is in an incorrect state or when
//     * the details are null or incorrectly specified
//     */
//    public int addTechnologyTargetCounty(TechnologyTargetCountyDetails technologyTargetCountyDetails) throws MilesException;
//
//    /**
//     *
//     * @return the list of technology target county record details retrieved
//     * @throws MilesException when the database is in an incorrect state
//     */
//    public List<TechnologyTargetCountyDetails> retrieveCounties() throws MilesException;
//
//    /**
//     *
//     * @param id the unique identifier of the technology target county record to
//     * be retrieved
//     * @return the details of the technology target county record retrieved
//     * @throws MilesException when the database is in an incorrect state
//     */
//    public TechnologyTargetCountyDetails retrieveTechnologyTargetCounty(int id) throws MilesException;
//
//    /**
//     *
//     * @param technologyTargetCountyDetails details of the technology target
//     * county record to be edited
//     * @throws MilesException when the database is in an incorrect state or when
//     * the details are null or incorrectly specified
//     */
//    public void editTechnologyTargetCounty(TechnologyTargetCountyDetails technologyTargetCountyDetails) throws MilesException;
//
//    /**
//     *
//     * @param id the unique identifier of the technology target county record to
//     * be removed
//     * @throws MilesException when the database is in an incorrect state
//     */
//    public void removeTechnologyTargetCounty(int id) throws MilesException;
//
//    /**
//     *
//     * @param technologyTargetCounty the technology target county to be
//     * converted
//     * @return the details of the converted technologyTargetCounty
//     */
//    public TechnologyTargetCountyDetails convertTechnologyTargetCountyToTechnologyTargetCountyDetails(TechnologyTargetCounty technologyTargetCounty);
//
//}
