package com.cars.server.model;

import com.cars.plat.util.page.Page;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Created by wangyupeng on 2018/5/3 22:13
 */
@Table(name = "pmis_project")
public class Project extends Page {
    @Id
    private String projectId;
    @OrderBy
    private String projectCode;//项目代码
    private String projectName;//项目全称
    private String shortName;//项目简称
    private String startDate;//项目开始日期
    private String endDate;//项目结束日期
    private String description;//项目简介
    private String version;//当前版本
    private String status;//项目状态
    private String fzr;//项目负责人
    private String phone;//项目负责人联系电话
    private String svn;//svn地址
    private String createUser;//创建用户
    private String createDate;//创建时间
    private String updateUser;//更新用户
    private String updateDate;//更新时间
    private String remark;//备注

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSvn() {
        return svn;
    }

    public void setSvn(String svn) {
        this.svn = svn;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId='" + projectId + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", status='" + status + '\'' +
                ", fzr='" + fzr + '\'' +
                ", phone='" + phone + '\'' +
                ", svn='" + svn + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
