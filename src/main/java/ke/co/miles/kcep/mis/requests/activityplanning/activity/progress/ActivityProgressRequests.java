/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ke.co.miles.kcep.mis.requests.activityplanning.activity.progress;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ke.co.miles.debugger.MilesDebugger;
import ke.co.miles.kcep.mis.defaults.EntityRequests;
import ke.co.miles.kcep.mis.entities.ActivityProgress;
import ke.co.miles.kcep.mis.entities.ActivityProgressComment;
import ke.co.miles.kcep.mis.entities.Phenomenon;
import ke.co.miles.kcep.mis.entities.SubActivity;
import ke.co.miles.kcep.mis.exceptions.InvalidArgumentException;
import ke.co.miles.kcep.mis.exceptions.InvalidStateException;
import ke.co.miles.kcep.mis.exceptions.MilesException;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.progress.comment.ActivityProgressCommentRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.activity.sub.SubActivityRequestsLocal;
import ke.co.miles.kcep.mis.requests.activityplanning.financialyear.FinancialYearRequestsLocal;
import ke.co.miles.kcep.mis.utilities.ActivityProgressDetails;
import ke.co.miles.kcep.mis.utilities.ActivityProgressReportDetails;
import ke.co.miles.kcep.mis.utilities.ProgressTypeDetail;

/**
 *
 * @author siech
 */
@Stateless
public class ActivityProgressRequests extends EntityRequests implements ActivityProgressRequestsLocal {

//<editor-fold defaultstate="collapsed" desc="Create">
    @Override
    public int addActivityProgress(ActivityProgressDetails activityProgressDetails) throws MilesException {

        if (activityProgressDetails == null) {
            throw new InvalidArgumentException("error_056_01");
        } else if (activityProgressDetails.getSubActivity() == null) {
            throw new InvalidArgumentException("error_056_02");
        } else if (activityProgressDetails.getProgressType() == null) {
            throw new InvalidArgumentException("error_056_03");
        }

        ActivityProgress activityProgress = new ActivityProgress();
        activityProgress.setProgressType(em.getReference(Phenomenon.class, activityProgressDetails.getProgressType().getId()));
        activityProgress.setSubActivity(em.getReference(SubActivity.class, activityProgressDetails.getSubActivity().getId()));

        try {
            em.persist(activityProgress);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return activityProgress.getId();

    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Read">

    @SuppressWarnings("unchecked")
    private List<SubActivity> retrieveSubActivities(short financialYearId) throws MilesException {
        List<SubActivity> subActivities = new ArrayList<>();
        setQ(em.createNamedQuery("SubActivity.findByFinancialYearId"));
        q.setParameter("financialYearId", financialYearId);
        try {
            subActivities = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return subActivities;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public void checkForActivityProgress(Short financialYearId) throws MilesException {
        if (financialYearId == null) {
            financialYearId = financialYearService.retrieveCurrentFinancialYear().getId();
        }
        List<ActivityProgress> activityProgressList;
        setQ(em.createNamedQuery("ActivityProgress.findByFinancialYearId"));
        q.setParameter("financialYearId", financialYearId);
        try {
            activityProgressList = q.getResultList();
            if (activityProgressList == null || activityProgressList.isEmpty()) {
                List<SubActivity> subActivities = retrieveSubActivities(financialYearId);
                ActivityProgressComment activityProgressComment;
                ActivityProgress activityProgress;
                int one = 0;
                for (SubActivity subActivity : subActivities) {
                    for (Integer i = 1; i <= 4; i++) {
                        activityProgress = new ActivityProgress();
                        activityProgress.setProgressType(em.getReference(Phenomenon.class, ProgressTypeDetail.PHYSICAL.getId()));
                        activityProgress.setSubActivity(subActivity);
                        activityProgress.setQuarter(new Short(i.toString()));
                        try {
                            em.persist(activityProgress);
                        } catch (Exception e) {
                            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                            throw new InvalidStateException("error_000_01");
                        }
                        activityProgress = new ActivityProgress();
                        activityProgress.setProgressType(em.getReference(Phenomenon.class, ProgressTypeDetail.FINANCIAL.getId()));
                        activityProgress.setSubActivity(subActivity);
                        activityProgress.setQuarter(new Short(i.toString()));
                        try {
                            em.persist(activityProgress);
                        } catch (Exception e) {
                            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                            throw new InvalidStateException("error_000_01");
                        }
                    }
                    activityProgress = new ActivityProgress();
                    activityProgress.setProgressType(em.getReference(Phenomenon.class, ProgressTypeDetail.PHYSICAL_APPRAISAL.getId()));
                    activityProgress.setSubActivity(subActivity);
                    activityProgress.setQuarter(null);
                    try {
                        em.persist(activityProgress);
                    } catch (Exception e) {
                        MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                        throw new InvalidStateException("error_000_01");
                    }
                    activityProgress = new ActivityProgress();
                    activityProgress.setProgressType(em.getReference(Phenomenon.class, ProgressTypeDetail.FINANCIAL_APPRAISAL.getId()));
                    activityProgress.setSubActivity(subActivity);
                    activityProgress.setQuarter(null);
                    try {
                        em.persist(activityProgress);
                    } catch (Exception e) {
                        MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                        throw new InvalidStateException("error_000_01");
                    }
                    /* persist activity progress comment */
                    activityProgressComment = new ActivityProgressComment();
                    activityProgressComment.setSubActivity(subActivity);
                    try {
                        em.persist(activityProgressComment);
                    } catch (Exception e) {
                        MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                        throw new InvalidStateException("error_000_01");
                    }
                }

                em.flush();

            }
        } catch (NumberFormatException e) {
        } catch (MilesException ex) {
            throw ex;
        }

    }

    @Override
    public ActivityProgressReportDetails retrieveActivityProgress(String awpbReferenceCode, String level, Short levelId, Short financialYearId) throws MilesException {
        ActivityProgressReportDetails activityProgressReport = new ActivityProgressReportDetails();
        ActivityProgress activityProgress;
        for (short i = 1; i <= 4; i++) {
            switch (level) {
                case "Region":
                    setQ(em.createNamedQuery("ActivityProgress.findForRegionByFinancialYearIdAndReferenceCode"));
                    q.setParameter("quarter", i);
                    q.setParameter("regionId", levelId);
                    q.setParameter("awpbReferenceCode", awpbReferenceCode);
                    q.setParameter("progressTypeId", ProgressTypeDetail.PHYSICAL.getId());
                    q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                    break;
                case "County":
                    setQ(em.createNamedQuery("ActivityProgress.findForCountyByFinancialYearIdAndReferenceCode"));
                    q.setParameter("quarter", i);
                    q.setParameter("countyId", levelId);
                    q.setParameter("awpbReferenceCode", awpbReferenceCode);
                    q.setParameter("progressTypeId", ProgressTypeDetail.PHYSICAL.getId());
                    q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                    break;
                case "Head":
                    setQ(em.createNamedQuery("ActivityProgress.findForHeadOrPartnerByFinancialYearIdAndReferenceCode"));
                    q.setParameter("quarter", i);
                    q.setParameter("awpbOwnerId", 178);
                    q.setParameter("awpbReferenceCode", awpbReferenceCode);
                    q.setParameter("progressTypeId", ProgressTypeDetail.PHYSICAL.getId());
                    q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                    break;
                default:
                    return activityProgressReport;
            }
            try {
                activityProgress = (ActivityProgress) q.getSingleResult();
                switch (i) {
                    case 1:
                        activityProgressReport.setPhysicalProgressQ1(convertActivityProgressToActivityProgressDetails(activityProgress));
                        break;
                    case 2:
                        activityProgressReport.setPhysicalProgressQ2(convertActivityProgressToActivityProgressDetails(activityProgress));
                        break;
                    case 3:
                        activityProgressReport.setPhysicalProgressQ3(convertActivityProgressToActivityProgressDetails(activityProgress));
                        break;
                    case 4:
                        activityProgressReport.setPhysicalProgressQ4(convertActivityProgressToActivityProgressDetails(activityProgress));
                        break;
                }
            } catch (Exception e) {
                MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                throw new InvalidStateException("error_000_01");
            }

            switch (level) {
                case "Region":
                    setQ(em.createNamedQuery("ActivityProgress.findForRegionByFinancialYearIdAndReferenceCode"));
                    q.setParameter("quarter", i);
                    q.setParameter("regionId", levelId);
                    q.setParameter("awpbReferenceCode", awpbReferenceCode);
                    q.setParameter("progressTypeId", ProgressTypeDetail.FINANCIAL.getId());
                    q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                    break;
                case "County":
                    setQ(em.createNamedQuery("ActivityProgress.findForCountyByFinancialYearIdAndReferenceCode"));
                    q.setParameter("quarter", i);
                    q.setParameter("countyId", levelId);
                    q.setParameter("awpbReferenceCode", awpbReferenceCode);
                    q.setParameter("progressTypeId", ProgressTypeDetail.FINANCIAL.getId());
                    q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                    break;
                case "Head":
                    setQ(em.createNamedQuery("ActivityProgress.findForHeadOrPartnerByFinancialYearIdAndReferenceCode"));
                    q.setParameter("quarter", i);
                    q.setParameter("awpbOwnerId", 178);
                    q.setParameter("awpbReferenceCode", awpbReferenceCode);
                    q.setParameter("progressTypeId", ProgressTypeDetail.FINANCIAL.getId());
                    q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                    break;
                default:
                    return activityProgressReport;
            }
            try {
                activityProgress = (ActivityProgress) q.getSingleResult();
                switch (i) {
                    case 1:
                        activityProgressReport.setFinancialProgressQ1(convertActivityProgressToActivityProgressDetails(activityProgress));
                        break;
                    case 2:
                        activityProgressReport.setFinancialProgressQ2(convertActivityProgressToActivityProgressDetails(activityProgress));
                        break;
                    case 3:
                        activityProgressReport.setFinancialProgressQ3(convertActivityProgressToActivityProgressDetails(activityProgress));
                        break;
                    case 4:
                        activityProgressReport.setFinancialProgressQ4(convertActivityProgressToActivityProgressDetails(activityProgress));
                        break;
                }
            } catch (Exception e) {
                MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
                throw new InvalidStateException("error_000_01");
            }
        }

        ActivityProgressDetails activityProgressDetails;

        switch (level) {
            case "Region":
                setQ(em.createNamedQuery("ActivityProgress.findAppraisalForRegionByFinancialYearIdAndReferenceCode"));
                q.setParameter("regionId", levelId);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("progressTypeId", ProgressTypeDetail.PHYSICAL_APPRAISAL.getId());
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            case "County":
                setQ(em.createNamedQuery("ActivityProgress.findAppraisalForCountyByFinancialYearIdAndReferenceCode"));
                q.setParameter("countyId", levelId);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("progressTypeId", ProgressTypeDetail.PHYSICAL_APPRAISAL.getId());
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            case "Head":
                setQ(em.createNamedQuery("ActivityProgress.findAppraisalForHeadOrPartnerByFinancialYearIdAndReferenceCode"));
                q.setParameter("awpbOwnerId", 178);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("progressTypeId", ProgressTypeDetail.PHYSICAL_APPRAISAL.getId());
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            default:
                return activityProgressReport;
        }

        activityProgressDetails = convertActivityProgressToActivityProgressDetails((ActivityProgress) q.getSingleResult());
        activityProgressReport.setPhysicalAppraisal(activityProgressDetails);

        switch (level) {
            case "Region":
                setQ(em.createNamedQuery("ActivityProgress.findAppraisalForRegionByFinancialYearIdAndReferenceCode"));
                setQ(em.createNamedQuery("ActivityProgress.findForRegionByFinancialYearIdAndReferenceCode"));
                q.setParameter("regionId", levelId);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("progressTypeId", ProgressTypeDetail.FINANCIAL_APPRAISAL.getId());
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            case "County":
                setQ(em.createNamedQuery("ActivityProgress.findAppraisalForCountyByFinancialYearIdAndReferenceCode"));
                q.setParameter("countyId", levelId);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("progressTypeId", ProgressTypeDetail.FINANCIAL_APPRAISAL.getId());
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            case "Head":
                setQ(em.createNamedQuery("ActivityProgress.findAppraisalForHeadOrPartnerByFinancialYearIdAndReferenceCode"));
                q.setParameter("awpbOwnerId", 178);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("progressTypeId", ProgressTypeDetail.FINANCIAL_APPRAISAL.getId());
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            default:
                return activityProgressReport;
        }

        activityProgressDetails = convertActivityProgressToActivityProgressDetails((ActivityProgress) q.getSingleResult());
        activityProgressReport.setFinancialAppraisal(activityProgressDetails);

        switch (level) {
            case "Region":
                setQ(em.createNamedQuery("ActivityProgressComment.findForRegionByFinancialYearIdAndReferenceCode"));
                setQ(em.createNamedQuery("ActivityProgress.findForRegionByFinancialYearIdAndReferenceCode"));
                q.setParameter("regionId", levelId);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            case "County":
                setQ(em.createNamedQuery("ActivityProgressComment.findForCountyByFinancialYearIdAndReferenceCode"));
                q.setParameter("countyId", levelId);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            case "Head":
                setQ(em.createNamedQuery("ActivityProgressComment.findForHeadOrPartnerByFinancialYearIdAndReferenceCode"));
                q.setParameter("awpbOwnerId", 178);
                q.setParameter("awpbReferenceCode", awpbReferenceCode);
                q.setParameter("financialYearId", financialYearId == null ? financialYearService.retrieveCurrentFinancialYear().getId() : financialYearId);
                break;
            default:
                return activityProgressReport;
        }

        /* retrieve activity progress comment */
        activityProgressReport.setActivityProgressComment(activityProgressCommentService.convertActivityProgressCommentToActivityProgressCommentDetails((ActivityProgressComment) q.getSingleResult()));

        activityProgressDetails = new ActivityProgressDetails();
        try {
            activityProgressDetails.setTargetOrBudget(
                    (activityProgressReport.getPhysicalProgressQ1().getTargetOrBudget() == null ? new BigDecimal("0") : activityProgressReport.getPhysicalProgressQ1().getTargetOrBudget())
                    .add((activityProgressReport.getPhysicalProgressQ2().getTargetOrBudget() == null ? new BigDecimal("0") : activityProgressReport.getPhysicalProgressQ2().getTargetOrBudget()))
                    .add((activityProgressReport.getPhysicalProgressQ3().getTargetOrBudget() == null ? new BigDecimal("0") : activityProgressReport.getPhysicalProgressQ3().getTargetOrBudget()))
                    .add((activityProgressReport.getPhysicalProgressQ4().getTargetOrBudget() == null ? new BigDecimal("0") : activityProgressReport.getPhysicalProgressQ4().getTargetOrBudget()))
            );
            if (activityProgressDetails.getTargetOrBudget().equals(new BigDecimal("0"))) {
                activityProgressDetails.setTargetOrBudget(null);
            }
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        try {
            activityProgressDetails.setValueAchievedOrExpense(
                    (activityProgressReport.getPhysicalProgressQ1().getValueAchievedOrExpense() == null ? new BigDecimal("0") : activityProgressReport.getPhysicalProgressQ1().getValueAchievedOrExpense())
                    .add((activityProgressReport.getPhysicalProgressQ2().getValueAchievedOrExpense() == null ? new BigDecimal("0") : activityProgressReport.getPhysicalProgressQ2().getValueAchievedOrExpense()))
                    .add((activityProgressReport.getPhysicalProgressQ3().getValueAchievedOrExpense() == null ? new BigDecimal("0") : activityProgressReport.getPhysicalProgressQ3().getValueAchievedOrExpense()))
                    .add((activityProgressReport.getPhysicalProgressQ4().getValueAchievedOrExpense() == null ? new BigDecimal("0") : activityProgressReport.getPhysicalProgressQ4().getValueAchievedOrExpense()))
            );
            if (activityProgressDetails.getValueAchievedOrExpense().equals(new BigDecimal("0"))) {
                activityProgressDetails.setValueAchievedOrExpense(null);
            }
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        activityProgressReport.setCumulativePhysicalProgress(activityProgressDetails);

        activityProgressDetails = new ActivityProgressDetails();
        try {
            activityProgressDetails.setTargetOrBudget(
                    (activityProgressReport.getFinancialProgressQ1().getTargetOrBudget() == null ? new BigDecimal("0") : activityProgressReport.getFinancialProgressQ1().getTargetOrBudget())
                    .add((activityProgressReport.getFinancialProgressQ2().getTargetOrBudget() == null ? new BigDecimal("0") : activityProgressReport.getFinancialProgressQ2().getTargetOrBudget()))
                    .add((activityProgressReport.getFinancialProgressQ3().getTargetOrBudget() == null ? new BigDecimal("0") : activityProgressReport.getFinancialProgressQ3().getTargetOrBudget()))
                    .add((activityProgressReport.getFinancialProgressQ4().getTargetOrBudget() == null ? new BigDecimal("0") : activityProgressReport.getFinancialProgressQ4().getTargetOrBudget()))
            );
            if (activityProgressDetails.getTargetOrBudget().equals(new BigDecimal("0"))) {
                activityProgressDetails.setTargetOrBudget(null);
            }
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        try {
            activityProgressDetails.setValueAchievedOrExpense(
                    (activityProgressReport.getFinancialProgressQ1().getValueAchievedOrExpense() == null ? new BigDecimal("0") : activityProgressReport.getFinancialProgressQ1().getValueAchievedOrExpense())
                    .add((activityProgressReport.getFinancialProgressQ2().getValueAchievedOrExpense() == null ? new BigDecimal("0") : activityProgressReport.getFinancialProgressQ2().getValueAchievedOrExpense()))
                    .add((activityProgressReport.getFinancialProgressQ3().getValueAchievedOrExpense() == null ? new BigDecimal("0") : activityProgressReport.getFinancialProgressQ3().getValueAchievedOrExpense()))
                    .add((activityProgressReport.getFinancialProgressQ4().getValueAchievedOrExpense() == null ? new BigDecimal("0") : activityProgressReport.getFinancialProgressQ4().getValueAchievedOrExpense()))
            );
            if (activityProgressDetails.getValueAchievedOrExpense().equals(new BigDecimal("0"))) {
                activityProgressDetails.setValueAchievedOrExpense(null);
            }
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }

        activityProgressReport.setCumulativeFinancialProgress(activityProgressDetails);

        /* Set the report id as the id of financial progress for quarter 4 */
        activityProgressReport.setId(activityProgressReport.getFinancialProgressQ4().getId());

        return activityProgressReport;
    }

    @Override
    public ActivityProgressDetails retrieveActivityProgress(int id) throws MilesException {
        ActivityProgress activityProgress;
        setQ(em.createNamedQuery("ActivityProgress.findById"));
        q.setParameter("id", id);
        try {
            activityProgress = (ActivityProgress) q.getSingleResult();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertActivityProgressToActivityProgressDetails(activityProgress);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ActivityProgressDetails> retrieveActivityProgressList(int subActivityId) throws MilesException {
        List<ActivityProgress> activityProgressList;
        setQ(em.createNamedQuery("ActivityProgress.findBySubActivityId"));
        q.setParameter("subActivityId", subActivityId);
        try {
            activityProgressList = q.getResultList();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

        return convertActivityProgressListToActivityProgressDetailsList(activityProgressList);
    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Update">
    @Override
    public void editActivityProgress(ActivityProgressDetails activityProgressDetails) throws MilesException {

        if (activityProgressDetails == null) {
            throw new InvalidArgumentException("error_056_01");
        } else if (activityProgressDetails.getId() == null) {
            throw new InvalidArgumentException("error_056_04");
        }

        ActivityProgress activityProgress = em.getReference(ActivityProgress.class, activityProgressDetails.getId());
        if (activityProgressDetails.getTargetOrBudget() != null) {
            activityProgress.setTargetOrBudget(activityProgressDetails.getTargetOrBudget());
        }
        if (activityProgressDetails.getValueAchievedOrExpense() != null) {
            activityProgress.setValueAchievedOrExpense(activityProgressDetails.getValueAchievedOrExpense());
        }

        try {
            em.merge(activityProgress);
            em.flush();
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }

    }

//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Delete">
    @Override
    public void removeActivityProgress(int id) throws MilesException {
        ActivityProgress activityProgress = em.find(ActivityProgress.class, id);
        try {
            em.remove(activityProgress);
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
            throw new InvalidStateException("error_000_01");
        }
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Convert">

    @Override
    public ActivityProgressDetails convertActivityProgressToActivityProgressDetails(ActivityProgress activityProgress
    ) {

        ActivityProgressDetails activityProgressDetails = new ActivityProgressDetails();
        try {
            activityProgressDetails.setId(activityProgress.getId());
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }
        activityProgressDetails.setTargetOrBudget(activityProgress.getTargetOrBudget());
        activityProgressDetails.setValueAchievedOrExpense(activityProgress.getValueAchievedOrExpense());
        try {
            activityProgressDetails.setSubActivity(subActivityService.convertSubActivityToSubActivityDetails(activityProgress.getSubActivity()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }
        try {
            activityProgressDetails.setProgressType(ProgressTypeDetail.getProgressTypeDetail(activityProgress.getSubActivity().getId()));
        } catch (Exception e) {
            MilesDebugger.debug(this.getClass().getSimpleName() + ": " + e);
        }
        return activityProgressDetails;

    }

    private List<ActivityProgressDetails> convertActivityProgressListToActivityProgressDetailsList(List<ActivityProgress> activityProgressList) {

        List<ActivityProgressDetails> activityProgressDetailsList = new ArrayList<>();
        for (ActivityProgress activityProgress : activityProgressList) {
            activityProgressDetailsList.add(convertActivityProgressToActivityProgressDetails(activityProgress));
        }

        return activityProgressDetailsList;

    }

//</editor-fold>
    @EJB
    private SubActivityRequestsLocal subActivityService;
    @EJB
    private FinancialYearRequestsLocal financialYearService;
    @EJB
    private ActivityProgressCommentRequestsLocal activityProgressCommentService;

}
