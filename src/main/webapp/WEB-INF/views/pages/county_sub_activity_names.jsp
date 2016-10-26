<%--
    Document   : county_sub_activity_names
    Created on : Jul 15, 2016, 8:05:02 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view sub-activity names </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/sub_activity_names.jsp"/>

    </jsp:attribute>
</kcep:county>