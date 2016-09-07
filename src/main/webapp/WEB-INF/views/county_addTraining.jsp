<%-- 
    Document   : county_addTraining
    Created on : Jun 25, 2016, 4:10:02 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add training </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/addTraining.jsp"/>
    </jsp:attribute>
</kcep:county>