<%--
    Document   : activity_report
    Created on : Oct 10, 2016, 4:37:01 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view procurement plans for goods </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Status of programme implementation at county/sub-county level
                    </div>
                    <div class="panel-body">
                        <h4>Activity progress report</h4>
                        <!--                        <table>
                                                    <tr>
                                                        <th>Republic of Kenya</th>
                                                        <th>
                                                            Report date: &nbsp;
                                                            <input id="report-date" class="datefield">
                                                        </th>
                                                    </tr>
                                                    <tr>
                                                        <th>Kenya Cereal Enhancement Programme</th>
                                                        <th>
                                                            Project year: &nbsp;
                                                            <select id="project-year" onchange="changeOutcomeReport()">
                        <c:forEach var="projectYear" items="${sessionScope.projectYears}" varStatus="index">
                            <option value="${projectYear}">${projectYear}</option>
                        </c:forEach>
                    </select>
                </th>
            </tr>
        </table>-->
                        <table class="table table-striped table-bordered table-hover" id="activity-report-table">
                            <thead>
                                <tr>
                                    <th colspan="14">Physical progress</th>
                                    <th colspan="11">Fincancial progress</th>
                                    <th>Comments</th>
                                </tr>
                                <tr>
                                    <th colspan="1">&nbsp;</th>
                                    <th colspan="1">&nbsp;</th>
                                    <th colspan="1">&nbsp;</th>
                                    <th colspan="1">&nbsp;</th>
                                    <th colspan="2">Q1</th>
                                    <th colspan="2">Q2</th>
                                    <th colspan="2">Q3</th>
                                    <th colspan="2">Q4</th>
                                    <th colspan="2">Cummulative</th>
                                    <th colspan="1">Expenditure category</th>
                                    <th colspan="2">Q1</th>
                                    <th colspan="2">Q2</th>
                                    <th colspan="2">Q3</th>
                                    <th colspan="2">Q4</th>
                                    <th colspan="2">Cummulative</th>
                                    <th>&nbsp;</th>
                                </tr>
                                <tr>
                                    <th colspan="1">AWPB ref code</th>
                                    <th colspan="1">Component</th>
                                    <th colspan="1">Planned activity</th>
                                    <th colspan="1">Unit</th>
                                    <th colspan="1">Target</th>
                                    <th colspan="1">Value achieved</th>
                                    <th colspan="1">Target</th>
                                    <th colspan="1">Value achieved</th>
                                    <th colspan="1">Target</th>
                                    <th colspan="1">Value achieved</th>
                                    <th colspan="1">Target</th>
                                    <th colspan="1">Value achieved</th>
                                    <th colspan="1">Target</th>
                                    <th colspan="1">Value achieved</th>
                                    <th colspan="1">&nbsp;</th>
                                    <th colspan="1">Planned budget</th>
                                    <th colspan="1">Actual expenditure</th>
                                    <th colspan="1">Planned budget</th>
                                    <th colspan="1">Actual expenditure</th>
                                    <th colspan="1">Planned budget</th>
                                    <th colspan="1">Actual expenditure</th>
                                    <th colspan="1">Planned budget</th>
                                    <th colspan="1">Actual expenditure</th>
                                    <th colspan="1">Planned budget</th>
                                    <th colspan="1">Actual expenditure</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <th colspan="26">Report on second level results</th>
                                </tr>
                            </tfoot>
                            <tbody>
                                <tr>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ1.subActivity.annualWorkplanReferenceCode}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${sessionScope.activityProgressReport.physicalProgressQ1.subActivity.component.component}">${sessionScope.activityProgressReport.physicalProgressQ1.subActivity.component.component}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${sessionScope.activityProgressReport.physicalProgressQ1.subActivity.subActivityName.name}">${sessionScope.activityProgressReport.physicalProgressQ1.subActivity.subActivityName.name}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ1.subActivity.measurementUnit.unit}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ1.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ1.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ2.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ2.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ3.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ3.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ4.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.physicalProgressQ4.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.cummulativePhysicalProgress.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.cummulativePhysicalProgress.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ1.subActivity.expenditureCategory.category.name}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ1.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ1.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ2.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ2.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ3.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ3.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ4.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.financialProgressQ4.valueAchievedOrExpense}</td>
                                    <td>${sessionScope.activityProgressReport.cummulativeFinancialProgress.targetOrBudget}</td>
                                    <td>${sessionScope.activityProgressReport.cummulativeFinancialProgress.valueAchievedOrExpense}</td>
                                    <td>&nbsp;</td>
                                </tr>
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

    </jsp:attribute>
</kcep:head>>

