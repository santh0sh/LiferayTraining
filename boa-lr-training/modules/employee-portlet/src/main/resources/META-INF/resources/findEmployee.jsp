<%@ include file="/init.jsp" %>

<p>

<portlet:renderURL var="employeeActionURL">
	    <portlet:param name="action" value="employeeActionPage" />
    </portlet:renderURL>
    <div>
        <a href="<%=employeeActionURL%>">Back</a>
    </div>
	<h2>Look for Employee Here !</h2>

</p>