<%--
    Document   : people
    Created on : Sep 9, 2016, 4:24:06 PM
    Author     : ronne
--%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of system users
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addPerson</label>
                    <table class="table table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
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
                                <td colspan="14">List of system users</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="person" items="${sessionScope.people}" varStatus="index">
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
                                    <td>${person.location.county.name}</td>
                                    <td>${person.location.subCounty.name}</td>
                                    <td>${person.location.ward.name}</td>
                                    <td>${person.contact.phone}</td>
                                    <td>${person.contact.email}</td>
                                    <td><button onclick="editPerson('${person.id}', '${person.name}', '${person.sex.id}', '${person.personRoleId}', '${person.nationalId}',
                                                    '<fmt:formatDate pattern="MM/dd/yyyy" value="${person.dateOfBirth}"/>', '${person.businessName}',
                                                    '${person.farmerGroup.id}', '${person.farmerSubGroup.id}', '${person.location.id}', '${person.location.county.id}',
                                                    '${person.location.subCounty.id}', '${person.location.ward.id}', '${person.contact.id}',
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
                                <td colspan="7" class="divider"></td>
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
                                <td> ${sessionScope.femaleElders} </td>
                                <td> ${sessionScope.femaleTotal} </td>
                                <td> ${sessionScope.maleYouth} </td>
                                <td> ${sessionScope.maleElders} </td>
                                <td> ${sessionScope.maleTotal} </td>
                                <td> ${sessionScope.total} </td>
                            </tr>
                            <tr>
                                <td> ${sessionScope.femaleYouth} </td>
                                <td> ${sessionScope.femaleElders} </td>
                                <td> ${sessionScope.femaleTotal} </td>
                                <td> ${sessionScope.maleYouth} </td>
                                <td> ${sessionScope.maleElders} </td>
                                <td> ${sessionScope.maleTotal} </td>
                                <td> ${sessionScope.total} </td>
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
