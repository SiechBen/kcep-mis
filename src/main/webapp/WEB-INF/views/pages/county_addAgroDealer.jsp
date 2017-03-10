<%--
    Document   : county_addAgroDealer
    Created on : Oct 25, 2016, 8:49:54 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add farmer </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addAgroDealer.jsp"/>
        <div>
            <input type="hidden" id="county" value="${sessionScope.person.location.county.id}">
        </div>
        <div class="form-group">
            Sub-county
            <select id="sub-county" class="form-control" onchange="updateWards()">
                <c:forEach var="subCounty" items="${sessionScope.subCounties}" varStatus="index">
                    <option value="${subCounty.id}">${subCounty.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            Ward
            <select id="ward" class="form-control">
                <c:forEach var="ward" items="${sessionScope.wards}" varStatus="index">
                    <option value="${ward.id}">${ward.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="button" class="btn btn-outline btn-primary" onclick="addAgroDealer()">Save agro-dealer</button>
    </form>
</div>
</div>
</div>
</div>

</jsp:attribute>
</kcep:county>