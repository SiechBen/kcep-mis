<%-- 
    Document   : head_procurements
    Created on : Jun 22, 2016, 3:34:29 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view equipment procurements </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/procurements.jsp"/>

    </jsp:attribute>
</kcep:head>
