<%--
    Document   : county_activity_names
    Created on : Jul 22, 2016, 11:26:41 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view activity names </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/activity_names.jsp"/>

    </jsp:attribute>
</kcep:county>