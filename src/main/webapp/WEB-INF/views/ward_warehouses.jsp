<%-- 
    Document   : ward_warehouses
    Created on : Jun 22, 2016, 5:11:02 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouses </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/warehouse.jsp"/>

    </jsp:attribute>
</kcep:ward>