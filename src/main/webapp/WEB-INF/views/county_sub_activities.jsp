<%-- 
    Document   : county_sub_activities
    Created on : Jul 15, 2016, 8:05:02 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view sub-activities </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of sub-activites
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addSubActivity')">Add</button></th>
                                        <th>Annual workplan reference code</th>
                                        <th>Component</th>
                                        <th>Sub-component</th>
                                        <th>Performance indicator</th>
                                        <th>Activity name</th>
                                        <th>Sub-activity name</th>
                                        <th>Start date</th>
                                        <th>End date</th>
                                        <th>Measurement unit</th>
                                        <th>Unit cost</th>
                                        <th>Awpb target</th>
                                        <th>Programme target</th>
                                        <th>Totals</th>
                                        <th>Response pcu</th>
                                        <th>Implementing partner</th>
                                        <th>Procurement plan</th>
                                        <th>Description</th>
                                        <th>Value achieved</th>
                                        <th>Allocated budget</th>
                                        <th>Expenditure category</th>
                                        <th>GOK percentage</th>
                                        <th>IFAD loan percentage </th>
                                        <th>IFAD grant percentage</th>
                                        <th>Beneficiaries percentage</th>
                                        <th>EU percentage</th>
                                        <th>Financial institution percentage</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of sub-activities </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="subActivity" items="${sessionScope.subActivities}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                            <td>${index.count}</td>
                                            <td>${subActivity.annualWorkplanReferenceCode}</td>
                                            <td>${subActivity.component.component}</td>
                                            <td>${subActivity.subComponent.subComponent}</td>
                                            <td>${subActivity.performanceIndicator.description}</td>
                                            <td>${subActivity.activityName.name}</td>
                                            <td>${subActivity.subActivityName.name}</td>
                                            <td>${subActivity.startDate}</td>
                                            <td>${subActivity.endDate}</td>
                                            <td>${subActivity.measurementUnit.unit}(${subActivity.measurementUnit.symbol}</td>
                                            <td>${subActivity.unitCost}</td>
                                            <td>${subActivity.awpbTarget}</td>
                                            <td>${subActivity.programmeTarget}</td>
                                            <td>${subActivity.totals}</td>
                                            <td>${subActivity.responsePcu.name}</td>
                                            <td>${subActivity.implementingPartner.personRole.personRole}</td>
                                            <td>${subActivity.procurementPlan}</td>
                                            <td>${subActivity.description}</td>
                                            <td>${subActivity.valueAchieved}</td>
                                            <td>${subActivity.allocatedBudget}</td>
                                            <td>${subActivity.expenditureCategory.name}</td>
                                            <td>${subActivity.gokPercentage}</td>
                                            <td>${subActivity.ifadLoanPercentage}</td>
                                            <td>${subActivity.ifadGrantPercentage}</td>
                                            <td>${subActivity.beneficiariesPercentage}</td>
                                            <td>${subActivity.euPercentage}</td>
                                            <td>${subActivity.financialInstitutionPercentage}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:county>