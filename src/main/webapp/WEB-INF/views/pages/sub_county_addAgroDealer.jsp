<%--
    Document   : sub_county_addAgroDealer
    Created on : Oct 25, 2016, 8:57:26 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:sub_county>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add agro-dealer </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addAgroDealer.jsp"/>
        <div class="form-group">
            <input type="hidden" id="county" class="form-control" value="${person.location.county.id}">
        </div>
        <div class="form-group">
            <input type="hidden" id="sub-county" class="form-control" value="${person.location.subCounty.id}">
        </div>
        <div class="form-group">
            Ward
            <select id="ward" class="form-control">
                <c:forEach var="ward" items="${sessionScope.wards}" varStatus="index">
                    <option value="${ward.id}">${ward.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save agro-dealer</button>
    </form>
</div>
</div>
</div>
</div>

</jsp:attribute>
</kcep:sub_county>