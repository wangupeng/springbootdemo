package com.cars.outsource.model;

import com.cars.plat.util.page.Page;

import javax.persistence.Id;
import javax.persistence.OrderBy;
import javax.persistence.Table;

/**
 * Created by wangyupeng on 2018/5/7 21:30
 */
@Table(name = "pmis_person")
public class Person extends Page {
    @Id
    private String id;//人员ID
    private String companyId;
    private String name;//姓名
    private String sex;//性别 1:男，2:女
    private String age;//年龄
    private String mobile;//手机号
    private String dutyCode;//职务,1:项目经理，2：开发人员，3：测试人员
    private String remark;
    @OrderBy
    private Integer px;//排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDutyCode() {
        return dutyCode;
    }

    public void setDutyCode(String dutyCode) {
        this.dutyCode = dutyCode;
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
        return "Person{" +
                "id='" + id + '\'' +
                ", companyId='" + companyId + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", age='" + age + '\'' +
                ", mobile='" + mobile + '\'' +
                ", dutyCode='" + dutyCode + '\'' +
                ", remark='" + remark + '\'' +
                ", px=" + px +
                '}';
    }
}
