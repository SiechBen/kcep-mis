<%-- 
    Document   : region_addProcurementPlan
    Created on : Jul 2, 2016, 9:32:54 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add procurement plan </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Procurement plan details
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                Procurement plan type
                                <select id="procurement-plan-type" class="form-control">
                                    <c:forEach var="procurementPlanType" items="${sessionScope.procurementPlanTypes}">
                                        <option value="${procurementPlanType.id}">${procurementPlanType.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Description
                                <input id="description" class="form-control">
                            </div>
                            <div class="form-group">
                                IFAD prior review
                                <select id="ifad-prior-review" class="form-control">
                                    <c:forEach var="ifadPriorReview" items="${sessionScope.ifadPriorReviewChoices}">
                                        <option value="${ifadPriorReview.id}">${ifadPriorReview.choice}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Plan vs Actual
                                <select id="plan-vs-actual" class="form-control">
                                    <c:forEach var="planVsActual" items="${sessionScope.planVsActualChoices}">
                                        <option value="${planVsActual.id}">${planVsActual.choice}</option>
                                    </c:forEach>
                                </select> 
                            </div>  
                            <div class="form-group">
                                Cost [KES]
                                <input id="cost"  type="number" step="0.01"  class="form-control">
                            </div>
                            <div class="form-group">
                                Procurement method
                                <select id="procurement-method" class="form-control">
                                    <c:forEach var="procurementMethod" items="${sessionScope.procurementMethods}">
                                        <option value="${procurementMethod.id}">${procurementMethod.method}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                            <div class="form-group">
                                Complete BD
                                <input id="complete-bd" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                *Approval by IFAD
                                <input id="approval-by-ifad1" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Approval by SDA
                                <input id="approval-by-sda" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Issue BD
                                <input id="issue-bd" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Receive bids
                                <input id="receive-bids" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Evaluate bids
                                <input id="evaluate-bids" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Award
                                <input id="award" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                *Approval by IFAD
                                <input id="approval-by-ifad2" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Approval by SDA/AG
                                <input id="approval-by-sda-or-ag" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Sign contract
                                <input id="sign-contract" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Commence contract
                                <input id="commence-contract" class="form-control datefield">
                            </div>
                            <button type="button" class="btn btn-outline btn-primary" onclick="addProcurementPlan()">Save procurement plan</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:region>