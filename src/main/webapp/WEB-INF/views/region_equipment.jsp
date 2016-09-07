<%-- 
    Document   : region_equipment
    Created on : Jul 19, 2016, 3:27:43 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouse equipment </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/equipment.jsp"/>

    </jsp:attribute>
</kcep:region>