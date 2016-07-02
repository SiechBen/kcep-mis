<%-- 
    Document   : head_addActivity
    Created on : Jun 28, 2016, 7:57:35 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add activity </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Programme details
                    </div>
                    <div class="panel-body">
                        <form role="form">
                            <div class="form-group">
                                Activity
                                <input id="description" class="form-control">
                            </div>   
                            <button type="button" class="btn btn-outline btn-primary" onclick="addActivity()">Save activity</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>
