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

        <div class="row">
            <div class="col-lg-9">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Farm Information of farmer <b>${sessionScope.farmer.name}</b>
                    </div>
                    <div class="panel-body">
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
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="home">
                                <h4>Farm Details</h4>
                                <table id="farm-table" class="table table-striped table-bordered table-hover data-table">                         
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
                                <table id="account-table" class="table table-striped table-bordered table-hover data-table">                         
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
                                <table id="loan-table" class="table table-striped table-bordered table-hover data-table">                         
                                    <thead>     <tr>
                                            <th><button type="button" class="btn btn-outline btn-primary" onclick="addLoan(); return false;">Add</button></th>
                                            <th>Loan Amount</th>
                                            <th>Loan Type</th>
                                            <th>Account Number</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <c:forEach var="loan" items="${sessionScope.loans}" varStatus="index">
                                            <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                                <td>${index.count}</td>
                                                <td>${loan.amount}</td>
                                                <td>${loan.type}</td>
                                                <td>${loan.account.accountNumber}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table> 
                            </div>
                            <div class="tab-pane fade" id="settings">
                                <h4>Inputs Collection</h4>
                                <table id="inputs-collection-table" class="table table-striped table-bordered table-hover data-table">                         
                                    <thead>
                                        <tr>
                                            <th><button type="button" class="btn btn-outline btn-primary" onclick="addInputsCollection(); return false;">Add</button></th>
                                            <th>Date collected</th>
                                            <th>Agro dealer name</th>
                                            <th>Agro dealer's business name</th>
                                            <th>Input type</th>
                                            <th>Input name</th>
                                            <th>Quantity</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <c:forEach var="inputsCollection" items="${sessionScope.inputsCollections}" varStatus="index">
                                            <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                                <td>${index.count}</td>
                                                <td>${inputsCollection.date}</td>
                                                <td>${inputsCollection.agroDealer.name}</td>
                                                <td>${inputsCollection.agroDealer.businessName}</td>
                                                <td>${inputsCollection.inputType.type}</td>
                                                <td>${inputsCollection.inputType.staticInput.name}</td>
                                                <td>${inputsCollection.quantity}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row dialog" id="loan-dialog">
            <div class="col-lg-12">
                <div class="panel-default">
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                Loan Amount
                                <input type="number " class="form-control" step="0.01" id="loan-amount" required="true">
                            </div>   
                            <div class="form-group">
                                Loan Type
                                <input type="text " class="form-control" id="loan-type" required="true">
                            </div>   
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="dialog" id="inputs-collection-dialog">
            <div class="col-lg-12">
                <div class="panel-default">
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                Date collected
                                <input type="date" id="date-collected" class="form-control datefield" required="true">
                            </div>
                            <div class="form-group">
                                Agro-dealer name
                                <select id="agro-dealer" class="form-control" required="true">
                                    <c:forEach var="agroDealer" items="${sessionScope.agroDealers}" varStatus="index">
                                        <option value="${agroDealer.id}">${agroDealer.name} - ${agroDealer.businessName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Input type
                                <select id="input-type" class="form-control" required="true">
                                    <c:forEach var="inputType" items="${sessionScope.inputTypes}" varStatus="index">
                                        <option value="${inputType.id}">${inputType.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Quantity
                                <input type="number " step="0.1" class="form-control" id="quantity" required="true">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>
