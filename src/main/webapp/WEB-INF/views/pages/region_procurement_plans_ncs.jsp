<%--
    Document   : region_procurement_plans_ncs
    Created on : Oct 10, 2016, 1:03:29 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view procurement plans for ncs</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/procurement_plans_ncs.jsp"/>

    </jsp:attribute>
</kcep:region>