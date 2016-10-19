<%--
    Document   : county_eVouchers
    Created on : Oct 18, 2016, 11:15:47 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view e-vouchers </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/eVouchers.jsp"/>

    </jsp:attribute>
</kcep:county>