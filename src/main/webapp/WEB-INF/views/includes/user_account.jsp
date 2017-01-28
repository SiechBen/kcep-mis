<%--
    Document   : user_account
    Created on : Nov 30, 2016, 6:22:48 PM
    Author     : siech
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-6">
        <div class="panel panel-default">
            <div class="panel-heading">
                User account settings
            </div>
            <div class="panel-body">
                <form role="form">
                    <input id="person-id" type="hidden" value="${sessionScope.person.id}">
                    <div class="form-group">
                        Username
                        <input id="username" class="form-control" value="${sessionScope.person.username}">
                    </div>
                    <div class="form-group">
                        Current password
                        <input id="old-password" type="password" onchange="validatePassword()" class="form-control">
                        <div id="valid-password-information"></div>
                    </div>
                    <div class="form-group">
                        New password
                        <input id="new-password" type="password" class="form-control">
                        <div></div>
                    </div>
                    <div class="form-group">
                        Confirm password
                        <input id="confirm-password" type="password" onchange="matchPassword()" class="form-control">
                        <div id="matching-password-information"></div>
                    </div>
                    <div class="form-group">
                        <button type="button" class="btn btn-outline btn-primary" onclick="editUserAccount()">Update user account</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<c:choose>
    <c:when test="${sessionScope.person.personRoleId == 7}">
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        System backup
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                <button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('backupSystem')">Backup system</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:when test="${sessionScope.person.personRoleId == 9}">
        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        System backup
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                <button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('backupSystem')">Backup system</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
</c:choose>