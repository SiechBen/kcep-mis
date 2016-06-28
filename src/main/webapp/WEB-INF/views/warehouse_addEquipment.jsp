<%-- 
    Document   : warehouse_addEquipment
    Created on : Jun 22, 2016, 4:00:34 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:genericpage>
    <jsp:attribute name="title"> KCEP-MIS - add equipment </jsp:attribute>
    <jsp:attribute name="menuitems"></jsp:attribute>
    <jsp:attribute name="content">

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Warehouse Operator</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            Equipment details
                        </div>
                        <div class="panel-body">
                            <form id="equipment-form" role="form">
                                <div class="form-group">
                                    Equipment type
                                    <input id="equipment-type" name="equipment-type" class="form-control">
                                </div>
                                <div class="form-group">
                                    Total Count
                                    <input type="number" id="equipment-total-count" name="equipment-total-count" class="form-control">
                                </div>
                                <div class="form-group">
                                    Equipment status
                                    <input id="equipment-status" name="equipment-status" class="form-control">
                                </div>
                                <input type="button" class="btn btn-outline btn-primary" onclick="addEquipment()" value="Save equipment">
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:genericpage>
