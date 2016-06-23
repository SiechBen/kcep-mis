<%-- 
    Document   : region_addProgramme
    Created on : Jun 22, 2016, 4:19:40 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add programme </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Regional Coordinator</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
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
                                    <input type="text" id="activity" class="form-control">
                                </div>
                                <div class="form-group">
                                    Start period
                                    <input id="start-period" class="form-control datefield">
                                </div>
                                <div class="form-group">
                                    End period
                                    <input id="end-period" class="form-control datefield">
                                </div>
                                <div class="form-group">
                                    Unit
                                    <input id="unit" class="form-control">
                                </div>
                                <div class="form-group">
                                    AWP target
                                    <input id="awp-target" class="form-control">
                                </div>
                                <div class="form-group">
                                    Programme target
                                    <input id="programme-target" class="form-control">
                                </div>
                                <div class="form-group">
                                    Value achieved
                                    <input id="value-achieved" class="form-control">
                                </div>
                                <div class="form-group">
                                    Requested budget
                                    <input id="requested-budget" class="form-control">
                                </div>
                                <div class="form-group">
                                    Actual expenditure
                                    <input id="actual-expenditure" class="form-control">
                                </div>
                                <button type="button" class="btn btn-outline btn-primary" onclick="addProgramme()">Save programme</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:region>
