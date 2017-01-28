<%--
    Document   : view_farmers_on_map
    Created on : Jan 25, 2017, 7:37:21 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Agro-dealers locations on map
            </div>
            <div class="panel-body">
                <input type="hidden" id="person-type" value="Farmer">
                <div id="map"></div>
            </div>
        </div>
    </div>
</div>

<script src="static/js/maps.js" type="text/javascript"></script>

<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAosZcLbpuT4q2Mrl96oMfgtsC2etLRvLw&callback=getLocations"></script>
