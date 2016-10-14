<%--
    Document   : agro_dealer_feedback
    Created on : Jun 27, 2016, 9:30:59 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agro_dealer>
    <jsp:attribute name="pagetitle"> KCEP-MIS - farmer feedback </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/feedback.jsp"/>

    </jsp:attribute>
</kcep:agro_dealer>

