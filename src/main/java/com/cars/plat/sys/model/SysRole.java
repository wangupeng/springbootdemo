package com.cars.plat.sys.model;

import com.cars.plat.util.page.Page;

import java.util.Date;

/**
 * Created by wangyupeng on 2017/8/18.
 */
public class SysRole extends Page {
    private String roleCode;//角色代码
    private String roleName;//角色名称
    private String description;//描述
    private String createUser;//创建人
    private Date createDate;//创建时间
    private String lastModifiedUser;//上次修改人
    private Date lastModifiedDate;//上次修改时间

    @Override
    public String toString() {
        return "Role{" +
                ", roleCode='" + roleCode + '\'' +
                ", roleName='" + roleName + '\'' +
                ", description='" + description + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
