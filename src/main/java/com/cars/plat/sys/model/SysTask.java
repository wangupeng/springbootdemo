package com.cars.plat.sys.model;

import com.cars.plat.util.page.Page;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by wangyupeng on 2018/4/4 22:47
 */
public class SysTask extends Page implements Serializable {
    /**任务ID*/
    private String jobId;
    /**任务名*/
    private String jobName;
    /**任务组*/
    private String jobGroup;
    /**任务包*/
    private String jobClass;
    /**任务描述*/
    private String jobDescribe;
    /**cron表达式*/
    private String cronExpression;
    /**任务状态*/
    private String status;

    /**创建时间*/
    private Date createDate;
    /**创建人ID*/
    private String createUser;
    /**修改时间*/
    private Date updateDate;
    /**修改人ID*/
    private String updateUser;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getJobDescribe() {
        return jobDescribe;
    }

    public void setJobDescribe(String jobDescribe) {
        this.jobDescribe = jobDescribe;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass;
    }
}
