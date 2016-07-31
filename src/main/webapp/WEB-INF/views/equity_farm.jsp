<%-- 
    Document   : equity_farm
    Created on : Jul 22, 2016, 10:30:59 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:equity>
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
                            <li class="active">
                                <a href="#farm" data-toggle="tab">Farm</a>
                            </li>
                            <li>
                                <a href="#account" data-toggle="tab">Account</a>
                            </li>
                            <li>
                                <a href="#loan" data-toggle="tab">Loan</a>
                            </li>
                            <li>
                                <a href="#inputs" data-toggle="tab">Inputs</a>
                            </li>
                            <li>
                                <a href="#farm-activities" data-toggle="tab">Farm Activities</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane fade in active" id="farm">
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
                            <div class="tab-pane fade" id="account">
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
                            <div class="tab-pane fade" id="loan">
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
                            <div class="tab-pane fade" id="inputs">
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
                                                <td>${inputsCollection.staticInput.name}</td>
                                                <td>${inputsCollection.quantity}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table> 
                            </div>     
                            <div class="tab-pane fade" id="farm-activities">
                                <h4>Farm activity</h4>
                                <table id="farm-activity-table" class="table table-striped table-bordered table-hover data-table">                         
                                    <thead>
                                        <tr>
                                            <th><button type="button" class="btn btn-outline btn-primary" onclick="addFarmActivity(); return false;">Add</button></th>
                                            <th>Farm activity name</th>
                                            <th>Yield</th>
                                            <th>Date</th>
                                            <th>Quantity sold</th>
                                            <th>Quantity harvested</th>
                                            <th>Average selling price</th>
                                        </tr>
                                    </thead>
                                    <tbody> 
                                        <c:forEach var="farmActivity" items="${sessionScope.farmActivities}" varStatus="index">
                                            <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                                <td>${index.count}</td>
                                                <td>${farmActivity.name}</td>
                                                <td>${farmActivity.yield}</td>
                                                <td>${farmActivity.date}</td>
                                                <td>${farmActivity.quantitySold}</td>
                                                <td>${farmActivity.quantityHarvested}</td>
                                                <td>${farmActivity.averageSellingPrice}</td>
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
                                <select id="input-type" class="form-control" required="true" onchange="updateStaticInputs()">
                                    <c:forEach var="inputType" items="${sessionScope.inputTypes}" varStatus="index">
                                        <option value="${inputType.id}">${inputType.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="form-group">
                                Input name
                                <select id="static-input" class="form-control">
                                    <c:forEach var="staticInput" items="${sessionScope.staticInputs}" varStatus="index">
                                        <option value="${staticInput.id}">${staticInput.name}</option>
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
        <div class="dialog" id="farm-activity-dialog">
            <div class="col-lg-12">
                <div class="panel-default">
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                Farm activity name
                                <input id="farm-activity-name" class="form-control" required="true">
                            </div>
                            <div class="form-group">
                                Yield
                                <input  id="yield" class="form-control">
                            </div>
                            <div class="form-group">
                                Date
                                <input type="date" id="farm-activity-date" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Quantiy sold
                                <input type="number" id="quantity-sold" step="0.01" class="form-control" required="true">
                            </div>
                            <div class="form-group">
                                Quantiy harvested
                                <input type="number" id="quantity-harvested" step="0.01" class="form-control" required="true">
                            </div>
                            <div class="form-group">
                                Average selling price
                                <input type="number" id="average-selling-price" step="0.01" class="form-control" required="true">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:equity>