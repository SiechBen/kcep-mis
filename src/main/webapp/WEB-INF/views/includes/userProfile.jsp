<%-- 
    Document   : userProfile
    Created on : Sep 7, 2016, 2:12:34 PM
    Author     : ronne
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                            <c:forEach var="personRole" items="${applicationScope.personRoles}" varStatus="index">
                                <option value="${personRole.id}" <c:if test="${personRole.id} == ${sessionScope.personRole.id}">selected</c:if>>${personRole.personRole}</option>
                            </c:forEach>
                        </select>    
                    </div>
                    <div class="form-group">
                        National id number
                        <input id="national-id" class="form-control" value="${sessionScope.person.nationalId}">
                    </div>
                    <div class="form-group">
                        Date of birth
                        <input id="date-of-birth" class="form-control datefield" value="${sessionScope.person.dateOfBirth}">
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
                        Farmer group
                        <select id="farmer-group" class="form-control">
                            <option value="${sessionScope.person.farmerGroup.id}"selected>${sessionScope.person.farmerGroup.name}</option>
                            <c:forEach var="farmerGroup" items="${applicationScope.farmerGroups}" varStatus="index"> 
                                <option value="${farmerGroup.id}">${farmerGroup.name}</option>
                            </c:forEach>
                        </select>    
                    </div>
                    <div class="form-group">
                        County
                        <select id="person-county" class="form-control">
                            <option value="${sessionScope.person.location.county.id}"selected>${sessionScope.person.location.county.name}</option>
                            <c:forEach var="county" items="${applicationScope.counties}" varStatus="index"> 
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>  
                    </div>
                    <div class="form-group">
                        Sub-county
                        <input id="person-sub-county" class="form-control" value="${sessionScope.person.location.subCounty}">
                    </div>
                    <div class="form-group">
                        Ward
                        <input id="person-ward" class="form-control" value="${sessionScope.person.location.ward}">
                    </div>
                    <button type="button" class="btn btn-outline btn-primary" onclick="editPerson()">Save person</button>
                </form>
            </div>
        </div>
    </div>
</div>