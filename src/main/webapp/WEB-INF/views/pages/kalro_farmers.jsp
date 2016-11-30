<%--
    Document   : kalro_farmers
    Created on : Nov 30, 2016, 11:44:11 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view farmers</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/partner_farmers.jsp"/>

    </jsp:attribute>
</kcep:kalro>
