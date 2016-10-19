<%--
    Document   : county_agro_dealers
    Created on : Oct 18, 2016, 11:14:24 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view agro-dealers</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/agro_dealers.jsp"/>

    </jsp:attribute>
</kcep:county>