<%--
    Document   : sub_county_equipment
    Created on : Jul 19, 2016, 3:26:35 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouse equipment </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/equipment.jsp"/>

    </jsp:attribute>
</kcep:sub_county>