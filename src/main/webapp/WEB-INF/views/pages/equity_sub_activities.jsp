<%--
    Document   : equity_sub_activities
    Created on : Feb 9, 2017, 4:59:31 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:equity>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view sub-activities </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/sub_activities.jsp"/>

    </jsp:attribute>
</kcep:equity>