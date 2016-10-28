<%--
    Document   : agmark_agro_dealers
    Created on : Oct 19, 2016, 8:29:46 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view agro-dealers</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/partner_agro_dealers.jsp"/>

    </jsp:attribute>
</kcep:agmark>