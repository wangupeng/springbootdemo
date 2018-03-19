package com.cars.plat.sys.model;

import com.cars.plat.util.page.Page;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by wangyupeng on 2017/8/18.
 */
public class SysUser extends Page {
    @NotEmpty(message = "用户名不允许为空")
    private String userName;//主键,登陆账号，唯一
    @NotEmpty(message="密码不能为空")
    private String passWord;//密码
    private String oldPassWord;//旧密码
    private String roleId;//角色ID
    private String roleName;
    private String salt;//加密用的盐
    private String mobile;//手机号
    private String status;//状态,1:正常，2：锁定
    private String realName;//真实姓名
    private String createUser;//创建人
    private Date createDate;//创建时间
    private String lastModifiedUser;//上次修改人
    private Date lastModifiedDate;//上次修改时间
    private String innerFlag;//人员标识，1：本部门；2：外协
    private String companyCode;//公司代码
    private String companyName;//公司名称

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getOldPassWord() {
        return oldPassWord;
    }

    public void setOldPassWord(String oldPassWord) {
        this.oldPassWord = oldPassWord;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public String getInnerFlag() {
        return innerFlag;
    }

    public void setInnerFlag(String innerFlag) {
        this.innerFlag = innerFlag;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", oldPassWord='" + oldPassWord + '\'' +
                ", roleId='" + roleId + '\'' +
                ", roleName='" + roleName + '\'' +
                ", salt='" + salt + '\'' +
                ", mobile='" + mobile + '\'' +
                ", status='" + status + '\'' +
                ", realName='" + realName + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate=" + createDate +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                ", lastModifiedDate=" + lastModifiedDate +
                ", innerFlag='" + innerFlag + '\'' +
                ", companyCode='" + companyCode + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}
