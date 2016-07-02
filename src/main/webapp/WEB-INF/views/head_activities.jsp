<%-- 
    Document   : head_activities
    Created on : Jun 28, 2016, 9:04:59 PM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:head>
    <jsp:attribute name="pagetitle"> KCEP-MIS - view activities </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <!-- /.row -->
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        List of activites
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover data-table">
                                <thead>
                                    <tr>
                                        <th><button type="button" class="btn btn-outline btn-primary" onclick="loadAjaxWindow('addActivity')">Add</button></th>
                                        <th>Activity description</th>
                                    </tr>
                                </thead>
                                <tfoot>
                                    <tr>
                                        <td colspan="8"> List of e-vouchers </td>
                                    </tr>
                                </tfoot>
                                <tbody>
                                    <c:forEach var="activity" items="${sessionScope.activities}" varStatus="index">
                                        <tr <c:if test="${index.count % 2 == 0}">class="odd"</c:if>>
                                            <td>${index.count}</td>
                                            <td>${activity.description}</td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

    </jsp:attribute>
</kcep:head>