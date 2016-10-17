<%--
    Document   : sub_county_addWarehouse
    Created on : Jun 23, 2016, 7:47:53 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add warehouse</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addWarehouse.jsp"/>
        <div class="form-group">
            <input type="hidden" id="county" value="${sessionScope.person.location.county.id}">
            <input type="hidden" id="sub-county" value="${sessionScope.person.location.subCounty.id}">
        </div>
        <div class="form-group">
            Ward
            <select id="ward" class="form-control">
                <c:forEach var="ward" items="${sessionScope.wards}" varStatus="index">
                    <option value="${ward.id}">${ward.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="button" class="btn btn-outline btn-primary" onclick="addWarehouse()">Save warehouse</button>
    </form>
</div>
</div>
</div>
</div>

</jsp:attribute>
</kcep:sub_county>