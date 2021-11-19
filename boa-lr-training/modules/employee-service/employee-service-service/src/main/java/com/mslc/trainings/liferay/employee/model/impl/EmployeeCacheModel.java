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

package com.mslc.trainings.liferay.employee.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.mslc.trainings.liferay.employee.model.Employee;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class EmployeeCacheModel
	implements CacheModel<Employee>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeCacheModel)) {
			return false;
		}

		EmployeeCacheModel employeeCacheModel = (EmployeeCacheModel)object;

		if (employeeId == employeeCacheModel.employeeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, employeeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(11);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", employeeId=");
		sb.append(employeeId);
		sb.append(", name=");
		sb.append(name);
		sb.append(", workLocation=");
		sb.append(workLocation);
		sb.append(", createdBy=");
		sb.append(createdBy);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employee toEntityModel() {
		EmployeeImpl employeeImpl = new EmployeeImpl();

		if (uuid == null) {
			employeeImpl.setUuid("");
		}
		else {
			employeeImpl.setUuid(uuid);
		}

		employeeImpl.setEmployeeId(employeeId);

		if (name == null) {
			employeeImpl.setName("");
		}
		else {
			employeeImpl.setName(name);
		}

		if (workLocation == null) {
			employeeImpl.setWorkLocation("");
		}
		else {
			employeeImpl.setWorkLocation(workLocation);
		}

		if (createdBy == null) {
			employeeImpl.setCreatedBy("");
		}
		else {
			employeeImpl.setCreatedBy(createdBy);
		}

		employeeImpl.resetOriginalValues();

		return employeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		employeeId = objectInput.readLong();
		name = objectInput.readUTF();
		workLocation = objectInput.readUTF();
		createdBy = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(employeeId);

		if (name == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (workLocation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(workLocation);
		}

		if (createdBy == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(createdBy);
		}
	}

	public String uuid;
	public long employeeId;
	public String name;
	public String workLocation;
	public String createdBy;

}