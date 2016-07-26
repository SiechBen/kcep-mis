<%-- 
    Document   : region_addSubActivity
    Created on : Jul 15, 2016, 8:06:59 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add sub-activity </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Sub-activity details
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <input type="hidden" id="activityPlanningId" value="${sessionScope.activityPlanningId}">
                            <div class="form-group">
                                Annual workplan reference code
                                <input id="annual-workplan-reference-code" class="form-control">
                            </div>   
                            <div class="form-group">
                                Component
                                <select id="component" class="form-control">
                                    <c:forEach var="component" items="${sessionScope.components}" varStatus="counter">
                                        <option value="${component.id}">${component.component}</option>
                                    </c:forEach>
                                </select>
                            </div>   
                            <div class="form-group">
                                Sub-component
                                <select id="sub-component" class="form-control">
                                    <c:forEach var="subComponent" items="${sessionScope.subComponents}" varStatus="counter">
                                        <option value="${subComponent.id}">${subComponent.subComponent}</option>
                                    </c:forEach>
                                </select>
                            </div>   
                            <div class="form-group">
                                Performance indicator
                                <select id="performance-indicator" class="form-control">
                                    <c:forEach var="performanceIndicator" items="${sessionScope.performanceIndicators}" varStatus="counter">
                                        <option value="${performanceIndicator.id}">${performanceIndicator.description}</option>
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
                                    <c:forEach var="subActivityName" items="${sessionScope.subActivityNames}" varStatus="counter">
                                        <option value="${subActivityName.id}">${subActivityName.name}</option>
                                    </c:forEach>
                                </select>
                            </div>   
                            <div class="form-group">
                                Start Date
                                <input id="start-date" class="form-control datefield">
                            </div>   
                            <div class="form-group">
                                End date
                                <input id="end-date" class="form-control datefield">
                            </div>   
                            <div class="form-group">
                                Measurement unit
                                <select id="measurement-unit" class="form-control">
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
                                <input id="totals" class="form-control" type="number" step="0.01">
                            </div>   
                            <div class="form-group">
                                Response PCU
                                <select id="response-pcu" class="form-control">
                                    <c:forEach var="responsePcu" items="${sessionScope.responsePcuList}" varStatus="counter">
                                        <option value="${responsePcu.id}">${responsePcu.name}</option>
                                    </c:forEach>
                                </select>
                            </div>   
                            <div class="form-group">
                                Implementing partner
                                <select id="implementing-partner" class="form-control">
                                    <c:forEach var="implementingPartner" items="${sessionScope.implementingPartners}" varStatus="counter">
                                        <option value="${implementingPartner.id}">${implementingPartner.personRole.personRole}</option>
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
                            <div class="form-group">
                                Value achieved
                                <input id="value-achieved" type="number" step="0.01" class="form-control">
                            </div>   
                            <div class="form-group">
                                Allocated budget
                                <input id="allocated-budget" type="number" step="0.01" class="form-control">
                            </div>   
                            <div class="form-group">
                                Expenditure category
                                <select id="expected-category" class="form-control">
                                    <c:forEach var="expenditureCategory" items="${sessionScope.expenditureCategories}" varStatus="counter">
                                        <option value="${expenditureCategory.id}">${expenditureCategory.name}</option>
                                    </c:forEach>
                                </select>
                            </div>   
                            <div class="form-group">
                                GOK percentage
                                <input id="gok-percentage" type="number" step="0.01" class="form-control">
                            </div>   
                            <div class="form-group">
                                IFAD loan percentage
                                <input id="ifad-loan-percentage" type="number" step="0.01" class="form-control">
                            </div>   
                            <div class="form-group">
                                IFAD grant percentage
                                <input id="ifad-grant-percentage" type="number" step="0.01" class="form-control">
                            </div>   
                            <div class="form-group">
                                Beneficiaries percentage
                                <input id="beneficiaries-percentage" type="number" step="0.01" class="form-control">
                            </div>   
                            <div class="form-group">
                                EU percentage
                                <input id="eu-percentage" type="number" step="0.01" class="form-control">
                            </div>   
                            <div class="form-group">
                                Financial institution percentage
                                <input id="financial-institution-percentage" type="number" step="0.01" class="form-control">
                            </div>   
                            <button type="button" class="btn btn-outline btn-primary" onclick="addSubActivity()">Save sub-activity</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:region>