<%--
    Document   : agro_dealers
    Created on : Oct 5, 2016, 11:23:20 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Agro-dealers
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addAgroDealer</label>
                    <table id="agro-dealers-table" class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th style="display: none">&nbsp;</th>
                                <th>&nbsp;</th>
                                <th>Name</th>
                                <th>Gender</th>
                                <th>Age</th>
                                <th>National ID</th>
                                <th>Business name</th>
                                <th>County</th>
                                <th>Sub-county</th>
                                <th>Ward</th>
                                <th>Latitude, Longitude</th>
                                <th>Phone number</th>
                                <th>Email address</th>
                                <th>&nbsp;</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="13">List of agro-dealers</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="person" items="${sessionScope.agroDealers}" varStatus="index">
                                <tr>
                                    <td style="display: none">${person.id}</td>
                                    <td>${index.count}</td>
                                    <td>${person.name}</td>
                                    <td>${person.sex.sex}</td>
                                    <td>${person.age}</td>
                                    <td>${person.nationalId}</td>
                                    <td>${person.businessName}</td>
                                    <td>${person.location.county.name}</td>
                                    <td>${person.location.subCounty.name}</td>
                                    <td>${person.location.ward.name}</td>
                                    <td>${person.location.latitude}, ${person.location.longitude}</td>
                                    <td>${person.contact.phone}</td>
                                    <td>${person.contact.email}</td>
                                    <td><button onclick="editAgrodealer('${person.id}', '${person.name}', '${person.sex.id}',
                                                    '${person.nationalId}', '${person.yearOfBirth}', '${person.businessName}',
                                                    '${person.location.id}', '${person.location.county.id}', '${person.location.subCounty.id}',
                                                    '${person.location.ward.id}', '${person.location.latitude}', '${person.location.longitude}',
                                                    '${person.contact.id}', '${person.contact.phone}', '${person.contact.email}'
                                                    )"><span class="glyphicon glyphicon-pencil"></span></button></td>
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

<div class="dialog" id="person-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        Name
                        <input id="person-name" name="person-name" class="form-control">
                    </div>
                    <div class="form-group">
                        Gender
                        <select id="sex" name="sex" class="form-control">
                            <c:forEach var="sex" items="${applicationScope.sexes}" varStatus="index">
                                <option value="${sex.id}">${sex.sex}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <input type="hidden" id="person-role" value="2">
                    </div>
                    <div class="form-group">
                        National ID
                        <input id="national-id" name="national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Year of birth
                        <select id="year-of-birth" name="year-of-birth" class="form-control">
                            <option disabled>Select year</option>
                        </select>
                    </div>
                    <div class="form-group">
                        Business Name
                        <input id="business-name" name="person-business-name" class="form-control">
                    </div>
                    <div class="form-group">
                        County
                        <select id="county" name="person-county" class="form-control" onchange="updateSubCounties()">
                            <c:forEach var="county" items="${sessionScope.counties}" varStatus="index">
                                <option value="${county.id}">${county.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Sub-county
                        <select id="sub-county" name="person-sub-county" class="form-control" onchange="updateWards()">
                            <c:forEach var="subCounty" items="${sessionScope.subCounties}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Ward
                        <select id="ward" name="person-ward" class="form-control">
                            <c:forEach var="subCounty" items="${sessionScope.wards}" varStatus="index">
                                <option value="${subCounty.id}">${subCounty.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        Latitude
                        <input type="number" name="person-latitude" class="form-control" id="latitude" min="-90" max="90">
                    </div>
                    <div class="form-group">
                        Longitude
                        <input type="number" name="person-longitude" class="form-control" id="longitude" min="-180" max="180">
                    </div>
                    <div class="form-group">
                        Phone
                        <input id="phone" name="person-phone" class="form-control">
                    </div>
                    <div class="form-group">
                        Email
                        <input id="email" name="person-email" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<div class="row dialog" id="search-person-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        National ID
                        <input id="search-national-id" name="search-national-id" class="form-control">
                    </div>
                    <div class="form-group">
                        Name
                        <input id="search-name" name="search-name" class="form-control">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
