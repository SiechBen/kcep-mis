<%--
    Document   : agmark_activity_names
    Created on : Feb 9, 2017, 6:19:56 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view activity names </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/activity_names.jsp"/>

    </jsp:attribute>
</kcep:agmark>