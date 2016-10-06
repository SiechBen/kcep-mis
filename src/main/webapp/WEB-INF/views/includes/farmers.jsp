<%--
    Document   : farmers
    Created on : Oct 5, 2016, 11:23:05 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Beneficiaries
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addPerson</label>
                    <table id="farmers-table" class="table table-bordered table-hover evoucher-people">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Name</th>
                                <th>Person role</th>
                                <th>Gender</th>
                                <th>Age</th>
                                <th>National id</th>
                                <th>Business name</th>
                                <th>Farmer group</th>
                                <th>Farmer sub-group</th>
                                <th>County</th>
                                <th>Sub-county</th>
                                <th>Ward</th>
                                <th>Phone number</th>
                                <th>Email address</th>
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="16">List of farmers</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="person" items="${sessionScope.farmers}" varStatus="index">
                                <tr class="farmer-row">
                                    <td onclick="loadFarmWindow(${person.id})">${index.count}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.personRole}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.sex.sex}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.age}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.nationalId}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.businessName}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.farmerGroup.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.farmerSubGroup.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.county.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.subCounty.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.ward.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.contact.phone}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.contact.email}</td>
                                    <td><button onclick="editPerson('${person.id}', '${person.name}', '${person.sex.id}', '${person.nationalId}', '${person.personRoleId}',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${person.dateOfBirth}"/>', '${person.businessName}', '${person.farmerGroup.id}', '${person.farmerSubGroup.id}',
                                                    '${person.location.id}', '${person.location.county.id}', '${person.location.subCounty.id}', '${person.location.ward.id}', '${person.contact.id}',
                                                    '${person.contact.phone}', '${person.contact.email}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deletePerson(${person.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <table class="table table-bordered table-hover evoucher-data-table">
                        <tbody>
                            <tr>
                                <td> Count by: </td>
                                <td colspan="6">
                                    <select id="counter" onchange="updateCounts()">
                                        <c:forEach var="countOption" items="${sessionScope.countOptions}">
                                            <option value="${countOption.id}">${countOption.personRole}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3"> <strong>Female</strong> </td>
                                <td colspan="3"> <strong>Male</strong> </td>
                                <td rowspan="2"> <strong>Total</strong> </td>
                            </tr>
                            <tr>
                                <td> <strong>Youth(<35 years old)</strong> </td>
                                <td> <strong>Elderly(>35 years old)</strong> </td>
                                <td> <strong>Female Total</strong> </td>
                                <td> <strong>Youth(<35 years old)</strong> </td>
                                <td> <strong>Elderly(>35 years old)</strong> </td>
                                <td> <strong>Male Total</strong> </td>
                            </tr>
                            <tr id="people-summary">
                                <td> ${sessionScope.femaleYouth} </td>
                                <td> ${sessionScope.femaleElderly} </td>
                                <td> ${sessionScope.femaleTotal} </td>
                                <td> ${sessionScope.maleYouth} </td>
                                <td> ${sessionScope.maleElderly} </td>
                                <td> ${sessionScope.maleTotal} </td>
                                <td> ${sessionScope.totalPeople} </td>
                            </tr>
                            <tr>
                                <td colspan="7" class="divider"></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
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
                    <div class="form-group">
                        Person role
                        <select id="person-role" class="form-control">
                            <c:forEach var="personRole" items="${applicationScope.personRoles}" varStatus="index">
                                <option value="${personRole.id}">${personRole.personRole}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        National ID
                        <input id="national-id" name="national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Date Of Birth
                        <input id="date-of-birth" name="date-of-birth" class="form-control datefield">
                    </div>
                    <div class="form-group">
                        Business Name
                        <input id="business-name" name="person-business-name" class="form-control">
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
                            <c:forEach var="county" items="${applicationScope.counties}" varStatus="index">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="sub-county" name="person-sub-county" class="form-control" onchange="updateWards()">
                            <c:forEach var="subCounty" items="${applicationScope.subCounties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Ward
                        <select id="ward" name="ward" class="form-control">
                            <c:forEach var="subCounty" items="${applicationScope.subCounties}" varStatus="index">
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
                        <input id="date-redeemed" name="date-redeemed" class="form-control datefield">
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
                        National id
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