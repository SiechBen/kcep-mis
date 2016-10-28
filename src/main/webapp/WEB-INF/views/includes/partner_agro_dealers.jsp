<%--
    Document   : partner_agro_dealers
    Created on : Oct 28, 2016, 9:58:26 AM
    Author     : siech
--%>
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
                    <table id="partner-agro-dealers-table" class="table table-striped table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Name</th>
                                <th>Gender</th>
                                <th>Age</th>
                                <th>National id</th>
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
                                <td colspan="11">List of agro-dealers</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="person" items="${sessionScope.agroDealers}" varStatus="index">
                                <tr>
                                    <td>${index.count}</td>
                                    <td>${person.name}</td>
                                    <td>${person.sex.sex}</td>
                                    <td>${person.age}</td>
                                    <td>${person.nationalId}</td>
                                    <td>${person.businessName}</td>
                                    <td>${person.location.county.name}</td>
                                    <td>${person.location.subCounty.name}</td>
                                    <td>${person.location.ward.name}</td>
                                    <td>${person.contact.phone}</td>
                                    <td>${person.contact.email}</td>
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

<div class="row dialog" id="search-person-dialog">
    <div class="col-lg-12">
        <div class="panel-default">
            <div class="panel-body">
                <form role="form">
                    <div class="form-group">
                        National id
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