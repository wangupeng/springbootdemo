package com.cars.plat.sys.model;

/**
 * Created by wangyupeng on 2017/12/20.
 */
public class SysLog {
    private String logId;
    private String userName;
    private String operaIp;
    private String operaDate;
    private String operaUrl;
    private String methodName;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOperaIp() {
        return operaIp;
    }

    public void setOperaIp(String operaIp) {
        this.operaIp = operaIp;
    }

    public String getOperaDate() {
        return operaDate;
    }

    public void setOperaDate(String operaDate) {
        this.operaDate = operaDate;
    }

    public String getOperaUrl() {
        return operaUrl;
    }

    public void setOperaUrl(String operaUrl) {
        this.operaUrl = operaUrl;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

}
