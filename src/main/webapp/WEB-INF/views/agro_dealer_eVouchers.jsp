<%-- 
    Document   : agro_dealer_eVouchers
    Created on : Jun 22, 2016, 4:40:26 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agro_dealer>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view e-vouchers </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="includes/eVouchers.jsp"/>

    </jsp:attribute>
</kcep:agro_dealer>