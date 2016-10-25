<%--
    Document   : ward_addFarmer
    Created on : Oct 25, 2016, 6:58:19 AM
    Author     : siech
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="kcep" tagdir="/WEB-INF/tags/" %>

<kcep:ward>
    <jsp:attribute name="pagetitle"> KCEP-MIS - add farmer </jsp:attribute>
    <jsp:attribute name="pagecontent">

        <jsp:include page="../includes/addFarmer.jsp"/>
        <div class="form-group">
            <input type="hidden" id="county" class="form-control" value="${person.location.county.id}">
        </div>
        <div class="form-group">
            <input type="hidden" id="sub-county" class="form-control" value="${person.location.subCounty.id}">
        </div>
        <div class="form-group">
            <input type="hidden" id="ward" class="form-control" value="${person.location.ward.id}">
        </div>
        <button type="button" class="btn btn-outline btn-primary" onclick="addFarmer()">Save farmer</button>
    </form>
</div>
</div>
</div>
</div>

</jsp:attribute>
</kcep:ward>
