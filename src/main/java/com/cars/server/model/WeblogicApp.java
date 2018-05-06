package com.cars.server.model;

import com.cars.plat.util.page.Page;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by wangyupeng on 2018/4/28 22:07
 */
@Table(name = "pmis_weblogic_app")
public class WeblogicApp extends Page {
    @Id
    private String weblogicId;
    @Id
    private String appCode;
    @Transient
    private String weblogicName;
    private String appName;
    private String appStatus;//1:生产应用，2：测试应用，3：废弃待删除
    private String fzr;//负责人
    @OrderBy
    private Integer px;
    private String description;//描述
    private String createUser;
    private String createDate;

    public String getWeblogicId() {
        return weblogicId;
    }

    public void setWeblogicId(String weblogicId) {
        this.weblogicId = weblogicId;
    }

    public String getWeblogicName() {
        return weblogicName;
    }

    public void setWeblogicName(String weblogicName) {
        this.weblogicName = weblogicName;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPx() {
        return px;
    }

    public void setPx(Integer px) {
        this.px = px;
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

    @Override
    public String toString() {
        return "WeblogicApp{" +
                "appCode='" + appCode + '\'' +
                ", appName='" + appName + '\'' +
                ", appStatus='" + appStatus + '\'' +
                ", fzr='" + fzr + '\'' +
                ", px=" + px +
                ", description='" + description + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
