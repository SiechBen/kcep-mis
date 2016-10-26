<%--
    Document   : county_addFinancialYear
    Created on : Oct 26, 2016, 10:22:41 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/"%>

<kcep:county>

    <jsp:attribute name="pagetitle"> KCEP-MIS - add financial year</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addFinancialYear.jsp"/>

    </jsp:attribute>

</kcep:county>
