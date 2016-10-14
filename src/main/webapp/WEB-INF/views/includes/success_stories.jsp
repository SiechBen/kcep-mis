<%--
    Document   : success_stories
    Created on : Oct 11, 2016, 4:11:39 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<div class="row">
    <div class="col-lg-9">
        <div class="panel panel-default">
            <div class="panel-heading">
                Enter your success story below. Explain what you observed or learnt in the field.<br>
                Provide an attachment such as a photo to support the story.
            </div>
            <div class="panel-body">
                <form id="success-story-form" action="saveSuccessStory" method="POST" enctype="multipart/form-data">
                    <table class="table table-bordered table-hover">
                        <tbody>
                            <tr>
                                <th>Success story</th>
                            </tr>
                            <tr>
                                <td>
                                    <textarea name="success-story" class="form-control"></textarea>
                                </td>
                            </tr>
                            <tr>
                                <th class="divider"></th>
                            </tr>
                            <tr>
                                <th>Attachment(s) / media file(s)</th>
                            </tr>
                            <tr>
                                <td>
                                    <input type="file" name="media-file" multiple>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit" value="Submit" class="btn btn-outline btn-primary">
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>