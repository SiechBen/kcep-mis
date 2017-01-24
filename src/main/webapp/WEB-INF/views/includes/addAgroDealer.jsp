<%--
    Document   : addAgroDealer
    Created on : Oct 25, 2016, 8:50:38 AM
    Author     : siech
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
                <form role="form">
                    <div>
                        <input type="hidden" id="person-role" value="2">
                    </div>
                    <div class="form-group">
                        Name
                        <input id="person-name" class="form-control">
                    </div>
                    <div class="form-group">
                        National ID number
                        <input id="national-id" type="number" pattern="[0-9]{7}|{8}" title="National ID format" class="form-control">
                    </div>
                    <div class="form-group">
                        Year of birth
                        <select id="year-of-birth" class="form-control">
                            <option disabled>Select year</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Business name
                        <input id="business-name" class="form-control">
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