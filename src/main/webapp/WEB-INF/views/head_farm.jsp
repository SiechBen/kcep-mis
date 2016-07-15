<%-- 
    Document   : head_farm
    Created on : Jul 14, 2016, 7:36:11 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - national officer </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <!-- /.row -->
        <div class="row">
            <div class="col-lg-9">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Farm Information farmer ${sessionScope.farmer.name}
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#home" data-toggle="tab">Farm</a>
                            </li>
                            <li><a href="#profile" data-toggle="tab">Account</a>
                            </li>
                            <li><a href="#messages" data-toggle="tab">Loan</a>
                            </li>
                            <li><a href="#settings" data-toggle="tab">Inputs</a>
                            </li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <h4>Farm Details</h4>
                                <table id="people-table" class="table table-striped table-bordered table-hover data-table">                         
                                    <tr>
                                        <th>Plot Size</th>
                                        <td>${sessionScope.farmer.plotSize}</td>
                                    </tr>
                                    <tr>
                                        <th>County</th>
                                        <td>${sessionScope.farmer.location.county.name}</td>
                                    </tr>
                                    <tr>
                                        <th>Sub-county</th>
                                        <td>${sessionScope.farmer.location.county.name}</td>
                                    </tr>
                                    <tr>
                                        <th>Ward</th>
                                        <td>${sessionScope.farmer.location.ward.name}</td>
                                    </tr>
                                    <tr>
                                        <th>Divisional Location</th>
                                        <td>${sessionScope.farmer.location.divisionalLocation.name}</td>
                                    </tr>
                                    <tr>
                                        <th>Village</th>
                                        <td>${sessionScope.farmer.location.village.name}</td>
                                    </tr>
                                    <tr>
                                        <th>Latitude,Longitude</th>
                                        <td>${sessionScope.farmer.location.latitude},${sessionScope.farmer.location.longitude}</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="profile">
                                <h4>Account Details</h4>
                                <table id="people-table" class="table table-striped table-bordered table-hover data-table">                         
                                    <tr>
                                        <th>Account Number</th>
                                        <td>${sessionScope.account.accountNumber}</td>
                                    </tr>
                                    <tr>
                                        <th>Ebl Branch</th>
                                        <td>${sessionScope.account.eblBranch.name}</td>
                                    </tr>
                                    <tr>
                                        <th>Sol Id</th>
                                        <td>${sessionScope.account.solId}</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="tab-pane fade" id="messages">
                                <h4>Loan Details</h4>
                                <table id="people-table" class="table table-striped table-bordered table-hover data-table">                         
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addLoan')">Add</button></th>
                                        <th>Loan Amount</th>
                                        <th>Loan Type</th>
                                        <th>Account Number</th>
                                    </tr>
                                    <c:forEach var="loan" items="${sessionScope.loans}" varStatus="counter">
                                        <tr <c:if test="${counter % 2 == 0}">class="odd"</c:if>>
                                            <td>${counter}</td>
                                            <td>${sessionScope.loan.amount}</td>
                                            <td>${sessionScope.loan.type}</td>
                                            <td>${sessionScope.loan.account.accountNumber}</td>
                                        </tr>
                                    </c:forEach>
                                </table> 
                            </div>
                            <div class="tab-pane fade" id="settings">
                                <h4>Inputs Collection</h4>
                                <table id="people-table" class="table table-striped table-bordered table-hover data-table">                         
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addInputsCollection)">Add</button></th>
                                        <th>Date collected</th>
                                        <th>Agro dealer name</th>
                                        <th>Agro dealer's business name</th>
                                        <th>Input type</th>
                                        <th>Input name</th>
                                        <th>Quantity</th>
                                    </tr>
                                    <c:forEach var="inputsCollection" items="${sessionScope.inputsCollections}" varStatus="counter">
                                        <tr <c:if test="${counter % 2 == 0}">class="odd"</c:if>>
                                            <td>${counter}</td>
                                            <td>${sessionScope.inputsCollection.date}</td>
                                            <td>${sessionScope.inputsCollection.agroDealer.name}</td>
                                            <td>${sessionScope.inputsCollection.agroDealer.businessName}</td>
                                            <td>${sessionScope.inputsCollection.inputType.type}</td>
                                            <td>${sessionScope.inputsCollection.inputType.staticInput.name}</td>
                                            <td>${sessionScope.inputsCollection.quantity}</td>
                                        </tr>
                                    </c:forEach>
                                </table> 
                            </div>
                        </div>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
        </div>

    </jsp:attribute>
</kcep:head>
