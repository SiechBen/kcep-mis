<%-- 
    Document   : agro_dealer_feedback
    Created on : Jun 27, 2016, 9:30:59 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:agro_dealer>
    <jsp:attribute name="pagetitle"> KCEP-MIS - farmer feedback </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-9">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Feedback from farmers in your sub-county.
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <c:forEach var="feedback" items="${sessionScope.feedbackList}">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>${feedback.farmer.name} ${feedback.timePosted}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <textarea id="feedback" name="feedback" class="form-control">${feedback.message}</textarea>
                                        </td>
                                    </tr>
                                    <!--                                        <tr>
                                                                                <td>
                                                                                    <Button type="submit" class="btn btn-default">Reply</Button>
                                                                                </td>
                                                                            </tr>-->
                                </tbody>
                            </table>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:agro_dealer>
