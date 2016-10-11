<%--
    Document   : region_procurement_plans_goods
    Created on : Oct 10, 2016, 1:03:19 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view procurement plans for goods </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/procurement_plans_goods.jsp"/>

    </jsp:attribute>
</kcep:region>