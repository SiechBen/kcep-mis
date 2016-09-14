<%-- 
    Document   : addEquipment
    Created on : Sep 8, 2016, 4:04:08 PM
    Author     : ronne
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="page-wrapper">
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
                            Serial number
                            <input id="serial-number" name="serial-number" class="form-control">
                        </div>
                        <div class="form-group">
                            Total Count
                            <input type="number" id="equipment-total-count" name="equipment-total-count" class="form-control">
                        </div>
                        <div class="form-group">
                            Equipment status
                            <input id="equipment-status" name="equipment-status" class="form-control">
                        </div>
                        <input type="button" class="btn btn-outline btn-primary" onclick="addEquipment(${sessionScope.warehouse.id})" value="Save equipment">
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>