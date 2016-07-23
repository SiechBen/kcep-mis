<%-- 
    Document   : region_addPlanning
    Created on : Jun 22, 2016, 4:19:40 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add planning </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Activity planning details
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
                                    <option selected disabled>Select sub-component</option>
                                    <c:forEach var="subComponent" items="${sessionScope.subComponents}">
                                        <option value="${subComponent.id}">${subComponent.subComponent}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Activity name/description
                                <select id="activity" class="form-control">
                                    <c:forEach var="activity" items="${sessionScope.activities}">
                                        <option value="${activity.id}">${activity.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Key Performance Indicator
                                <select id="performance-indicator" class="form-control">
                                    <c:forEach var="performanceIndicator" items="${applicationScope.performanceIndicators}">
                                        <option value="${performanceIndicator.id}">${performanceIndicator.description}</option>
                                    </c:forEach>
                                </select> 
                            </div>  
                            <div class="form-group">
                                Annual Workplan Reference Code
                                <input id="annual-workplan-reference-code" class="form-control">
                            </div>
                            <div class="form-group">
                                Start period
                                <input id="start-period" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                End period
                                <input id="end-period" class="form-control datefield">
                            </div>
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
                                Allocated budget
                                <input id="allocated-budget" class="form-control">
                            </div>
                            <div class="form-group">
                                Implementing partner
                                <select id="implementing-partner" class="form-control">
                                    <c:forEach var="implementingPartner" items="${sessionScope.implementingPartners}">
                                        <option value="${implementingPartner.id}">${implementingPartner.personRole.personRole}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Category
                                <input id="category" class="form-control">
                            </div>
                            <div class="form-group">
                                Procurement plan
                                <input id="procurement-plan" class="form-control">
                            </div>
                            <button type="button" class="btn btn-outline btn-primary" onclick="addActivityPlanning()">Save planning</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:region>
