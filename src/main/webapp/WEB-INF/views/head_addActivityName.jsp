<%-- 
    Document   : head_addActivityName
    Created on : Jul 22, 2016, 11:27:00 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add activity name </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Activity name details
                    </div>
                    <div class="panel-body">
                        <form id="activity-form" role="form">
                            <div class="form-group">
                                Name
                                <input id="name" class="form-control">
                            </div>
                            <input type="button" class="btn btn-outline btn-primary" onclick="addActivityName()" value="Save activity name">
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>

