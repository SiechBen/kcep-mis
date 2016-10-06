<%--
    Document   : loginpage
    Created on : Jun 4, 2016, 8:31:25 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:genericpage>
    <jsp:attribute name="title"> KCEP-MIS - login </jsp:attribute>
    <jsp:attribute name="menuitems"></jsp:attribute>
    <jsp:attribute name="content">

        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">
                        <div class="panel-heading">
                            <h3 class="panel-title">Please Sign In</h3>
                        </div>
                        <div id="login-form" class="panel-body">
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" id="username" placeholder="E-mail" type="email" autofocus required>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" id="password" placeholder="Password" type="password" value="" required>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input id="remember" type="checkbox" value="Remember Me"> Remember Me
                                    </label>
                                </div>
                                <button id="login-button" class="btn btn-lg btn-success btn-block" onclick="loginUser()">Login</button>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:genericpage>
