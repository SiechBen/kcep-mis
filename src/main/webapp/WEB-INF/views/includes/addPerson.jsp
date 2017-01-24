<%--
    Document   : addPerson
    Created on : Sep 7, 2016, 1:08:36 PM
    Author     : ronne
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                Person details
            </div>
            <div class="panel-body">
                <form role="form" id="add-person-form">
                    <div class="form-group">
                        Name
                        <input id="person-name" class="form-control">
                    </div>
                    <div class="form-group">
                        Person role
                        <select id="person-role" class="form-control" onchange="hideLocation()">
                            <c:forEach var="personRole" items="${sessionScope.personRoles}" varStatus="index">
                                <option value="${personRole.id}">${personRole.personRole}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        National ID number
                        <input id="national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Year of birth
                        <select id="year-of-birth" class="form-control">
                            <option disabled>Select year</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Gender
                        <select id="sex" class="form-control">
                            <c:forEach var="sex" items="${applicationScope.sexes}" varStatus="index">
                                <option value="${sex.id}">${sex.sex}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Phone number
                        <input id="phone" type="phone" class="form-control">
                    </div>
                    <div class="form-group">
                        Postal address
                        <input id="postal-address" class="form-control">
                    </div>
                    <div class="form-group">
                        Email address
                        <input id="email" type="email" class="form-control">
                    </div>