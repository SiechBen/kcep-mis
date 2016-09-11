<%-- 
    Document   : people
    Created on : Sep 9, 2016, 4:24:06 PM
    Author     : ronne
--%>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                List of system users
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addPerson')">Add</button></th>
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
                                <th>&nbsp;</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="15" class="divider"></td>
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
                                        <tr class="farmer-row" onclick="loadFarmWindow('${person.id}')">
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="odd">
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
                                    <td><button onclick="editPerson('${person.id}', '${person.name}', '${person.sex.sex}', '${person.nationalId}',
                                                '${person.dateOfBirth}', '${person.businessName}', '${person.farmerGroup.name}', '${person.farmerSubGroup.name}',
                                                '${person.location.county.name}', '${person.location.subCounty.name}', '${person.location.ward.name}',
                                                '${person.contact.phone}', '${person.contact.email}')"><span class="glyphicon glyphicon-pencil"></span></button></td>
                                    <td><button onclick="deletePerson('${person.id}')"><span class="glyphicon glyphicon-trash"></span></button></td>
                                </tr>
                            </c:forEach>
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
                        <select id="person-county" name="person-county" class="form-control">
                            <c:forEach var="county" items="${applicationScope.counties}" varStatus="index"> 
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>  
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="person-sub-county" name="person-sub-county" class="form-control">
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
