package com.cars.plat.sys.controller;

import com.cars.plat.sys.model.SysLog;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.sys.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    /**
     * 日志列表
     * @param sysLog
     * @return
     */
    @RequestMapping
    public ModelAndView listLog(SysLog sysLog){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(sysLog.getPageNum(), sysLog.getPageSize());
        //查询用户列表
        List<SysLog> listLog = sysLogService.listLog(sysLog);
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(listLog);
        mv.addObject("pageInfo",pageInfo);

        mv.addObject("sysLog",sysLog);
        mv.setViewName("plat/sys/log/listLog");
        return mv;
    }
}
