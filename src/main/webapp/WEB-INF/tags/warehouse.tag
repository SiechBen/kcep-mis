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
        <li>
            <a onclick="loadAjaxWindow('equipment')"><i class="fa fa-edit fa-fw"></i> Equipment </a>
        </li>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div>         
            <jsp:invoke fragment="pagecontent" />
        </div>
    </jsp:attribute>
</kcep:genericpage>