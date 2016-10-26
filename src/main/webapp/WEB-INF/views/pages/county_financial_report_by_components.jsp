<%--
    Document   : county_financial_report_by_components
    Created on : Oct 17, 2016, 1:21:29 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - financial reports by components </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/financial_report_by_components.jsp"/>

    </jsp:attribute>
</kcep:county>