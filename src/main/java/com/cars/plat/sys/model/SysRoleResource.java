package com.cars.plat.sys.model;

/**
 * Created by wangyupeng on 2017/8/18.
 */
public class SysRoleResource {
    private String roleId;
    private String resourceId;

    @Override
    public String toString() {
        return "RoleResource{" +
                "roleId='" + roleId + '\'' +
                ", resourceId='" + resourceId + '\'' +
                '}';
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }
}
