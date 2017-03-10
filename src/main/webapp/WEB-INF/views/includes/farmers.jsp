<%--
    Document   : farmers
    Created on : Oct 5, 2016, 11:23:05 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row push-up">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Farmers
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addFarmer</label>
                    <table id="farmers-table" class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th style="display: none">&nbsp;</th>
                                <th>&nbsp;</th>
                                <th>Name</th>
                                <th>Gender</th>
                                <th>Age</th>
                                <th>National ID</th>
                                <!--                                <th>Farmer group</th>
                                                                <th>Farmer sub-group</th>-->
                                <th>Category</th>
                                <th>County</th>
                                <th>Sub-county</th>
                                <th>Ward</th>
                                <th>Division</th>
                                <th>Village</th>
                                <th>Latitude,Longitude</th>
                                <th>Phone number</th>
                                <th>Plot size</th>
                                <th>Total inputs collected</th>
                                <th>Account number</th>
                                <th>Amount of savings</th>
                                <th>Loan amount</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="19">List of farmers</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="person" items="${sessionScope.farmers}" varStatus="index">
                                <tr class="farmer-row <c:if test="${person.account.solId == 2}">category2-farmer-row</c:if>">
                                    <td style="display: none">${person.id}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${index.count}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.sex.sex}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.age}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.nationalId}</td>
    <!--                                    <td onclick="loadFarmWindow(${person.id})">${person.farmerGroup.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.farmerSubGroup.name}</td>-->
                                    <td onclick="loadFarmWindow(${person.id})">${person.account.solId}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.county.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.subCounty.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.ward.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.divisionalLocation.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.village.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.latitude},${person.location.longitude}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.contact.phone}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.plotSize}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.totalInputsCollected}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.account.accountNumber}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.account.savings}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.account.totalLoanAmount}</td>
                                    <td><button onclick="editFarmer('${person.id}', '${person.name}',
                                                    '${person.sex.id}', '${person.nationalId}',
                                                    '${person.yearOfBirth}', '${person.farmerGroup.id}',
                                                    '${person.farmerSubGroup.id}', '${person.location.id}',
                                                    '${person.location.county.id}', '${person.location.subCounty.id}',
                                                    '${person.location.ward.id}', '${person.contact.id}',
                                                    '${person.contact.phone}', '${person.contact.email}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-lg-12">
        <jsp:include page="people_count.jsp"/>
    </div>
</div>

<div class="dialog" id="person-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Name
                        <input id="person-name" name="person-name" class="form-control">
                    </div>
                    <div class="form-group">
                        Gender
                        <select id="sex" name="sex" class="form-control">
                            <c:forEach var="sex" items="${applicationScope.sexes}" varStatus="index">
                                <option value="${sex.id}">${sex.sex}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <input type="hidden" id="person-role" value="1">
                    </div>
                    <div class="form-group">
                        National ID
                        <input id="national-id" name="national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Year of birth
                        <select id="year-of-birth" name="year-of-birth" class="form-control">
                            <option disabled>Select year</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Farmer Group
                        <input id="farmer-group" name="person-framer-group" class="form-control">
                    </div>
                    <div class="form-group">
                        Farmer SubGroup
                        <input id="farmer-sub-group" name="person-farmer-sub-group" class="form-control">
                    </div>
                    <div class="form-group">
                        County
                        <select id="county" name="person-county" class="form-control" onchange="updateSubCounties()">
                            <c:forEach var="county" items="${sessionScope.counties}" varStatus="index">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="sub-county" name="person-sub-county" class="form-control" onchange="updateWards()">
                            <c:forEach var="subCounty" items="${sessionScope.subCounties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Ward
                        <select id="ward" name="ward" class="form-control">
                            <c:forEach var="subCounty" items="${sessionScope.wards}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Phone
                        <input id="phone" name="person-phone" class="form-control">
                    </div>
                    <div class="form-group">
                        Email
                        <input id="email" name="person-email" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="evoucher-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Amount
                        <input id="e-voucher-amount" name="amount" class="form-control">
                    </div>
                    <div class="form-group">
                        Input type
                        <select id="e-voucher-input-type" name="input-type" class="form-control">
                            <c:forEach var="inputType" items="${sessionScope.inputTypes}" varStatus="index">
                                <option value="${inputType.id}">${inputType.type}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Person
                        <select id="e-voucher-person" name="person" class="form-control">
                            <c:forEach var="person" items="${sessionScope.eVoucherPeople}" varStatus="index">
                                <option value="${person.id}">${person.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Date redeemed
                        <input id="date-redeemed" name="date-redeemed" class="form-control datefield" type="date">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="search-person-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        National ID
                        <input id="search-national-id" name="search-national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Name
                        <input id="search-name" name="search-name" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!--
<div class="row" id="sample-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <div id="myWizard3">
                    <section class="step" data-step-title="page">
                        <div class="hero-unit">
                            <h1>Hey !</h1>
                            <p>Discover my awesome product</p>
                            <p>
                                <a class="btn btn-primary btn-large">
                                    Read more
                                </a>
                            </p>
                        </div>
                    </section>
                    <section class="step" data-step-title="page">
                        <div class="hero-unit">
                            <h1>Here it is</h1>
                            <p>Soon this product for you (and only you)</p>
                        </div>
                    </section>
                    <section class="step" data-step-title="page">
                        <div class="hero-unit">
                            <h1>Awesome !</h1>
                            <p>This product is now ready to use</p>
                            <p>
                                <a class="btn btn-primary btn-large">
                                    Get it !
                                </a>
                            </p>
                        </div>
                    </section>
                </div>
                <div id="myWizard3Pager" class="pagination pagination-centered">
                    <ul>
                        <li class="previous"><a href="#">&larr; Previous</a></li>
                        <li class="page"><a href="#" rel="1">1</a></li>
                        <li class="page"><a href="#" rel="2">2</a></li>
                        <li class="page"><a href="#" rel="3">3</a></li>
                        <li class="next"><a href="#">Next &rarr;</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>-->
