<%@ page import="com.mslc.trainings.liferay.employee.model.Employee"%>
<%@ include file="/init.jsp" %>
<p>

    <portlet:renderURL var="employeeActionURL">
	    <portlet:param name="action" value="employeeActionPage" />
    </portlet:renderURL>
     <div>
        <a href="<%=employeeActionURL%>">Back</a>
    </div>
	<h2>Add Employee Here !</h2>
</p>

<%
    Employee employee = (Employee) request.getAttribute("employee");
%>

<portlet:actionURL var="submitEmployeeForm"/>

<form method="POST" action="<%=submitEmployeeForm%>">
    <div class="form-group">
        <label for="employeeId">Id</label>

        <input type="text" id="employeeId" class="form-control" name="<portlet:namespace/>employeeId"
               placeholder="Employee Id" value="<%=(employee == null ? "" : employee.getEmployeeId())  %>">

    </div>


    <div class="form-group">
        <label for="employeeId">Name</label>
        <input type="text" id="name" class="form-control" name="<portlet:namespace/>name" placeholder="Name" value="<%=(employee == null ? "" : employee.getName())  %>">


    </div>
    <div class="form-group">
        <label for="workLocation">Work Location</label>
        <input type="text" id="workLocation" class="form-control" name="<portlet:namespace/>workLocation" placeholder="Work Location" value="<%=(employee == null ? "" : employee.getWorkLocation())  %>">


    </div>

    <div class="row">
        <div class="col">
            <input type="submit" class="btn btn-primary" value="Submit">
        </div>
    </div>

</form>



