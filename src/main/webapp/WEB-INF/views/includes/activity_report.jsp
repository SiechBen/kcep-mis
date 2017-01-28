<%--
    Document   : activity_report
    Created on : Oct 10, 2016, 4:37:01 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Status of programme implementation in the current financial year
            </div>
            <div class="panel-body">
                <h4>Activity progress report</h4>
                <table class="table table-striped table-bordered table-hover" id="activity-report-table">
                    <thead>
                        <tr>
                            <th colspan="2">
                                Financial year: &nbsp;
                                <select id="financial-year" onchange="changeFinancialYear()">
                                    <c:forEach var="financialYear" items="${sessionScope.financialYears}" varStatus="index">
                                        <option value="${financialYear.id}"
                                                <c:if test="${empty sessionScope.financialYear && financialYear.currentYear}">selected</c:if>
                                                <c:if test="${sessionScope.financialYear == financialYear.id}">selected</c:if>>
                                            ${financialYear.financialYear}
                                        </option>
                                    </c:forEach>
                                </select>
                            </th>
                            <th colspan="13">
                                Quarter: &nbsp;
                                <select id="quarter" onchange="changeQuarter()">
                                    <option value="0"selected>select</option>
                                    <option value="1" <c:if test="${sessionScope.quarter == 1}">selected</c:if>>1</option>
                                    <option value="2" <c:if test="${sessionScope.quarter == 2}">selected</c:if>>2</option>
                                    <option value="3" <c:if test="${sessionScope.quarter == 3}">selected</c:if>>3</option>
                                    <option value="4" <c:if test="${sessionScope.quarter == 4}">selected</c:if>>4</option>
                                    </select>
                                </th>
                            </tr>
                            <tr>
                                <th colspan="8">Physical progress</th>
                                <th colspan="5">Fincancial progress</th>
                                <th>Comments</th>
                            </tr>
                            <tr>
                                <th colspan="1">&nbsp;</th>
                                <th colspan="1">&nbsp;</th>
                                <th rowspan="2">Unit</th>
                                <th colspan="2">Quarter ${sessionScope.quarter}</th>
                            <th rowspan="2">Appraisal Target</th>
                            <th colspan="2">Cumulative</th>
                            <th rowspan="2">Expenditure category</th>
                            <th colspan="2">Quarter ${sessionScope.quarter}</th>
                            <!--<th rowspan="2">Appraisal</th>-->
                            <th colspan="2">Cumulative</th>
                            <th>&nbsp;</th>
                        </tr>
                        <tr>
                            <th colspan="1">AWPB ref code</th>
                            <th colspan="1">Planned activity</th>
                            <th colspan="1">Target</th>
                            <th colspan="1">Value achieved</th>
                            <th colspan="1">Target</th>
                            <th colspan="1">Value achieved</th>
                            <th colspan="1">Planned budget</th>
                            <th colspan="1">Actual expenditure</th>
                            <th colspan="1">Planned budget</th>
                            <th colspan="1">Actual expenditure</th>
                            <th colspan="1">&nbsp;</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th colspan="14">Report on second level results</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        <c:forEach var="activityProgressReport" items="${sessionScope.activityProgressReports}" varStatus="index">
                            <tr>
                                <td>${activityProgressReport.physicalProgressQ1.subActivity.annualWorkplanReferenceCode}</td>
                                <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${activityProgressReport.physicalProgressQ1.subActivity.subActivityName.name}">${activityProgressReport.physicalProgressQ1.subActivity.subActivityName.name}</td>
                                <td>${activityProgressReport.physicalProgressQ1.subActivity.measurementUnit.unit}</td>
                                <c:if test="${empty sessionScope.quarter}">
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                </c:if>
                                <c:if test="${sessionScope.quarter==1}">
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalProgressQ1.id}, 'Target', 'Quarter 1')">${activityProgressReport.physicalProgressQ1.targetOrBudget}</td>
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalProgressQ1.id}, 'Value achieved', 'Quarter 1')">${activityProgressReport.physicalProgressQ1.valueAchievedOrExpense}</td>
                                </c:if>
                                <c:if test="${sessionScope.quarter==2}">
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalProgressQ2.id}, 'Target', 'Quarter 2')">${activityProgressReport.physicalProgressQ2.targetOrBudget}</td>
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalProgressQ2.id}, 'Value achieved', 'Quarter 2')">${activityProgressReport.physicalProgressQ2.valueAchievedOrExpense}</td>
                                </c:if>
                                <c:if test="${sessionScope.quarter==3}">
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalProgressQ3.id}, 'Target', 'Quarter 3')">${activityProgressReport.physicalProgressQ3.targetOrBudget}</td>
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalProgressQ3.id}, 'Value achieved', 'Quarter 3')">${activityProgressReport.physicalProgressQ3.valueAchievedOrExpense}</td>
                                </c:if>
                                <c:if test="${sessionScope.quarter==4}">
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalProgressQ4.id}, 'Target', 'Quarter 4')">${activityProgressReport.physicalProgressQ4.targetOrBudget}</td>
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalProgressQ4.id}, 'Value achieved', 'Quarter 4')">${activityProgressReport.physicalProgressQ4.valueAchievedOrExpense}</td>
                                </c:if>
                                <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.physicalAppraisal.id}, 'Appraisal')">${activityProgressReport.physicalAppraisal.targetOrBudget}</td>
                                <td>${activityProgressReport.cumulativePhysicalProgress.targetOrBudget}</td>
                                <td>${activityProgressReport.cumulativePhysicalProgress.valueAchievedOrExpense}</td>
                                <td>${activityProgressReport.financialProgressQ1.subActivity.expenditureCategory.category.name}</td>
                                <c:if test="${empty sessionScope.quarter}">
                                    <td>&nbsp;</td>
                                    <td>&nbsp;</td>
                                </c:if>
                                <c:if test="${sessionScope.quarter==1}">
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialProgressQ1.id}, 'Budget', 'Quarter 1')">${activityProgressReport.financialProgressQ1.targetOrBudget}</td>
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialProgressQ1.id}, 'Expense', 'Quarter 1')">${activityProgressReport.financialProgressQ1.valueAchievedOrExpense}</td>
                                </c:if>
                                <c:if test="${sessionScope.quarter==2}">
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialProgressQ2.id}, 'Budget', 'Quarter 2')">${activityProgressReport.financialProgressQ2.targetOrBudget}</td>
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialProgressQ2.id}, 'Expense', 'Quarter 2')">${activityProgressReport.financialProgressQ2.valueAchievedOrExpense}</td>
                                </c:if>
                                <c:if test="${sessionScope.quarter==3}">
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialProgressQ3.id}, 'Budget', 'Quarter 3')">${activityProgressReport.financialProgressQ3.targetOrBudget}</td>
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialProgressQ3.id}, 'Expense', 'Quarter 3')">${activityProgressReport.financialProgressQ3.valueAchievedOrExpense}</td>
                                </c:if>
                                <c:if test="${sessionScope.quarter==4}">
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialProgressQ4.id}, 'Budget', 'Quarter 4')">${activityProgressReport.financialProgressQ4.targetOrBudget}</td>
                                    <td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialProgressQ4.id}, 'Expense', 'Quarter 4')">${activityProgressReport.financialProgressQ4.valueAchievedOrExpense}</td>
                                </c:if>
                                <!--<td class="editable pencil" onclick="editActivityProgress(this, ${activityProgressReport.financialAppraisal.id}, 'Appraisal')">${activityProgressReport.financialAppraisal.targetOrBudget}</td>-->
                                <td>${activityProgressReport.cumulativeFinancialProgress.targetOrBudget}</td>
                                <td>${activityProgressReport.cumulativeFinancialProgress.valueAchievedOrExpense}</td>
                                <td class="editable pencil" onclick="editActivityProgressComment(this, ${activityProgressReport.activityProgressComment.id}, '${activityProgressReport.activityProgressComment.comment}')">${activityProgressReport.activityProgressComment.comment}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="awpb-reference-code-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        <select id="awpb-reference-code" class="form-control">
                            <c:forEach var="code" items="${sessionScope.awpbReferenceCodes}" varStatus="index">
                                <option value="${code}">${code}</option>
                            </c:forEach>
                        </select>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="activity-progress-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        <input id="activity-progress-value" type="number" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="activity-progress-comment-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        <textarea id="activity-progress-comment" class="form-control"></textarea>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
