<%-- 
    Document   : farmer
    Created on : Jun 27, 2016, 7:07:03 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:farmer>
    <jsp:attribute name="pagetitle"> KCEP-MIS - feedback</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-9">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        Enter your feedback, suggestions, comments and views about the programme below. 
                        This will be sent to the officials at the KCEP programme such as the Ward Extension Officer.
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <form id="feedback-form" action="saveFeedback" method="POST">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>Feedback message</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <textarea id="feedback" name="feedback" class="form-control"></textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="submit" value="Submit" class="btn btn-default">
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:farmer>