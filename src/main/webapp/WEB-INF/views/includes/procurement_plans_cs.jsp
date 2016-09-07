<%-- 
    Document   : procurement_plans_cs
    Created on : Sep 7, 2016, 11:30:04 AM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of procurement plans-cs
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addProcurementPlanCs')">Add</button></th>
                                <th>Procurement plan type</th>
                                <th>Description</th>
                                <th>IFAD prior review</th>
                                <th>Plan vs Actual</th>
                                <th>Cost[KES]</th>
                                <th>Procurement method</th>
                                <th>Submit TOR</th>
                                <th>Complete REOI</th>
                                <th>Complete BD</th>
                                <th>*Approval by IFAD</th>
                                <th>Approval by SDA</th>
                                <th>Issue REOI</th>
                                <th>Receive EOIS</th>
                                <th>Establish short list</th>
                                <th>Complete RFP</th>
                                <th>*Approval by IFAD</th>
                                <th>*Approval by SDA</th>
                                <th>Issue RFP</th>
                                <th>Receive proposals</th>
                                <th>Evaluate technical proposals</th>
                                <th>*Approval by IFAD</th>
                                <th>Negotiate</th>
                                <th>*Approval by IFAD</th>
                                <th>Award</th>
                                <th>*Approval by SDA/AG</th>
                                <th>Sign contract</th>
                                <th>Commence contract</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="17"> List of procurement plans-cs</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="procurementPlanCs" items="${sessionScope.procurementPlansCs}" varStatus="index">
                                <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                    <td>${index.count}</td>
                                    <td>${procurementPlanCs.procurementPlanType.type}</td>
                                    <td>${procurementPlanCs.description}</td>
                                    <td>${procurementPlanCs.ifadPriorReview.choice}</td>
                                    <td>${procurementPlanCs.planVsActual.choice}</td>
                                    <td>${procurementPlanCs.cost}</td>
                                    <td>${procurementPlanCs.procurementMethod.method}</td>
                                    <td>${procurementPlanCs.submitTor}</td>
                                    <td>${procurementPlanCs.completeReoi}</td>
                                    <td>${procurementPlanCs.completeBd}</td>
                                    <td>${procurementPlanCs.approvalByIfad1}</td>
                                    <td>${procurementPlanCs.approvalBySda}</td>
                                    <td>${procurementPlanCs.issueReoi}</td>
                                    <td>${procurementPlanCs.receiveEois}</td>
                                    <td>${procurementPlanCs.establishShortList}</td>
                                    <td>${procurementPlanCs.completeRfp}</td>
                                    <td>${procurementPlanCs.approvalByIfad2}</td>
                                    <td>${procurementPlanCs.approvalBySda}</td>
                                    <td>${procurementPlanCs.issueRfp}</td>
                                    <td>${procurementPlanCs.receiveProposals}</td>
                                    <td>${procurementPlanCs.evaluateTechnicalProposals}</td>
                                    <td>${procurementPlanCs.approvalByIfad3}</td>
                                    <td>${procurementPlanCs.negotiate}</td>
                                    <td>${procurementPlanCs.approvalByIfad4}</td>
                                    <td>${procurementPlanCs.award}</td>
                                    <td>${procurementPlanCs.approvalBySdaOrAg}</td>
                                    <td>${procurementPlanCs.signContract}</td>
                                    <td>${procurementPlanCs.commenceContract}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>