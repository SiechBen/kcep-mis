<%-- 
    Document   : county_training
    Created on : Jun 25, 2016, 3:31:57 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view training </jsp:attribute>
    <jsp:attribute name="pagecontent">

    <jsp:include page="includes/training.jsp"/>


    </jsp:attribute>
</kcep:county>