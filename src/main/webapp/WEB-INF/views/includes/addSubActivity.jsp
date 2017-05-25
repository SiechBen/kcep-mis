<%--
    Document   : addSubActivity
    Created on : Sep 7, 2016, 1:28:06 PM
    Author     : ronne
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Annual Workplan Budget item
            </div>
            <div class="panel-body">
                <form role="form">
                    <input type="hidden" id="activityPlanningId" value="${sessionScope.activityPlanningId}">
                    <div class="form-group">
                        Financial year
                        <select id="financial-year" class="form-control">
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
                        <select id="expected-outcome" class="form-control partial shown-eo">
                            <option disabled>Select expected outcome</option>
                            <c:forEach var="expectedOutcome" items="${sessionScope.expectedOutcomes}" varStatus="counter">
                                <option value="${expectedOutcome.id}">${expectedOutcome.category.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-eo" onclick="toggleExpectedOutcomeInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-expected-outcome" class="form-control" onchange="addExpectedOutcome(${sessionScope.expectedOutcomes[0].phenomenonType.id}); return false;">
                    </div>
                    <div class="form-group">
                        Component
                        <select id="component" class="form-control partial shown-c" onchange="updateSubComponents()">
                            <option disabled>Select component</option>
                            <c:forEach var="component" items="${sessionScope.components}" varStatus="counter">
                                <option value="${component.id}">${component.category.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-c" onclick="toggleComponentInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-component" class="form-control" onchange="addComponent(${sessionScope.components[0].phenomenonType.id}); return false;">
                    </div>
                    <div class="form-group">
                        Sub-component
                        <select id="sub-component" class="form-control partial shown-sc">
                            <option disabled>Select sub-component</option>
                            <c:forEach var="subComponent" items="${sessionScope.subComponents}" varStatus="counter">
                                <option value="${subComponent.id}">${subComponent.category.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-sc" onclick="toggleSubComponentInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-sub-component" class="form-control" onchange="addSubComponent(${sessionScope.subComponents[0].phenomenonType.id}); return false;">
                    </div>
                    <div class="form-group">
                        Annual indicator
                        <select id="annual-indicator" class="form-control partial shown-ai">
                            <option disabled>Select annual indicator</option>
                            <c:forEach var="annualIndicator" items="${sessionScope.annualIndicators}" varStatus="index">
                                <option value="${annualIndicator.id}">${annualIndicator.category.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-ai" onclick="toggleAnnualIndicatorInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-annual-indicator" class="form-control" onchange="addAnnualIndicator(${sessionScope.annualIndicators[0].phenomenonType.id}); return false;">
                    </div>
                    <div class="form-group">
                        Activity name
                        <select id="activity-name" class="form-control partial shown-an" onchange="updateSubActivityNames()">
                            <option disabled selected>Select activity name</option>
                            <c:forEach var="activityName" items="${sessionScope.activityNames}" varStatus="counter">
                                <option value="${activityName.id}">${activityName.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-an" onclick="toggleActivityNameInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-activity-name" class="form-control" onchange="flyAddActivityName(); return false;">
                    </div>
                    <div class="form-group">
                        Sub-activity name
                        <select id="sub-activity-name" class="form-control partial shown-san">
                            <option disabled selected>Select sub-activity name</option>
                            <c:forEach var="subActivityName" items="${sessionScope.subActivityNames}" varStatus="counter">
                                <option value="${subActivityName.id}">${subActivityName.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-san" onclick="toggleSubActivityNameInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-sub-activity-name" class="form-control" onchange="flyAddSubActivityName(); return false;">
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
                        <select id="measurement-unit" class="form-control partial shown-mu">
                            <c:forEach var="measurementUnit" items="${sessionScope.measurementUnits}" varStatus="counter">
                                <option value="${measurementUnit.id}">${measurementUnit.unit} <c:if test="${measurementUnit.symbol != null}">(${measurementUnit.symbol})</c:if></option>
                            </c:forEach>
                        </select>
                        <button class="shown-mu" onclick="toggleMeasurementUnitInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-measurement-unit" class="form-control" onchange="flyAddMeasurementUnit(); return false;" placeholder="Pixels (p)">
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
                        <select id="response-pcu" class="form-control partial shown-rp">
                            <c:forEach var="responsePcu" items="${sessionScope.responsePCUList}" varStatus="counter">
                                <option value="${responsePcu.id}">${responsePcu.category.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-rp" onclick="toggleResponsePcuInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-response-pcu" class="form-control" onchange="flyAddResponsePcu(${sessionScope.responsePCUList[0].phenomenonType.id}); return false;">
                    </div>
                    <div class="form-group">
                        Implementing partner
                        <select id="implementing-partner" class="form-control partial shown-ip">
                            <c:forEach var="implementingPartner" items="${sessionScope.implementingPartners}" varStatus="counter">
                                <option value="${implementingPartner.id}">${implementingPartner.category.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-ip" onclick="toggleImplementingPartnerInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-implementing-partner" class="form-control" onchange="flyAddImplementingPartner(${sessionScope.implementingPartners[0].phenomenonType.id}); return false;">
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
                    <div class="form-group">
                        Allocated budget
                        <input id="allocated-budget" type="number" step="0.01" class="form-control">
                    </div>
                    <div class="form-group">
                        Expenditure category
                        <select id="expenditure-category" class="form-control partial shown-ec">
                            <c:forEach var="expenditureCategory" items="${sessionScope.expenditureCategories}" varStatus="counter">
                                <option value="${expenditureCategory.id}">${expenditureCategory.category.name}</option>
                            </c:forEach>
                        </select>
                        <button class="shown-ec" onclick="toggleExpenditureCategoryInput(); return false;"><span class="glyphicon glyphicon-plus"></span></button>
                        <input id="other-expenditure-category" class="form-control" onchange="flyAddExpenditureCategory(${sessionScope.expenditureCategories[0].phenomenonType.id}); return false;">
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
                    <button type="button" class="btn btn-outline btn-primary" onclick="addSubActivity()">Save sub-activity</button>
                </form>
            </div>
        </div>
    </div>
</div>
