package com.cars.plat.common.base;

import javax.persistence.Id;

/**
 * Created by wangyupeng on 2018/4/1 15:00
 */
public class Country {
    @Id
    private Integer id;
    private String  countryname;
    private String  countrycode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryname() {
        return countryname;
    }

    public void setCountryname(String countryname) {
        this.countryname = countryname;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }
}
