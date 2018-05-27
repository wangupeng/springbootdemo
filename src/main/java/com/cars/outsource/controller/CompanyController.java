package com.cars.outsource.controller;

import com.cars.outsource.service.CompanyService;
import com.cars.outsource.model.Company;
import com.cars.plat.common.base.BaseController;
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
@RequestMapping("/outsource/company")
public class CompanyController extends BaseController {

    @Autowired
    private CompanyService companyService;

    /**
     * 查询所有公司信息
     * @param company
     * @return
     */
    @RequestMapping
    public ModelAndView list(Company company){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(company.getPageNum(), company.getPageSize());
        //查询用户列表
        List<Company> listCompany = companyService.select(company);
        PageInfo<Company> pageInfo = new PageInfo<Company>(listCompany);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("outsource/company/listCompany");
        return mv;
    }

    /**
     * 添加公司信息
     * @param company
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public int add(Company company){
        company.setId(StringUtil.uuid());
        return companyService.insert(company);
    }

    /**
     * 修改公司信息
     * @param company
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public int update(Company company){
        return companyService.update(company);
    }

    /**
     * 删除公司信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public int delete(String id){
        return companyService.deleteByPrimaryKey(id);
    }

    /**
     * 根据ID获取公司信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getById")
    public Company getById(String id){
        return companyService.selectById(id);
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectAll")
    public List<Company> selectAll(){
        return companyService.selectAll();
    }

}
