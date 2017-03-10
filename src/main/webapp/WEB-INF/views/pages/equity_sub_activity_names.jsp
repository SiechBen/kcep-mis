<%--
    Document   : equity_sub_activity_names
    Created on : Feb 9, 2017, 6:34:20 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:equity>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view sub-activity names </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/sub_activity_names.jsp"/>

    </jsp:attribute>
</kcep:equity>
