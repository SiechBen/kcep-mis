<%--
    Document   : partner_farmers
    Created on : Oct 28, 2016, 9:58:12 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                Farmers
            </div>
            <div class="panel-body">
                <div class="dataTable_wrapper">
                    <label hidden id="add-label">addFarmer</label>
                    <table id="partner-farmers-table" class="table table-bordered table-hover">
                        <thead>
                            <tr>
                                <th>&nbsp;</th>
                                <th>Name</th>
                                <th>Gender</th>
                                <th>Age</th>
                                <th>National ID</th>
                                <!--                                <th>Farmer group</th>
                                                                <th>Farmer sub-group</th>-->
                                <th>County</th>
                                <th>Sub-county</th>
                                <th>Ward</th>
                                <th>Phone number</th>
                            </tr>
                        </thead>
                        <tfoot>
                            <tr>
                                <td colspan="9">List of farmers</td>
                            </tr>
                        </tfoot>
                        <tbody>
                            <c:forEach var="person" items="${sessionScope.farmers}" varStatus="index">
                                <tr class="farmer-row">
                                    <td onclick="loadFarmWindow(${person.id})">${index.count}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.sex.sex}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.age}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.nationalId}</td>
<!--                                    <td onclick="loadFarmWindow(${person.id})">${person.farmerGroup.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.farmerSubGroup.name}</td>-->
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.county.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.subCounty.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.location.ward.name}</td>
                                    <td onclick="loadFarmWindow(${person.id})">${person.contact.phone}</td>
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