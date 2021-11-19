/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.mslc.trainings.liferay.employee.service.impl;

import com.liferay.blogs.model.BlogsEntry;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.transaction.Transactional;
import com.mslc.trainings.liferay.employee.model.Employee;
import com.mslc.trainings.liferay.employee.service.base.EmployeeLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * The implementation of the employee local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.mslc.trainings.liferay.employee.service.EmployeeLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EmployeeLocalServiceBaseImpl
 */
@Component(
	property = "model.class.name=com.mslc.trainings.liferay.employee.model.Employee",
	service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.mslc.trainings.liferay.employee.service.EmployeeLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.mslc.trainings.liferay.employee.service.EmployeeLocalServiceUtil</code>.
	 */


	private static final Log log = LogFactoryUtil.getLog(EmployeeLocalServiceImpl.class);


	public List<Employee> getEmployeesByName (String name){
		return  employeePersistence.findByName(name);
	}
	@Transactional
	public Employee createEmployee (long id, String name, String workLocation, ServiceContext serviceContext){
		Employee emp = employeePersistence.create(id);
		emp.setName(name);
		emp.setWorkLocation(workLocation);
		emp.setCreatedBy(String.valueOf(serviceContext.getThemeDisplay().getUserId()));
		employeePersistence.update(emp);
		//BlogsEntry blogsEntry = blogsEntryLocalService.createBlogsEntry(1000);

		/**
		 * BELOW IS HOW YOU CAN CREATE AN LIFERAY USER.
		 * tHROWING ERROR AS OF 11/18/2021 12.00 PM
		 */
		try{
			User user = userLocalService.createUser (0);
			user.setScreenName(name + " _ Screen");
			user.setFirstName(name);
			user.setLastName(name);
			user.setEmailAddress(name+"@hotmail.com");
			user.setPassword("12345");
			userLocalService.updateUser(user);
		}catch (Exception e){
			e.printStackTrace();
		}


		log.info("User Created with detail :: "+name );
		return emp;
	}

}