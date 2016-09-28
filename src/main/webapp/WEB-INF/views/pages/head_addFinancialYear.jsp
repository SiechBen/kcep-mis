<%-- 
    Document   : head_addFinancialYear
    Created on : Jul 27, 2016, 8:47:21 PM
    Author     : siech
--%>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add financial year </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Financial year details
                        </div>
                        <div class="panel-body">
                            <form id="equipment-form" role="form">
                                <div class="form-group">
                                    Financial year
                                    <input id="financial-year" class="form-control">
                                </div>
                                <div class="form-group">
                                    Current?
                                    <select id="current" class="form-control">
                                        <option value="true">Yes</option>
                                        <option value="false" selected>No</option>
                                    </select>
                                </div>
                                <input type="button" class="btn btn-outline btn-primary" onclick="addFinancialYear()" value="Save financial year">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:head>
