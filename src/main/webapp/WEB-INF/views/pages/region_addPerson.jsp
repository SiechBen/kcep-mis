<%--
    Document   : region_addPerson
    Created on : Jun 25, 2016, 1:54:49 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:region>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add person </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addPerson.jsp"/>
        <div class="form-group" id="county-to-hide">
            County
            <select id="county" class="form-control" onchange="updateSubCounties()">
                <c:forEach var="county" items="${sessionScope.counties}" varStatus="index">
                    <option value="${county.id}">${county.name}</option>
                </c:forEach>
            </select>
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

</jsp:attribute>
</kcep:region>