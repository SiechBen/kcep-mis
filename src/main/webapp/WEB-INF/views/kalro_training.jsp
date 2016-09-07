<%-- 
    Document   : kalro_training
    Created on : Jun 22, 2016, 4:58:03 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:kalro>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view training </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/training.jsp"/>

    </jsp:attribute>
</kcep:kalro>