<%--
    Document   : county_procurement_plans_goods
    Created on : Oct 10, 2016, 1:02:26 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view procurement plans for goods </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/procurement_plans_goods.jsp"/>

    </jsp:attribute>
</kcep:county>