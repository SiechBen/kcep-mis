<%--
    Document   : ward_addWarehouse
    Created on : Jun 22, 2016, 4:43:09 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add warehouse</jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addWarehouse.jsp"/>
        <div class="form-group">
            <input type="hidden" id="county" value="${sessionScope.person.location.county.id}">
            <input type="hidden" id="sub-county" value="${sessionScope.person.location.subCounty.id}">
            <input type="hidden" id="ward" value="${sessionScope.person.location.ward.id}">
        </div>
        <button type="button" class="btn btn-outline btn-primary" onclick="addWarehouse()">Save warehouse</button>
    </form>
</div>
</div>
</div>
</div>

</jsp:attribute>
</kcep:ward>
