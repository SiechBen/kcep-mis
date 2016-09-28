<%-- 
    Document   : region_procurement_plans
    Created on : Jul 2, 2016, 9:35:04 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view procurement plans </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/procurement_plans.jsp"/>

    </jsp:attribute>
</kcep:region>