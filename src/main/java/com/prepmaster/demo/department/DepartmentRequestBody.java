package com.prepmaster.demo.department;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DepartmentRequestBody {
    private Department department;
    private Long adminId;
    private Long departmentHeadId;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public Long getDepartmentHeadId() {
        return departmentHeadId;
    }

    public void setDepartmentHeadId(Long departmentHeadId) {
        this.departmentHeadId = departmentHeadId;
    }
}
