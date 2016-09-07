<%-- 
    Document   : warehouse_equipment
    Created on : Jun 22, 2016, 4:51:41 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:genericpage>
    <jsp:attribute name="title"> KCEP-MIS - view warehouse equipment </jsp:attribute>
    <jsp:attribute name="menuitems"></jsp:attribute>
    <jsp:attribute name="content">

        <jsp:include page="includes/equipment.jsp"/>

    </jsp:attribute>
</kcep:genericpage>