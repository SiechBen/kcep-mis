<%-- 
    Document   : county_addWarehouse
    Created on : Jun 25, 2016, 12:35:11 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view purchase </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addWarehouse.jsp"/>

    </jsp:attribute>
</kcep:county>