<%--
    Document   : warehouse
    Created on : Jun 23, 2016, 9:46:58 AM
    Author     : siech
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<%-- The list of fragments:--%>
<%@attribute name="pagecontent" fragment="true"%>
<%@attribute name="pagetitle" required="true" %>

<kcep:genericpage>

    <jsp:attribute name="title"> ${pagetitle} </jsp:attribute>

    <jsp:attribute name="menuitems">
        <jsp:include page="../../tags/includes/equipment_item.jsp"/>
    </jsp:attribute>

    <jsp:attribute name="content">
        <div id="page-wrapper">
            <div>
                <jsp:invoke fragment="pagecontent" />
            </div>
        </div>
    </jsp:attribute>

</kcep:genericpage>