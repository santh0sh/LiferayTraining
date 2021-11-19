<%@ page import="java.util.List"%>
<%@ page import="com.mslc.trainings.liferay.employee.model.Employee"%>
<%@ page import="java.util.Collections"%>
<%@ page import="javax.portlet.PortletURL" %>
<%@ include file="/init.jsp"%>

<p>
<h1>
	<liferay-ui:message key="employee.caption" />
</h1>
</p>
<portlet:renderURL var="addEmployeeURL">
	<portlet:param name="action" value="addEmployee" />
</portlet:renderURL>
<portlet:renderURL var="deleteEmployeeURL">
	<portlet:param name="action" value="deleteEmployee" />
</portlet:renderURL>
<portlet:renderURL var="findEmployeeURL">
	<portlet:param name="action" value="findEmployee" />
</portlet:renderURL>
<p>
User Id :- <%=user.getUserId()%>

<%

user.getRoles().forEach(x -> {
    System.out.println(x.getName());
});
%>
</p>
<%
    if(themeDisplay.isSignedIn()){
%>
<div>
    <div><a href="<%=addEmployeeURL%>" class="btn btn-primary" >Add Employee</a></div>
</div>
<%}else{%>

<p>Sign In to Add.</p>
<%}%>
<hr/>
<%
	List<Employee> employeeList = (List) request.getAttribute("employeeList");
    if (employeeList == null) {
		employeeList = Collections.emptyList();
	}
	System.out.println(employeeList);
	System.out.println(employeeList.size());

	PortletURL editURL = renderResponse.createRenderURL();
%>
<div>
<%
	if (employeeList != null && !employeeList.isEmpty()) {
%>
	<div class="row">
		<div class="col"><b>EmployeeId</b></div>
		<div class="col"><b>Name</b></div>
		<div class="col"><b>Work Location</b></div>
		<div class="col"><b>Action</b></div>
	</div>
<%}%>

    <% for (Employee e : employeeList) { %>
    <div class="row">
        <div class="col"><%=e.getEmployeeId()%></div>
        <div class="col"><%=e.getName()%></div>
        <div class="col"><%=e.getWorkLocation()%></div>
        <div class="col">
            <%
                editURL.setParameter("employeeId", String.valueOf(e.getEmployeeId()));
                editURL.setParameter("action", "editEmployee");
            %>
            <a href="<%=editURL%>">Edit</a>
        </div>
    </div>
    <% } %>
</div>
