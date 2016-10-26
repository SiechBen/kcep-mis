<%--
    Document   : region_sub_activity_names
    Created on : Oct 26, 2016, 3:53:03 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view sub-activity names </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/sub_activity_names.jsp"/>

    </jsp:attribute>
</kcep:region>