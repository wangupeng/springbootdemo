package com.cars.person.controller;

import com.cars.person.model.Company;
import com.cars.person.service.CompanyService;
import com.cars.plat.common.base.BaseController;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangyupeng on 2018/4/28 22:15
 */
@Controller
@RequestMapping("/company")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;

    /**
     * 查询所有公司信息
     * @param company
     * @return
     */
    @RequestMapping
    public ModelAndView listCompany(Company company){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(company.getPageIndex(), company.getPageSize());
        //查询用户列表
        List<Company> listCompany = companyService.select(company);
        PageInfo<Company> pageInfo = new PageInfo<Company>(listCompany);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("person/company/listCompany");
        return mv;
    }

    /**
     * 添加公司信息
     * @param company
     * @return
     */
    @ResponseBody
    @RequestMapping("/addCompany")
    public int addCompanyServer(Company company){
        company.setId(StringUtil.uuid());
        return companyService.insert(company);
    }

    /**
     * 修改公司信息
     * @param company
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateCompany")
    public int updateCompanyServer(Company company){
        return companyService.update(company);
    }

    /**
     * 删除公司信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteCompany")
    public int deleteCompanyServer(String id){
        return companyService.deleteByPrimaryKey(id);
    }

    /**
     * 根据ID获取公司信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getCompanyById")
    public Company getCompanyById(String id){
        return companyService.selectById(id);
    }

}
