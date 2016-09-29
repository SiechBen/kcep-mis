<%--
    Document   : eVouchers
    Created on : Sep 6, 2016, 12:16:47 PM
    Author     : qortez
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                e-Voucher information
            </div>
            <div class="panel-body">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#farmers" data-toggle="tab">Farmers</a>
                    </li>
                    <li>
                        <a href="#agro-dealers" data-toggle="tab">Agro-dealers</a>
                    </li>
                    <li>
                        <a href="#e-vouchers" data-toggle="tab">E-vouchers</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="farmers">
                        <h4>Farmers</h4>
                        <table id="farmers-table" class="table table-bordered table-hover data-table">
                            <thead>
                                <tr>
                                    <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addPerson')">Add</button></th>
                                    <th>Name</th>
                                    <th>Person role</th>
                                    <th>Gender</th>
                                    <th>National id</th>
                                    <th>Date of birth</th>
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
                                    <td colspan="14">List of farmers</td>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="person" items="${sessionScope.farmers}" varStatus="index">
                                    <c:choose>
                                        <c:when test="${person.personRoleId == 1}">
                                            <tr class="farmer-row" onclick="loadFarmWindow('${person.id}')">
                                            </c:when>
                                            <c:otherwise>
                                            <tr class="odd">
                                            </c:otherwise>
                                        </c:choose>
                                        <td>${index.count}</td>
                                        <td>${person.name}</td>
                                        <td>${person.personRole}</td>
                                        <td>${person.sex.sex}</td>
                                        <td>${person.nationalId}</td>
                                        <td><fmt:formatDate pattern="MM/dd/yyyy" value="${person.dateOfBirth}"/></td>
                                        <td>${person.businessName}</td>
                                        <td>${person.farmerGroup.name}</td>
                                        <td>${person.farmerSubGroup.name}</td>
                                        <td>${person.location.county.name}</td>
                                        <td>${person.location.subCounty.name}</td>
                                        <td>${person.location.ward.name}</td>
                                        <td>${person.contact.phone}</td>
                                        <td>${person.contact.email}</td>
                                        <td><button onclick="editPerson('${person.id}', '${person.name}', '${person.sex.sex}', '${person.nationalId}',
                                                        '<fmt:formatDate pattern="MM/dd/yyyy" value="${person.dateOfBirth}"/>', '${person.businessName}', '${person.farmerGroup.name}', '${person.farmerSubGroup.name}',
                                                        '${person.location.county.name}', '${person.location.subCounty.name}', '${person.location.ward.name}',
                                                        '${person.contact.phone}', '${person.contact.email}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deletePerson(${person.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <table class="table table-bordered table-hover data-table">
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
                    <div class="tab-pane fade" id="agro-dealers">
                        <h4>Agro-dealers</h4>
                        <table id="agro-dealers-table" class="table table-striped table-bordered table-hover data-table">
                            <thead>
                                <tr>
                                    <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addPerson')">Add</button></th>
                                    <th>Name</th>
                                    <th>Person role</th>
                                    <th>Gender</th>
                                    <th>National id</th>
                                    <th>Date of birth</th>
                                    <th>Business name</th>
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
                                    <td colspan="14">List of agro-dealers</td>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="person" items="${sessionScope.agroDealers}" varStatus="index">
                                    <tr>
                                        <td>${index.count}</td>
                                        <td>${person.name}</td>
                                        <td>${person.personRole}</td>
                                        <td>${person.sex.sex}</td>
                                        <td>${person.nationalId}</td>
                                        <td><fmt:formatDate pattern="MM/dd/yyyy" value="${person.dateOfBirth}"/></td>
                                        <td>${person.businessName}</td>
                                        <td>${person.location.county.name}</td>
                                        <td>${person.location.subCounty.name}</td>
                                        <td>${person.location.ward.name}</td>
                                        <td>${person.contact.phone}</td>
                                        <td>${person.contact.email}</td>
                                        <td><button onclick="editPerson('${person.id}', '${person.name}', '${person.sex.sex}', '${person.nationalId}',
                                                        '<fmt:formatDate pattern="MM/dd/yyyy" value="${person.dateOfBirth}"/>', '${person.businessName}', '${person.farmerGroup.name}', '${person.farmerSubGroup.name}',
                                                        '${person.location.county.name}', '${person.location.subCounty.name}', '${person.location.ward.name}',
                                                        '${person.contact.phone}', '${person.contact.email}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deletePerson(${person.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <table class="table table-bordered table-hover data-table">
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
                    <div class="tab-pane fade" id="e-vouchers">
                        <h4>E-vouchers</h4>
                        <label hidden id="add-label">addEVoucher</label>
                        <table id="e-voucher-table" class="table table-striped table-bordered table-hover data-table">
                            <thead>
                                <tr>
                                    <th>&nbsp;</th>
                                    <th>Amount</th>
                                    <th>Input type</th>
                                    <th>Person</th>
                                    <th>Date redeemed</th>
                                    <th>Attachment i.e scanned pages/ photos from the inputs logging book</th>
                                    <th>&nbsp;</th>
                                    <th>&nbsp;</th>
                                </tr>
                            </thead>
                            <tfoot>
                                <tr>
                                    <td colspan="8"> List of e-vouchers </td>
                                </tr>
                            </tfoot>
                            <tbody>
                                <c:forEach var="eVoucher" items="${sessionScope.eVouchers}" varStatus="index">
                                    <tr>
                                        <td>${index.count}</td>
                                        <td>${eVoucher.amount}</td>
                                        <td>${eVoucher.inputType.type}</td>
                                        <td>${eVoucher.person.name}</td>
                                        <td><fmt:formatDate pattern="MM/dd/yyyy" value="${eVoucher.dateRedeemed}"/></td>
                                        <td><a onclick="loadAjaxWindow('download?filePath=${eVoucher.inputsLogbookPage}')" target="_blank">${eVoucher.fileName}</a></td>
                                        <td><button onclick="editEVoucher('${eVoucher.id}', '${eVoucher.amount}', '${eVoucher.inputType.id}', '${eVoucher.person.id}', '<fmt:formatDate pattern="MM/dd/yyyy" value="${eVoucher.dateRedeemed}"/>')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                        <td><button onclick="deletEVoucher(${eVoucher.id})"><span class="glyphicon glyphicon-trash"></span></button></td>
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
                        <input id="gender" type="gender" step="0.01" name="gender" class="form-control">
                    </div>
                    <div class="form-group">
                        National ID
                        <input type="person-nationalid" id="nationalid" name="nationalid" class="form-control">
                    </div>
                    <div class="form-group">
                        Date Of Birth
                        <input type="dateOfBirth" id="nationalid" name="nationalid" class="form-control">
                    </div>
                    <div class="form-group">
                        Business Name
                        <input id="person-businessname" name="person-businessname" class="form-control">
                    </div>
                    <div class="form-group">
                        Farmer Group
                        <input id="person-farmergroup" name="person-framergroup" class="form-control">
                    </div>
                    <div class="form-group">
                        Farmer SubGroup
                        <input id="person-farmersubgroup" name="person-farmersubgroup" class="form-control">
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
                        <input id="person-phone" name="person-phone" class="form-control">
                    </div>
                    <div class="form-group">
                        Email
                        <input id="person-email" name="person-email" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>