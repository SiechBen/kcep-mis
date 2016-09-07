<%-- 
    Document   : farmer
    Created on : Jun 27, 2016, 7:10:54 AM
    Author     : siech
--%>

<%@tag description="put the tag description here" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<%-- The list of fragments:--%>
<%@attribute name="pagecontent" fragment="true"%>
<%@attribute name="pagetitle" required="true" %>

<kcep:genericpage>
    <jsp:attribute name="title"> ${pagetitle} </jsp:attribute>
    <jsp:attribute name="menuitems">     
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Farmer</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div>         
                <jsp:invoke fragment="pagecontent" />
            </div>
        </div>
    </jsp:attribute>
</kcep:genericpage>