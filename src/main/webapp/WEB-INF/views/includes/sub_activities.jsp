<%--
    Document   : sub_activities
    Created on : Sep 7, 2016, 2:00:16 PM
    Author     : ronne
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Annual Workplan Budget
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table id="awpb-table" class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Annual workplan reference code</th>
                                <th>GFS code</th>
                                <th>Expected outcome</th>
                                <th>Component</th>
                                <th>Sub-component</th>
                                <th>Annual indicator</th>
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
                                <!--<th>Expenditure</th>-->
                                <th>Allocated budget</th>
                                <th>Expenditure category</th>
                                <th>GOK percentage</th>
                                <th>IFAD loan percentage </th>
                                <th>IFAD grant percentage</th>
                                <th>Beneficiaries percentage</th>
                                <th>EU percentage</th>
                                <th>Financial institution percentage</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="30"> Annual Workplan Budget </td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="subActivity" items="${sessionScope.subActivities}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${subActivity.annualWorkplanReferenceCode}</td>
                                    <td>${subActivity.gfssCode.category.name} <c:if test="not empty ${subActivity.gfssCode.category.name}">-</c:if> ${subActivity.gfssCode.category.relative.name}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${subActivity.expectedOutcome.category.name}">${subActivity.expectedOutcome.category.name}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${subActivity.component.category.name}">${subActivity.component.category.name}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${subActivity.subComponent.category.name}">${subActivity.subComponent.category.name}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${subActivity.annualIndicator.category.name}">${subActivity.annualIndicator.category.name}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${subActivity.subActivityName.activityName.name}">${subActivity.subActivityName.activityName.name}</td>
                                    <td class="tooltipped" data-toggle="tooltip" data-placement="auto bottom" title="${subActivity.subActivityName.name}">${subActivity.subActivityName.name}</td>
                                    <td><fmt:formatDate pattern="yy-MMM-dd" value="${subActivity.startDate}"/></td>
                                    <td><fmt:formatDate pattern="yy-MMM-dd" value="${subActivity.endDate}"/></td>
                                    <td>${subActivity.measurementUnit.unit}</td>
                                    <td>${subActivity.unitCost}</td>
                                    <td>${subActivity.awpbTarget}</td>
                                    <td>${subActivity.programmeTarget}</td>
                                    <td>${subActivity.totals}</td>
                                    <td>${subActivity.responsePcu.category.name}</td>
                                    <td>${subActivity.implementingPartner.category.name}</td>
                                    <td>${subActivity.procurementPlan}</td>
                                    <td>${subActivity.description}</td>
                                    <!--<td>${subActivity.valueAchieved}</td>-->
                                    <td>${subActivity.allocatedBudget}</td>
                                    <td>${subActivity.expenditureCategory.category.name}</td>
                                    <td>${subActivity.gokPercentage}</td>
                                    <td>${subActivity.ifadLoanPercentage}</td>
                                    <td>${subActivity.ifadGrantPercentage}</td>
                                    <td>${subActivity.beneficiariesPercentage}</td>
                                    <td>${subActivity.euPercentage}</td>
                                    <td>${subActivity.financialInstitutionPercentage}</td>
                                    <td><button onclick="editSubActivity('${subActivity.id}',
                                                    '${subActivity.financialYear.id}',
                                                    '${subActivity.annualWorkplanReferenceCode}',
                                                    '${subActivity.gfssCode.id}',
                                                    '${subActivity.expectedOutcome.id}',
                                                    '${subActivity.component.id}',
                                                    '${subActivity.subComponent.id}',
                                                    '${subActivity.subActivityName.activityName.id}',
                                                    '${subActivity.subActivityName.id}',
                                                    '<fmt:formatDate pattern="yy-MMM-dd" value="${subActivity.startDate}"/>',
                                                    '<fmt:formatDate pattern="yy-MMM-dd" value="${subActivity.endDate}"/>',
                                                    '${subActivity.measurementUnit.id}',
                                                    '${subActivity.unitCost}',
                                                    '${subActivity.awpbTarget}',
                                                    '${subActivity.programmeTarget}',
                                                    '${subActivity.totals}',
                                                    '${subActivity.responsePcu.id}',
                                                    '${subActivity.implementingPartner.id}',
                                                    '${subActivity.procurementPlan}',
                                                    '${subActivity.description}',
                                                    '${subActivity.valueAchieved}',
                                                    '${subActivity.allocatedBudget}',
                                                    '${subActivity.expenditureCategory.id}',
                                                    '${subActivity.gokPercentage}',
                                                    '${subActivity.ifadLoanPercentage}',
                                                    '${subActivity.ifadGrantPercentage}',
                                                    '${subActivity.beneficiariesPercentage}',
                                                    '${subActivity.euPercentage}',
                                                    '${subActivity.financialInstitutionPercentage}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deleteSubActivity(${subActivity.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="sub-activity-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Financial year
                        <select id="financial-year" class="form-control">
                            <option value="">Select financial year</option>
                            <c:forEach var="financialYear" items="${sessionScope.financialYears}" varStatus="counter">
                                <option value="${financialYear.id}">${financialYear.financialYear}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Annual workplan reference code
                        <input id="annual-workplan-reference-code" class="form-control">
                    </div>
                    <div class="form-group">
                        GFS code
                        <select id="gfss-code" name="gfss-code" class="form-control">
                            <c:forEach var="gfssCode" items="${sessionScope.gfssCodes}" varStatus="index">
                                <option value="${gfssCode.id}">${gfssCode.category.name} - ${gfssCode.category.relative.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Expected outcome
                        <select id="expected-outcome" class="form-control">
                            <option value="">Select expected outcome</option>
                            <c:forEach var="expectedOutcome" items="${sessionScope.expectedOutcomes}" varStatus="counter">
                                <option value="${expectedOutcome.id}">${expectedOutcome.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Component
                        <select id="component" class="form-control">
                            <option value="">Select component</option>
                            <c:forEach var="component" items="${sessionScope.components}" varStatus="counter">
                                <option value="${component.id}">${component.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-component
                        <select id="sub-component" class="form-control">
                            <option value="">Select sub-component</option>
                            <c:forEach var="subComponent" items="${sessionScope.subComponents}" varStatus="counter">
                                <option value="${subComponent.id}">${subComponent.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Activity name
                        <select id="activity-name" class="form-control" onchange="updateSubActivityNames()">
                            <option disabled selected>Select activity name</option>
                            <c:forEach var="activityName" items="${sessionScope.activityNames}" varStatus="counter">
                                <option value="${activityName.id}">${activityName.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub activity name
                        <select id="sub-activity-name" class="form-control">
                            <option value="">Select sub-activity name</option>
                            <c:forEach var="subActivityName" items="${sessionScope.subActivityNames}" varStatus="counter">
                                <option value="${subActivityName.id}">${subActivityName.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Start Date
                        <input id="start-date" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        End date
                        <input id="end-date" class="form-control datefield" type="date">
                    </div>
                    <div class="form-group">
                        Measurement unit
                        <select id="measurement-unit" class="form-control">
                            <option value="">Select measurement unit</option>
                            <c:forEach var="measurementUnit" items="${applicationScope.measurementUnits}" varStatus="counter">
                                <option value="${measurementUnit.id}">${measurementUnit.unit}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Unit cost
                        <input id="unit-cost" class="form-control" type="number" step="0.01">
                    </div>
                    <div class="form-group">
                        Awpb target
                        <input id="awpb-target" class="form-control" type="number" step="0.01">
                    </div>
                    <div class="form-group">
                        Programme target
                        <input id="programme-target" class="form-control" type="number" step="0.01">
                    </div>
                    <div class="form-group">
                        Totals
                        <input id="totals" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        Response PCU
                        <select id="response-pcu" class="form-control">
                            <option value="">Select response pcu</option>
                            <c:forEach var="responsePcu" items="${sessionScope.responsePCUList}" varStatus="counter">
                                <option value="${responsePcu.id}">${responsePcu.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Implementing partner
                        <select id="implementing-partner" class="form-control">
                            <option value="">Select implementing partner</option>
                            <c:forEach var="implementingPartner" items="${sessionScope.implementingPartners}" varStatus="counter">
                                <option value="${implementingPartner.id}">${implementingPartner.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Procurement plan
                        <select id="procurement-plan" class="form-control">
                            <option value="Yes">Yes</option>
                            <option value="No">No</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Description
                        <input id="description" class="form-control">
                    </div>
                    <!--                    <div class="form-group">
                                            Expenditure
                                            <input id="value-achieved" type="number" step="0.01" class="form-control">
                                        </div>-->
                    <div class="form-group">
                        Allocated budget
                        <input id="allocated-budget" type="number" step="0.01" class="form-control">
                    </div>
                    <div class="form-group">
                        Expenditure category
                        <select id="expected-category" class="form-control">
                            <option value="">Select expenditure category</option>
                            <c:forEach var="expenditureCategory" items="${sessionScope.expenditureCategories}" varStatus="counter">
                                <option value="${expenditureCategory.id}">${expenditureCategory.category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        GOK percentage
                        <input id="gok-percentage" type="number" step="0.01" class="form-control" data-toggle="tooltip" data-placement="auto top">
                    </div>
                    <div class="form-group">
                        IFAD loan percentage
                        <input id="ifad-loan-percentage" type="number" step="0.01" class="form-control" data-toggle="tooltip" data-placement="auto top">
                    </div>
                    <div class="form-group">
                        IFAD grant percentage
                        <input id="ifad-grant-percentage" type="number" step="0.01" class="form-control" data-toggle="tooltip" data-placement="auto top">
                    </div>
                    <div class="form-group">
                        Beneficiaries percentage
                        <input id="beneficiaries-percentage" type="number" step="0.01" class="form-control" data-toggle="tooltip" data-placement="auto top">
                    </div>
                    <div class="form-group">
                        EU percentage
                        <input id="eu-percentage" type="number" step="0.01" class="form-control" data-toggle="tooltip" data-placement="auto top">
                    </div>
                    <div class="form-group">
                        Financial institution percentage
                        <input id="financial-institution-percentage" type="number" step="0.01" class="form-control" data-toggle="tooltip" data-placement="auto top">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>