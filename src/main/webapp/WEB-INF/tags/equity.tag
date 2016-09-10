<%-- 
    Document   : equity
    Created on : Jun 22, 2016, 3:37:03 PM
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
            <a onclick="loadAjaxWindow('people')"><i class="fa fa-edit fa-fw"></i> People </a>
        </li>
        <li>
            <a onclick="loadAjaxWindow('eVouchers')"><i class="fa fa-edit fa-fw"></i> E-vouchers </a>
        </li>
        <li>
            <a herf="192.168.1.6:8080/ODKAggregate/"><i class="fa fa-edit fa-fw"></i> Surveys </a>
        </li>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Equity Agent</h1>
                </div>
            </div>
            <div>         
                <jsp:invoke fragment="pagecontent" />
            </div>
        </div>
    </jsp:attribute>
</kcep:genericpage>
