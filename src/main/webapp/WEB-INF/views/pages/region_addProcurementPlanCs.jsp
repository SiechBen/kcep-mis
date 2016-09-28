<%-- 
    Document   : region_addProcurementPlanCs
    Created on : Jul 2, 2016, 3:36:27 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add procurement plan-cs </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addProcurementPlanCs.jsp"/>

    </jsp:attribute>
</kcep:region>