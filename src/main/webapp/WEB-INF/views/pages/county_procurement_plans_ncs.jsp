<%--
    Document   : county_procurement_plans_ncs
    Created on : Oct 10, 2016, 1:02:33 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view procurement plans for ncs </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/procurement_plans_ncs.jsp"/>

    </jsp:attribute>
</kcep:county>