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
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
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
                                    <td><button onclick="doEditProcurementPlansCs('${procurementPlanCs.id}', '${procurementPlanCs.procurementPlanType.type}', '${procurementPlanCs.description}','${procurementPlanCs.planVsActual.choice}', '${procurementPlanCs.cost}', '${procurementPlanCs.procurementMethod.method}', '${procurementPlanCs.submitTor}', '${procurementPlanCs.completeReoi}', '${procurementPlanCs.completeBd}', '${procurementPlanCs.approvalByIfad1}', '${procurementPlanCs.approvalBySda}', '${procurementPlanCs.issueReoi}', '${procurementPlanCs.receiveEois}', '${procurementPlanCs.establishShortList}', '${procurementPlanCs.completeRfp}', '${procurementPlanCs.approvalByIfad2}', '${procurementPlanCs.issueRfp}', '${procurementPlanCs.receiveProposals}', '${procurementPlanCs.evaluateTechnicalProposals}', '${procurementPlanCs.approvalByIfad3}', '${procurementPlanCs.negotiate}', '${procurementPlanCs.approvalByIfad4}', '${procurementPlanCs.award}', '${procurementPlanCs.approvalBySdaOrAg}', '${procurementPlanCs.signContract}', '${procurementPlanCs.commenceContract}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="doDeleteProcurementPlansCs(${procurementPlanCs.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row dialog" id="procurement-plans-cs-dialog">
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
                        Submit TOR
                        <input id="submit-tor" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Complete REOI
                        <input id="complete-reoi" class="form-control datefield">
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
                        *Approval by SDA
                        <input id="approval-by-sda" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Issue REOI
                        <input id="issue-reoi" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Receive EOIS
                        <input id="receive-eois" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Evaluate bids
                        <input id="evaluate-bids" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Establish short list
                        <input id="establish-short-list" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Complete RFP
                        <input id="complete-rfp" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        *Approval by IFAD
                        <input id="approval-by-ifad2" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        *Approval by SDA
                        <input id="approval-by-sda" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Issue RFP
                        <input id="issue-rfp" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Receive proposals
                        <input id="receive-proposals" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Evaluate technical proposals
                        <input id="evaluate-technical-proposals" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        *Approval by IFAD
                        <input id="approval-by-ifad3" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Negotiate
                        <input id="negotiate" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        *Approval by IFAD
                        <input id="approval-by-ifad4" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Award
                        <input id="award" class="form-control datefield">
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