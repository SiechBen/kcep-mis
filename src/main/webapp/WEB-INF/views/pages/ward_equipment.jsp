<%-- 
    Document   : ward_equipment
    Created on : Jun 22, 2016, 4:51:14 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view warehouse equipment </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/equipment.jsp"/>

    </jsp:attribute>
</kcep:ward>