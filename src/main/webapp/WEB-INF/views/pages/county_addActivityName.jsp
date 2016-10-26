<%--
    Document   : county_addActivityName
    Created on : Oct 26, 2016, 10:22:26 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/"%>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add activity name </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addActivityName.jsp"/>

    </jsp:attribute>
</kcep:county>