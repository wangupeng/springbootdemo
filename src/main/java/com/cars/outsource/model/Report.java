package com.cars.outsource.model;

import com.cars.plat.util.page.Page;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Created by wangyupeng on 2018/5/7 23:18
 */
@Table(name = "pmis_weekreport")
public class Report extends Page {
    @Id
    private String id;
    @OrderBy
    private String startDate;//开始日期
    @OrderBy
    private String endDate;//结束日期
    private String content;//本周工作内容
    private String contentNext;//下周工作内容
    private String fj;//附件
    private String status;//0:保存，1-提交
    private String viewUsers;//可查看用户
    private String createUser;//创建用户
    private String createDate;//创建时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentNext() {
        return contentNext;
    }

    public void setContentNext(String contentNext) {
        this.contentNext = contentNext;
    }

    public String getFj() {
        return fj;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getViewUsers() {
        return viewUsers;
    }

    public void setViewUsers(String viewUsers) {
        this.viewUsers = viewUsers;
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
        return "WeekReport{" +
                "id='" + id + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", content='" + content + '\'' +
                ", contentNext='" + contentNext + '\'' +
                ", fj='" + fj + '\'' +
                ", status='" + status + '\'' +
                ", viewUsers='" + viewUsers + '\'' +
                ", createUser='" + createUser + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
