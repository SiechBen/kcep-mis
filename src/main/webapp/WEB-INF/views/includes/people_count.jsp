<%--
    Document   : people_count
    Created on : Oct 24, 2016, 12:53:36 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="floating-table">
    <table id="people-count-table" class="table table-bordered table-hover table-responsive">
        <thead>
            <tr>
                <th> Count by: </th>
                <th colspan="6">
                    <select id="counter" onchange="updateCounts()">
                        <c:forEach var="countOption" items="${sessionScope.countOptions}">
                            <option value="${countOption.id}">${countOption.personRole}</option>
                        </c:forEach>
                    </select>
                </th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td colspan="3"> <strong>Female</strong> </td>
                <td colspan="3"> <strong>Male</strong> </td>
                <td rowspan="2"> <strong>Total</strong> </td>
            </tr>
            <tr>
                <td> <strong>18 to 35 years old</strong> </td>
                <td> <strong>Above 35 years old</strong> </td>
                <td> <strong>Female Total</strong> </td>
                <td> <strong>18 to 35 years old</strong> </td>
                <td> <strong>Above 35 years old</strong> </td>
                <td> <strong>Male Total</strong> </td>
            </tr>
            <tr id="people-summary">
                <td id="female-youth"> ${sessionScope.femaleYouth} </td>
                <td id="female-elderly"> ${sessionScope.femaleElderly} </td>
                <td id="female-total"> ${sessionScope.femaleTotal} </td>
                <td id="male-youth"> ${sessionScope.maleYouth} </td>
                <td id="male-elderly"> ${sessionScope.maleElderly} </td>
                <td id="male-total"> ${sessionScope.maleTotal} </td>
                <td id="people-total"> ${sessionScope.totalPeople} </td>
        <input type="hidden" id="female-youth-hidden" value="${sessionScope.femaleYouth}">
        <input type="hidden" id="female-elderly-hidden" value="${sessionScope.femaleElderly}">
        <input type="hidden" id="female-total-hidden" value="${sessionScope.femaleTotal}">
        <input type="hidden" id="male-youth-hidden" value="${sessionScope.maleYouth}">
        <input type="hidden" id="male-elderly-hidden" value="${sessionScope.maleElderly}">
        <input type="hidden" id="male-total-hidden" value="${sessionScope.maleTotal}">
        <input type="hidden" id="people-total-hidden" value="${sessionScope.totalPeople}">
        </tr>
        <tr>
            <td colspan="7" class="divider"></td>
        </tr>
        </tbody>
    </table>

</div>