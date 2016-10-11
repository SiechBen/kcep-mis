<%--
    Document   : sub_county_addTraining
    Created on : Jun 22, 2016, 4:26:28 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add training </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addTraining.jsp"/>

    </jsp:attribute>
</kcep:sub_county>