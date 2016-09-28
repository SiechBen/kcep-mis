<%-- 
    Document   : equity_addEVoucher
    Created on : Jun 22, 2016, 3:56:12 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:equity>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add e-voucher </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addEVoucher.jsp"/> 

    </jsp:attribute>
</kcep:equity>

