package com.cars.person.model;

import com.cars.plat.util.page.Page;

import javax.persistence.*;

/**
 * Created by wangyupeng on 2018/4/28 21:55
 */
@Table(name = "pmis_company")
public class Company extends Page {
    @Id
    private String id;
    @OrderBy
    private String companyName;//公司名称
    private String companyIntroduce;//公司简介
    private String companyNature;//公司性质:国有企业-1、集体企业-2、 股份合作制企业-3、 有限责任公司-4、 股份有限公司-5、 联营企业-6、 私营企业-7、 个体户-8、 合伙企业-9
    private String companyAddress;//公司地址
    private String establishedDate;//成立时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyIntroduce() {
        return companyIntroduce;
    }

    public void setCompanyIntroduce(String companyIntroduce) {
        this.companyIntroduce = companyIntroduce;
    }

    public String getCompanyNature() {
        return companyNature;
    }

    public void setCompanyNature(String companyNature) {
        this.companyNature = companyNature;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getEstablishedDate() {
        return establishedDate;
    }

    public void setEstablishedDate(String establishedDate) {
        this.establishedDate = establishedDate;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id='" + id + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyIntroduce='" + companyIntroduce + '\'' +
                ", companyNature='" + companyNature + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", establishedDate='" + establishedDate + '\'' +
                '}';
    }
}
