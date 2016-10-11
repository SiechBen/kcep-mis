<%--
    Document   : procurement_plans_goods
    Created on : Oct 10, 2016, 1:00:45 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of procurement plans - goods
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addProcurementPlan</label>
                    <table class="table table-striped table-bordered table-hover data-table" id="procurement-plan-table">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Procurement plan type</th>
                                <th>Description</th>
                                <th>IFAD prior review</th>
                                <th>Plan vs Actual</th>
                                <th>Cost[KES]</th>
                                <th>Procurement method</th>
                                <th>Complete BD</th>
                                <th>*Approval by IFAD</th>
                                <th>Approval by SDA</th>
                                <th>Issue BD</th>
                                <th>Receive bids</th>
                                <th>Evaluate bids</th>
                                <th>*Approval by IFAD</th>
                                <th>Award</th>
                                <th>Approval by SDA/AG</th>
                                <th>Sign contract</th>
                                <th>Commence contract</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="17"> List of procurement plans - goods</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="procurementPlan" items="${sessionScope.procurementPlans}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${procurementPlan.procurementPlanType.type}</td>
                                    <td>${procurementPlan.description}</td>
                                    <td>${procurementPlan.ifadPriorReview.choice}</td>
                                    <td>${procurementPlan.planVsActual.choice}</td>
                                    <td>${procurementPlan.cost}</td>
                                    <td>${procurementPlan.procurementMethod.method}</td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.completeBd}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.approvalByIfad1}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.approvalBySda}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.issueBd}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.receiveBids}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.evaluateBids}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.approvalByIfad2}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.award}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.approvalBySdaOrAg}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.signContract}"/></td>
                                    <td><fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.commenceContract}"/></td>
                                    <td><button onclick="editProcurementPlan('${procurementPlan.id}', '${procurementPlan.procurementPlanType.id}',
                                                    '${procurementPlan.description}', '${procurementPlan.ifadPriorReview.id}', '${procurementPlan.planVsActual.id}',
                                                    '${procurementPlan.cost}', '${procurementPlan.procurementMethod.id}', '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.completeBd}"/>',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.approvalByIfad1}"/>', '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.approvalBySda}"/>', '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.issueBd}"/>',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.receiveBids}"/>', '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.evaluateBids}"/>', '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.approvalByIfad2}"/>',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.award}"/>', '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.approvalBySdaOrAg}"/>', '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.signContract}"/>',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${procurementPlan.commenceContract}"/>')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deleteProcurementPlan(${procurementPlan.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="procurement-plans-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
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
                </form>
            </div>
        </div>
    </div>
</div>
