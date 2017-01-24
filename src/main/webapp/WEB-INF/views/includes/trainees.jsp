<%--
    Document   : trainees
    Created on : Sep 6, 2016, 11:21:11 AM
    Author     : qortez
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Below is the list of trainees for the training that started on <em><fmt:formatDate pattern="yy-MMM-dd" value="${sessionScope.training.startDate}"/></em><br>
                The topic of the training was <b>${sessionScope.training.topic.topic}</b>.
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <table class="table table-striped table-bordered table-hover data-table">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Name</th>
                                <!--<th>Person role</th>-->
                                <th>Gender</th>
                                <th>National ID</th>
                                <th>Age</th>
                                <th>Business name</th>
                                <th>County</th>
                                <th>Sub-county</th>
                                <th>Ward</th>
                                <th>Phone number</th>
                                <th>Email address</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="11"> List of trainees</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="trainee" items="${sessionScope.trainees}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${trainee.person.name}</td>
                                    <!--<td>${trainee.person.personRole}</td>-->
                                    <td>${trainee.person.sex.sex}</td>
                                    <td>${trainee.person.nationalId}</td>
                                    <td>${trainee.person.age}</td>
                                    <td>${trainee.person.businessName}</td>
                                    <td>${trainee.person.location.county.name}</td>
                                    <td>${trainee.person.location.subCounty.name}</td>
                                    <td>${trainee.person.location.ward.name}</td>
                                    <td>${trainee.person.contact.phone}</td>
                                    <td>${trainee.person.contact.email}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <jsp:include page="people_count.jsp"/>
                </div>
            </div>
        </div>
    </div>
</div>