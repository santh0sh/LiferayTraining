package com.mslc.training.liferay.employee.rest.application;

import com.mslc.training.liferay.employee.rest.application.pojo.EmployeeVO;
import com.mslc.trainings.liferay.employee.model.Employee;
import com.mslc.trainings.liferay.employee.service.EmployeeLocalServiceUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.*;
import javax.ws.rs.core.Application;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Administrator
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/employees",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Employees.Rest",
		"auth.verifier.guest.allowed=false"
	},
	service = Application.class
)
public class EmployeeRestApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Produces("application/json")
	public List<EmployeeVO> handleGetEmployees() {
		List<Employee> employeesList = EmployeeLocalServiceUtil.getEmployees(0, 100);
		String resultJson = "";
		/*Gson gson = new Gson();
		String resultJson = gson.toJson(employees);
		System.out.println(resultJson);
		return resultJson;*/

		return employeesList .stream().map(x -> {
							EmployeeVO vo = new EmployeeVO();
							vo.setEmployeeId(x.getEmployeeId());
							vo.setEmployeeName(x.getName());
							vo.setWorkLocation(x.getWorkLocation());
							return vo;
						}).collect(Collectors.toList());
	}



	@GET
	@Path("/morning/{name}")
	@Produces("text/plain")
	public String morning(
		@PathParam("name") String name,
		@QueryParam("drink") String drink) {

		String greeting = "Vannakam da Mapala " + name;

		if (drink != null) {
			greeting += ". " + drink + " Kudikaya?";
		}

		return greeting;
	}

}