package com.cars.outsource.controller;

import com.cars.outsource.model.Report;
import com.cars.outsource.service.ReportService;
import com.cars.plat.common.base.BaseController;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangyupeng on 2018/4/28 22:15
 */
@Controller
@RequestMapping("/outsource/report")
public class ReportController extends BaseController {

    @Autowired
    private ReportService reportService;

    /**
     * 查询所有
     * @param report
     * @return
     */
    @RequestMapping
    public ModelAndView list(Report report){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(report.getPageNum(), report.getPageSize());
        List<Report> listReport = reportService.select(report);
        PageInfo<Report> pageInfo = new PageInfo<Report>(listReport);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("outSource/report/listReport");
        return mv;
    }

    /**
     * 添加
     * @param report
     * @return
     */
    @ResponseBody
    @RequestMapping("/add")
    public int add(Report report,@SessionAttribute SysUser userSession){
        report.setId(StringUtil.uuid());
        report.setCreateUser(userSession.getUserName());
        report.setCreateDate(DateUtil.getSystemDate());
        return reportService.insert(report);
    }

    /**
     * 修改
     * @param report
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public int update(Report report){
        return reportService.update(report);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/delete")
    public int delete(String id){
        return reportService.deleteByPrimaryKey(id);
    }

    /**
     * 根据ID获取
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getById")
    public Report getById(String id){
        return reportService.selectById(id);
    }

}
