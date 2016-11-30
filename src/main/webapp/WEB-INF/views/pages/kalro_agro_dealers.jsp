<%--
    Document   : kalro_agro_dealers
    Created on : Nov 30, 2016, 11:45:53 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view agro-dealers</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/partner_agro_dealers.jsp"/>

    </jsp:attribute>
</kcep:kalro>
