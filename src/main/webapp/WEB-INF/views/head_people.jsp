<%-- 
    Document   : head_people
    Created on : Jun 22, 2016, 3:34:17 PM
    Author     : siech
--%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view people </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of system users
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table id="people-table" class="table table-striped table-bordered table-hover data-table">
                                <thead>
                                    <tr>
                                        <td><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addPerson')">Add</button></td>
                                        <th>Name</th>
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
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="13" class="divider"></td>
                                    </tr>
                                    <tr>
                                        <td> Count by: </td>
                                        <td colspan="2">
                                            <select id="counter" onchange="updateCounts()">
                                                <c:forEach var="countOption" items="${sessionScope.countOptions}">
                                                    <option value="${countOption.id}">${countOption.personRole}</option>
                                                </c:forEach>
                                            </select> 
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="3" class="divider"></td>
                                    </tr>
                                    <tr>
                                        <td> Total number </td>
                                        <td> Female count </td>
                                        <td> Male count </td>
                                        <td colspan="10"> &nbsp; </td>
                                    </tr>
                                    <tr id="people-summary">
                                        <td> ${sessionScope.totalCount} </td>
                                        <td> ${sessionScope.femaleCount} </td>
                                        <td> ${sessionScope.maleCount} </td>
                                        <td colspan="10"> &nbsp; </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="person" items="${sessionScope.people}" varStatus="index">
                                        <c:choose>
                                            <c:when test="${person.personRoleId == 1}">
                                                <tr <c:if test="${index.count % 2 == 0}"> class="odd pointable" onclick="loadFarmWindow('${person.id}')"</c:if><c:if test="${index.count % 2 != 0}"> class="pointable" onclick="loadFarmWindow('${person.id}')"</c:if>>
                                                </c:when>
                                                <c:otherwise>
                                                <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                                </c:otherwise>
                                            </c:choose>
                                            <td>${index.count}</td>
                                            <td>${person.name}</td>
                                            <td>${person.sex.sex}</td>
                                            <td>${person.nationalId}</td>
                                            <td>${person.dateOfBirth}</td>
                                            <td>${person.businessName}</td>
                                            <td>${person.farmerGroup.name}</td>
                                            <td>${person.farmerSubGroup.name}</td>
                                            <td>${person.location.county.name}</td>
                                            <td>${person.location.subCounty.name}</td>
                                            <td>${person.location.ward.name}</td>
                                            <td>${person.contact.phone}</td>
                                            <td>${person.contact.email}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>