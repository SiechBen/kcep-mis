<%--
    Document   : kalro_farm
    Created on : Jul 22, 2016, 10:28:05 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - farm </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/reducted_farm.jsp"/>

    </jsp:attribute>
</kcep:kalro>