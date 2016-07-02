<%-- 
    Document   : county_AddProgramme
    Created on : Jun 28, 2016, 7:56:33 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add programme </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Programme details
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                Component
                                <select id="component" class="form-control">
                                    <c:forEach var="component" items="${sessionScope.components}">
                                        <option value="${component.id}">${component.component}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Sub-component
                                <select id="sub-component" class="form-control">
                                    <option selected>Select sub-component</option>
                                    <c:forEach var="subComponent" items="${sessionScope.subComponents}">
                                        <option value="${subComponent.id}">${subComponent.subComponent}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Activity
                                <select id="activity" class="form-control">
                                    <c:forEach var="activity" items="${sessionScope.activities}">
                                        <option value="${activity.id}">${activity.description}</option>
                                    </c:forEach>
                                </select> 
                            </div>     <div class="form-group">
                                Start period
                                <input id="start-period" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                End period
                                <input id="end-period" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Unit
                                <select id="measurement-unit" class="form-control">
                                    <c:forEach var="programmeMeasurementUnit" items="${sessionScope.programmeMeasurementUnits}">
                                        <option value="${programmeMeasurementUnit.id}">${programmeMeasurementUnit.unit}</option>
                                    </c:forEach>
                                </select>     </div>
                            <div class="form-group">
                                Annual Work Plans and Budgets(AWPB) target
                                <input id="awpb-target" class="form-control">
                            </div>
                            <div class="form-group">
                                Programme target
                                <input id="programme-target" class="form-control">
                            </div>
                            <div class="form-group">
                                Value achieved
                                <input id="value-achieved" class="form-control">
                            </div>
                            <div class="form-group">
                                Requested budget
                                <input id="requested-budget" class="form-control">
                            </div>
                            <div class="form-group">
                                Actual expenditure
                                <input id="actual-expenditure" class="form-control">
                            </div>
                            <div class="form-group">
                                Implementing partner
                                <select id="implementing-partner" class="form-control">
                                    <c:forEach var="implementingPartner" items="${sessionScope.implementingPartners}">
                                        <option value="${implementingPartner.id}">${implementingPartner.personRole.personRole}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <button type="button" class="btn btn-outline btn-primary" onclick="addProgramme()">Save programme</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:county>