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
    <jsp:attribute name="styles">
        <link href="static/plugins/password/css/password.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="content">
        <style>
            body {
                font-size: 100%;
                line-height: 1.8em;
                background: #fff;
                font-family:"Century Gothic", Helvetica, sans-serif;
            }
        </style>
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
                                    <input class="form-control" id="password" placeholder="Password" type="password" required>
                                </div>
                                <div class="checkbox">
                                    <label>
                                        <input id="remember" type="checkbox" value="Remember Me"> Remember Me
                                    </label>
                                </div>
                                <button id="login-button" class="btn btn-lg btn-block" onclick="loginUser()">Login</button>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script src="static/plugins/password/js/modernizr.custom.js"></script>
        <script src="static/plugins/password/js/zepto.custom.js"></script>
        <script src="static/plugins/password/js/password.min.js"></script>
        <script src="static/plugins/password/js/password.custom.js"></script>
    </jsp:attribute>
</kcep:genericpage>
