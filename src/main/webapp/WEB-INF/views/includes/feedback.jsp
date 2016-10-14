<%--
    Document   : feedback
    Created on : Oct 13, 2016, 12:44:48 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<div class="row">
    <div class="col-lg-9">
        <div class="panel panel-default">
            <div class="panel-heading">
                Feedback and success stories
            </div>
            <div class="panel-body">
                <ul class="nav nav-tabs">
                    <li class="active">
                        <a href="#success-stories" data-toggle="tab">Success  stories</a>
                    </li>
                    <li>
                        <a href="#feedback-messages" data-toggle="tab">Feedback</a>
                    </li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="success-stories">
                        <h4>Success Stories</h4>
                        <label hidden id="add-label"></label>
                        <c:forEach var="successStory" items="${sessionScope.successStories}">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>${successStory.submitter.name} ${successStory.timePosted}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <textarea class="form-control">${successStory.message}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td><a href="download?filePath=${successStory.attachment}" target="_blank">Attached file(s): ${successStory.fileName}</a></td>
                                    </tr>
                                </tbody>
                            </table>
                        </c:forEach>
                    </div>
                    <div class="tab-pane fade" id="feedback-messages">
                        <h4>Feedback</h4>
                        <c:forEach var="feedback" items="${sessionScope.feedbackList}">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                    <tr>
                                        <th>${feedback.submitter.name} ${feedback.timePosted}</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>
                                            <textarea class="form-control">${feedback.message}</textarea>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>