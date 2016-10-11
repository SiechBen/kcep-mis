<%--
    Document   : ward_addPerson
    Created on : Jun 22, 2016, 4:04:23 PM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add person </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addPerson.jsp"/>
        <div class="form-group">
            <input type="hidden" id="county" class="form-control" value="${person.location.county.id}">
        </div>
        <div class="form-group">
            <input type="hidden" id="sub-county" class="form-control" value="${person.location.subCounty.id}">
        </div>
        <div class="form-group">
            <input type="hidden" id="ward" class="form-control" value="${person.location.ward.id}">
        </div>
        <button type="button" class="btn btn-outline btn-primary" onclick="addPerson()">Save person</button>
    </form>
</div>
</div>
</div>
</div>

</jsp:attribute>
</kcep:ward>
