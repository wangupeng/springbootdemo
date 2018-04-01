package com.cars.plat.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wangyupeng on 2018/3/31 11:25
 */
@RestController
@RequestMapping("/test")
public class BaseService {
    @Autowired
    CountryMapper countryMapper;

    @RequestMapping
    public List<Country> getCountries() {
        List<Country> countries = countryMapper.selectAll();
        return countries;
    }


}
