package com.cars.server.model;

import com.cars.plat.util.page.Page;

import java.util.List;

/**
 * Created by wangyupeng on 2018/4/28 21:55
 */
public class Weblogic extends Page {
    private String id;
    private String weblogicName;
    private String weblogicUrl;
    private String weblogicUsername;
    private String weblogicPassword;
    private String fzr;//负责人
    private String description;//描述
    private List<WeblogicApp> list;
    private Integer px;
    private String createUser;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWeblogicName() {
        return weblogicName;
    }

    public void setWeblogicName(String weblogicName) {
        this.weblogicName = weblogicName;
    }

    public String getWeblogicUrl() {
        return weblogicUrl;
    }

    public void setWeblogicUrl(String weblogicUrl) {
        this.weblogicUrl = weblogicUrl;
    }

    public String getWeblogicUsername() {
        return weblogicUsername;
    }

    public void setWeblogicUsername(String weblogicUsername) {
        this.weblogicUsername = weblogicUsername;
    }

    public String getWeblogicPassword() {
        return weblogicPassword;
    }

    public void setWeblogicPassword(String weblogicPassword) {
        this.weblogicPassword = weblogicPassword;
    }

    public List<WeblogicApp> getList() {
        return list;
    }

    public void setList(List<WeblogicApp> list) {
        this.list = list;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Weblogic{" +
                "id='" + id + '\'' +
                ", weblogicName='" + weblogicName + '\'' +
                ", weblogicUrl='" + weblogicUrl + '\'' +
                ", weblogicUsername='" + weblogicUsername + '\'' +
                ", weblogicPassword='" + weblogicPassword + '\'' +
                ", fzr='" + fzr + '\'' +
                ", description='" + description + '\'' +
                ", list=" + list +
                ", px=" + px +
                ", createUser='" + createUser + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
