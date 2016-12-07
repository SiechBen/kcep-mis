<%--
    Document   : userProfile
    Created on : Sep 7, 2016, 2:12:34 PM
    Author     : ronne
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Profile details
            </div>
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Name
                        <input id="person-name" class="form-control" value="${sessionScope.person.name}">
                    </div>
                    <div class="form-group">
                        Person role
                        <select id="person-role" class="form-control">
                            <c:forEach var="personRole" items="${sessionScope.personRoles}" varStatus="index">
                                <option value="${personRole.id}" <c:if test="${personRole.id} == ${sessionScope.personRole.id}">selected</c:if>>${personRole.personRole}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        National id number
                        <input id="national-id" class="form-control" value="${sessionScope.person.nationalId}">
                    </div>
                    <div class="form-group">
                        Year of birth
                        <select id="year-of-birth" class="form-control">
                            <option selected value="${sessionScope.person.yearOfBirth}">${sessionScope.person.yearOfBirth}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Business name
                        <input id="business-name" class="form-control" value="${sessionScope.person.businessName}">
                    </div>
                    <div class="form-group">
                        Sex
                        <select id="sex" class="form-control">
                            <option value="${sessionScope.person.sex.id}" selected>${sessionScope.person.sex.sex}</option>
                            <c:forEach var="sex" items="${applicationScope.sexes}" varStatus="index">
                                <option value="${sex.id}">${sex.sex}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Phone number
                        <input id="phone" type="phone" class="form-control" value="${sessionScope.person.contact.phone}">
                    </div>
                    <div class="form-group">
                        Postal address
                        <input id="postal-address" class="form-control" value="${sessionScope.person.contact.postalAddress}">
                    </div>
                    <div class="form-group">
                        Email address
                        <input id="email" type="email" class="form-control" value="${sessionScope.person.contact.email}">
                    </div>
                    <div class="form-group">
                        County
                        <select id="county" class="form-control" onchange="updateSubCounties()">
                            <option value="${sessionScope.person.location.county.id}"selected>${sessionScope.person.location.county.name}</option>
                            <c:forEach var="county" items="${applicationScope.counties}" varStatus="index">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="sub-county" class="form-control" onchange="updateSubCounties()">
                            <option value="${sessionScope.person.location.subCounty.id}"selected>${sessionScope.person.location.subCounty.name}</option>
                            <c:forEach var="subCounty" items="${applicationScope.counties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Ward
                        <select id="ward" class="form-control" onchange="updateSubCounties()">
                            <option value="${sessionScope.person.location.ward.id}"selected>${sessionScope.person.location.ward.name}</option>
                            <c:forEach var="ward" items="${applicationScope.counties}" varStatus="index">
                                <option value="${ward.id}">${ward.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button type="button" class="btn btn-outline btn-primary" onclick="editPerson()">Save person</button>
                </form>
            </div>
        </div>
    </div>
</div>