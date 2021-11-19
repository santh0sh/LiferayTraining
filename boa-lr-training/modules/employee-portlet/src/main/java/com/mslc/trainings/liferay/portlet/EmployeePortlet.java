package com.mslc.trainings.liferay.portlet;




import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.mslc.trainings.liferay.constants.EmployeePortletKeys;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import javax.portlet.*;

import com.mslc.trainings.liferay.employee.exception.NoSuchEmployeeException;
import com.mslc.trainings.liferay.employee.model.Employee;
import com.mslc.trainings.liferay.employee.service.EmployeeLocalServiceUtil;

import org.osgi.service.component.annotations.Component;


import java.io.IOException;
import java.util.List;

/**
 * @author Administrator
 *
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=Employee",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + EmployeePortletKeys.EMPLOYEE,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.version=3.0"
	},
	service = Portlet.class
)
public class EmployeePortlet extends MVCPortlet {

	private static final Log log = LogFactoryUtil.getLog(EmployeePortlet.class);

	@Override
	public void render(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {

		List<Employee> employeeListByName = EmployeeLocalServiceUtil.getEmployeesByName("Sandy");
		employeeListByName.forEach(System.out::println);

		RenderParameters renderParameters = renderRequest.getRenderParameters();
		String action = renderParameters.getValue("action");
		log.info("Render Executed with action"+action);

		if("addEmployee".equals(action)){
			PortletRequestDispatcher dispatcher = renderRequest.getPortletContext().getRequestDispatcher("/employee-form.jsp");
			dispatcher.forward(renderRequest,renderResponse);
		}else if ("editEmployee".equalsIgnoreCase(action)) {
			toEditEmployee(renderRequest, renderResponse);
		}
		else if("deleteEmployee".equals(action)){
			PortletRequestDispatcher dispatcher = renderRequest.getPortletContext().getRequestDispatcher("/deleteEmployee.jsp");
			dispatcher.forward(renderRequest,renderResponse);
		}else if("findEmployee".equals(action)){
			PortletRequestDispatcher dispatcher = renderRequest.getPortletContext().getRequestDispatcher("/findEmployee.jsp");
			dispatcher.forward(renderRequest,renderResponse);
		}
		else if("employeeActionPage".equals(action)){
			PortletRequestDispatcher dispatcher = renderRequest.getPortletContext().getRequestDispatcher("/view.jsp");
			dispatcher.forward(renderRequest,renderResponse);
		}
		else{
			List<Employee> employeeList = EmployeeLocalServiceUtil.getEmployees(0, 100);
			//log.info("Employees List" + employeeList.size());
			renderRequest.setAttribute("employeeList",employeeList);
			super.render(renderRequest, renderResponse);
		}

	}

	@Override
	public void processAction(ActionRequest actionRequest, ActionResponse actionResponse) throws IOException, PortletException {

		ActionParameters actionParameters = actionRequest.getActionParameters();

		String id = actionParameters.getValue("employeeId");
		String name = actionParameters.getValue("name");
		String workLocation = actionParameters.getValue("workLocation");

		log.info("Process action is executed : " + id + " -- " + name + " -- " + workLocation);
		Employee employee = null;

		try {
			employee = EmployeeLocalServiceUtil.getEmployee(Long.valueOf(id));
		} catch (NoSuchEmployeeException e) {
			log.info ("Employee Detail not found on DB");
		} catch (PortalException e) {

			e.printStackTrace();
		}

		if (employee == null) {
			log.info("*******Creating Employee");
			ServiceContext ctx = null;
			try{
				ctx = ServiceContextFactory.getInstance(actionRequest);
			}catch (Exception e){
				e.printStackTrace();
			}
			//employee = EmployeeLocalServiceUtil.createEmployee(Long.valueOf(id));
			employee = EmployeeLocalServiceUtil.createEmployee(Long.valueOf(id),name,workLocation,ctx);
		}
		/**
		 * BELOW LOGIC WILL GET USER DETAILS ON THE PORTLET ITSELF AND THE ABOVE ONE IS PASSING THE SERVICE CONTEXT TO
		 * SERVICE LAYER.
		 */
		/*ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY) ;
		employee.setCreatedBy(String.valueOf(themeDisplay.getUserId()));
		employee.setName(name);
		employee.setWorkLocation(workLocation);
		EmployeeLocalServiceUtil.updateEmployee(employee);*/
		log.info("Persisted Employee to DB");

		super.processAction(actionRequest, actionResponse);
	}

	private void toEditEmployee(RenderRequest renderRequest, RenderResponse renderResponse) {

		long empId = Long.valueOf(renderRequest.getRenderParameters().getValue("employeeId"));
		try {
			Employee emp = EmployeeLocalServiceUtil.getEmployee(empId);
			renderRequest.setAttribute("employee", emp);
		} catch (PortalException e) {
			e.printStackTrace();
		}

		PortletRequestDispatcher dispatcher = renderRequest
				.getPortletContext()
				.getRequestDispatcher("/employee-form.jsp");
		try {
			dispatcher.forward(renderRequest, renderResponse);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}