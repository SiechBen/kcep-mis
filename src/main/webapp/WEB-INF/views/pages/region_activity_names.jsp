<%--
    Document   : region_activity_names
    Created on : Oct 26, 2016, 3:51:31 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view activity names </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/activity_names.jsp"/>

    </jsp:attribute>
</kcep:region>