<%--
    Document   : county_view_agro_dealers_on_map
    Created on : Jan 25, 2017, 7:55:16 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view agro-dealers on map</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/view_agro_dealers_on_map.jsp"/>

    </jsp:attribute>
</kcep:county>