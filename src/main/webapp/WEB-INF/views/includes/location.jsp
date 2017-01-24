<%--
    Document   : location
    Created on : Jan 24, 2017, 8:44:05 AM
    Author     : siech
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div>
    <input type="hidden" id="county" value="${sessionScope.person.location.county.id}">
</div>
<div class="form-group" id="sub-county-to-hide">
    Sub-county
    <select id="sub-county" class="form-control" onchange="updateWards()">
        <c:forEach var="subCounty" items="${sessionScope.subCounties}" varStatus="index">
            <option value="${subCounty.id}">${subCounty.name}</option>
        </c:forEach>
    </select>
</div>
<div class="form-group" id="ward-to-hide">
    Ward
    <select id="ward" class="form-control">
        <c:forEach var="ward" items="${sessionScope.wards}" varStatus="index">
            <option value="${ward.id}">${ward.name}</option>
        </c:forEach>
    </select>
</div>
<button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save person</button>
</form>
</div>
</div>
</div>
</div>