<%--
    Document   : agmark_eVouchers
    Created on : Oct 19, 2016, 8:30:17 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agmark>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view e-vouchers </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/eVouchers.jsp"/>

    </jsp:attribute>
</kcep:agmark>