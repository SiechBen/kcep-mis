<%-- 
    Document   : procurement_plans
    Created on : Sep 7, 2016, 11:00:35 AM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of procurement plans
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addProcurementPlan')">Add</button></th>
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
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="17"> List of procurement plans</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="procurementPlan" items="${sessionScope.procurementPlans}" varStatus="index">
                                <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                    <td>${index.count}</td>
                                    <td>${procurementPlan.procurementPlanType.type}</td>
                                    <td>${procurementPlan.description}</td>
                                    <td>${procurementPlan.ifadPriorReview.choice}</td>
                                    <td>${procurementPlan.planVsActual.choice}</td>
                                    <td>${procurementPlan.cost}</td>
                                    <td>${procurementPlan.procurementMethod.method}</td>
                                    <td>${procurementPlan.completeBd}</td>
                                    <td>${procurementPlan.approvalByIfad1}</td>
                                    <td>${procurementPlan.approvalBySda}</td>
                                    <td>${procurementPlan.issueBd}</td>
                                    <td>${procurementPlan.receiveBids}</td>
                                    <td>${procurementPlan.evaluateBids}</td>
                                    <td>${procurementPlan.approvalByIfad2}</td>
                                    <td>${procurementPlan.award}</td>
                                    <td>${procurementPlan.approvalBySdaOrAg}</td>
                                    <td>${procurementPlan.signContract}</td>
                                    <td>${procurementPlan.commenceContract}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>