<%--
    Document   : region_warehouses
    Created on : Nov 2, 2016, 9:19:15 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouses </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/warehouses.jsp" />
        <jsp:include page="../includes/warehouse_count.jsp" />

    </jsp:attribute>
</kcep:region>