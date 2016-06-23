<%-- 
    Document   : region
    Created on : Jun 22, 2016, 3:37:33 PM
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
            <a onclick="loadAjaxWindow('programmes')"><i class="fa fa-edit fa-fw"></i> Programmes </a>
        </li>
    </li>
    </jsp:attribute>
    <jsp:attribute name="content">
        <div>         
            <jsp:invoke fragment="pagecontent" />
        </div>
    </jsp:attribute>
</kcep:genericpage>
