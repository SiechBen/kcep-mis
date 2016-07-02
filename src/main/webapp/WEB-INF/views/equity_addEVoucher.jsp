<%-- 
    Document   : equity_addEVoucher
    Created on : Jun 22, 2016, 3:56:12 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:equity>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add e-voucher </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <div class="row">
            <div class="col-lg-6">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        E-voucher details
                    </div>
                    <div class="panel-body">
                        <form id="e-voucher-form" role="form" action="doAddEVoucher" method="POST" enctype="multipart/form-data">
                            <div class="form-group">
                                Amount
                                <input id="e-voucher-amount" name="amount" class="form-control">
                            </div>
                            <div class="form-group">
                                Input type
                                <select id="e-voucher-input-type" name="input-type" class="form-control">
                                    <c:forEach var="inputType" items="${sessionScope.inputTypes}" varStatus="index"> 
                                        <option value="${inputType.id}">${inputType.type}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                            <div class="form-group">
                                Person
                                <select id="e-voucher-person" name="person" class="form-control">
                                    <c:forEach var="person" items="${sessionScope.people}" varStatus="index"> 
                                        <option value="${person.id}">${person.name}</option>
                                    </c:forEach>
                                </select> 
                            </div>
                            <div class="form-group">
                                Date redeemed
                                <input id="date-redeemed" name="date-redeemed" class="form-control datefield">
                            </div>
                            <div class="form-group">
                                Scanned pages/ photos from the inputs logging book
                                <input type="file" id="inputs-loogbook-page" name="inputs-loogbook-page" class="form-control">
                            </div>
                            <input type="submit" class="btn btn-outline btn-primary" value="Save e-voucher">
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
</kcep:equity>

