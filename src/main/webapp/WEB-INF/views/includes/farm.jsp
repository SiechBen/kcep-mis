<%-- 
    Document   : farm
    Created on : Sep 5, 2016, 2:42:34 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
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
                        <div class="float-left">
                            <h4>Account Details</h4>
                        </div>
                        <div class="float-right">
                            <button onclick="editAccount('${sessionScope.account.accountNumber}', '${sessionScope.account.eblBranch.id}', '${sessionScope.account.solId}', '${sessionScope.account.savings}')"><span class="glyphicon glyphicon-pencil large-12"></span></button>
                        </div>
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
                            <tr>
                                <th>Amount of savings</th>
                                <td>${sessionScope.account.savings}</td>
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
                                    <th>Input name</th>
                                    <th>Input type</th>
                                    <th>Input variety</th>
                                    <th>Quantity</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="inputsCollection" items="${sessionScope.inputsCollections}" varStatus="index">
                                    <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                        <td>${index.count}</td>
                                        <td>${inputsCollection.dateDone}</td>
                                        <td>${inputsCollection.agroDealer.name}</td>
                                        <td>${inputsCollection.agroDealer.businessName}</td>
                                        <td>${inputsCollection.inputType.type}</td>
                                        <td>${inputsCollection.staticInput.name}</td>
                                        <td>${inputsCollection.inputVariety.variety}</td>
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
                                    <th>Crop</th>
                                    <th>Date</th>
                                    <th>Quantity harvested</th>
                                    <th>Family consumption</th>
                                    <th>Quantity sold</th>
                                    <th>Post harvest loss</th>
                                    <th>Average selling price</th>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tbody> 
                                <c:forEach var="farmActivity" items="${sessionScope.farmActivities}" varStatus="index">
                                    <tr>
                                        <td>${index.count}</td>
                                        <td>${farmActivity.name}</td>
                                        <td>${farmActivity.yield}</td>
                                        <td>${farmActivity.dateDone}</td>
                                        <td>${farmActivity.quantityHarvested}</td>
                                        <td>${farmActivity.familyConsumption}</td>
                                        <td>${farmActivity.quantitySold}</td>
                                        <td>${farmActivity.postHarvestLoss}</td>
                                        <td>${farmActivity.averageSellingPrice}</td>
                                        <td><button onclick="editFarmActivity('${farmActivity.id}', '${farmActivity.quantityHarvested}', '${farmActivity.familyConsumption}', '${farmActivity.quantitySold}', '${farmActivity.postHarvestLoss}', '${farmActivity.yield}', '${farmActivity.dateDone}', '${farmActivity.name}', '${farmActivity.averageSellingPrice}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deleteFarmActivity('${farmActivity.id}')"><span class="glyphicon glyphicon-trash"></span></button></td>
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
<div class="row dialog" id="account-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Account Number
                        <input class="form-control" id="account-number" >
                    </div>
                    <div class="form-group">
                        Ebl Branch
                        <select  id="ebl-branch" class="form-control"> 
                            <c:forEach var="eblBranch" items="${sessionScope.eblBranches}" varStatus="index">
                                <option value="${eblBranch.id}">${eblBranch.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sol id
                        <input class="form-control" id="sol-id" >
                    </div>
                    <div class="form-group">
                        Deposit(+) or withdrawal(-)
                        <input type="number" step="0.01" class="form-control" id="change">
                    </div>
                    Savings
                    <div class="form-group">
                        <input type="number" step="0.01" class="form-control" id="savings"  readonly>
                    </div>
                </form>
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
                        <input class="form-control" id="loan-type" required="true">
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
                        Input collected
                        <select id="input-type" class="form-control" required="true" onchange="updateStaticInputs()">
                            <option disabled selected>Select input name</option>
                            <c:forEach var="inputType" items="${sessionScope.inputTypes}" varStatus="index">
                                <option value="${inputType.id}">${inputType.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Type of input collected
                        <select id="static-input" class="form-control" onchange="updateInputVarieties()">
                            <c:forEach var="staticInput" items="${sessionScope.staticInputs}" varStatus="index">
                                <option value="${staticInput.id}">${staticInput.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Variety of input collected
                        <select id="input-variety" class="form-control">
                            <c:forEach var="inputVariety" items="${sessionScope.inputVarieties}" varStatus="index">
                                <option value="${inputVariety.id}">${inputVariety.variety}</option>
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
                        Crop
                        <input id="yield" class="form-control">
                    </div>
                    <div class="form-group">
                        Date
                        <input type="date" id="farm-activity-date" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Quantity harvested
                        <input type="number" step="0.01" id="quantity-harvested" class="form-control">
                    </div>
                    <div class="form-group">
                        Family consumption
                        <input type="number" step="0.01" id="family-consumption" class="form-control">
                    </div>
                    <div class="form-group">
                        Quantity sold/taken to warehouse
                        <input type="number" step="0.01" id="quantity-sold" class="form-control">
                    </div>
                    <div class="form-group">
                        Post-harvest loss
                        <input type="number"  id="post-harvest-loss" step="0.01" class="form-control" readonly>
                    </div>
                    <div class="form-group">
                        Average selling price
                        <input type="number" id="average-selling-price" step="0.01" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
