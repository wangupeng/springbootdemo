package com.cars.server.model;

import com.cars.plat.util.page.Page;

import javax.persistence.Column;
import javax.persistence.Table;

/**
 * Created by wangyupeng on 2018/5/3 22:13
 */
@Table(name = "pmis_project")
public class Project extends Page {
    @Column(name = "project_code")
    private String projectCode;//项目代码
    @Column(name = "project_name")
    private String projectName;//项目名称
    @Column(name = "short_name")
    private String shortName;//项目简称
    @Column(name = "start_date")
    private String startDate;//项目开始日期
    @Column(name = "end_date")
    private String endDate;//项目结束日期
    @Column(name = "description")
    private String description;//项目简介
    @Column(name = "version")
    private String version;//当前版本
    @Column(name = "version_date")
    private String versionDate;//版本上线时间
    @Column(name = "status")
    private String status;//应用状态
    @Column(name = "svn")
    private String svn;//svn地址
    @Column(name = "fzr")
    private String fzr;//项目负责人
    @Column(name = "phone")
    private String phone;//项目负责人联系电话
    @Column(name = "create_user")
    private String createUser;//创建用户
    @Column(name = "create_date")
    private String createDate;//创建时间
    @Column(name = "update_user")
    private String updateUser;//更新用户
    @Column(name = "update_date")
    private String updateDate;//更新时间
    @Column(name = "remark")
    private String remark;//备注
    @Column(name = "px")
    private Integer px;//排序

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

    public String getVersionDate() {
        return versionDate;
    }

    public void setVersionDate(String versionDate) {
        this.versionDate = versionDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSvn() {
        return svn;
    }

    public void setSvn(String svn) {
        this.svn = svn;
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

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectCode='" + projectCode + '\'' +
                ", projectName='" + projectName + '\'' +
                ", shortName='" + shortName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", description='" + description + '\'' +
                ", version='" + version + '\'' +
                ", versionDate='" + versionDate + '\'' +
                ", status='" + status + '\'' +
                ", svn='" + svn + '\'' +
                ", fzr='" + fzr + '\'' +
                ", phone='" + phone + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateUser='" + updateUser + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
