package com.cars.server.controller;

import com.cars.plat.sys.model.SysUser;
import com.cars.server.model.Weblogic;
import com.cars.server.model.WeblogicApp;
import com.cars.server.service.WeblogicService;
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
@RequestMapping("/weblogic")
public class WeblogicController {

    @Autowired
    private WeblogicService weblogicService;

    /**
     * 查询所有weblogic服务器信息
     * @param weblogic
     * @return
     */
    @RequestMapping
    public ModelAndView listWeblogic(Weblogic weblogic){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(weblogic.getPageIndex(), weblogic.getPageSize());
        //查询用户列表
        List<Weblogic> listWeblogic = weblogicService.listWeblogicServer(weblogic);
        PageInfo<Weblogic> pageInfo = new PageInfo<Weblogic>(listWeblogic);
        mv.addObject("pageInfo",pageInfo);

        mv.setViewName("server/weblogic/listWeblogic");
        return mv;
    }

    /**
     * 添加weblogic服务器信息
     * @param weblogic
     * @return
     */
    @ResponseBody
    @RequestMapping("/addWeblogic")
    public int addWeblogicServer(Weblogic weblogic){
        return weblogicService.addWeblogicServer(weblogic);
    }

    /**
     * 修改weblogic服务器信息
     * @param weblogic
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateWeblogic")
    public int updateWeblogicServer(Weblogic weblogic){
        return weblogicService.updateWeblogicServer(weblogic);
    }

    /**
     * 删除weblogic服务器信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteWeblogic")
    public int deleteWeblogicServer(String id){
        return weblogicService.deleteWeblogicServer(id);
    }

    /**
     * 根据ID获取weblogic服务器信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWeblogicById")
    public Weblogic getWeblogicById(String id){
        return weblogicService.getWeblogicById(id);
    }


    /**
     * 查询weblogic应用
     * @param weblogicApp
     * @return
     */
    @RequestMapping("/listWeblogicApp")
    public ModelAndView listWeblogic(WeblogicApp weblogicApp){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(weblogicApp.getPageIndex(), weblogicApp.getPageSize());
        //查询用户列表
        List<WeblogicApp> listWeblogic = weblogicService.listWeblogicApp(weblogicApp.getWeblogicId());
        PageInfo<WeblogicApp> pageInfo = new PageInfo<WeblogicApp>(listWeblogic);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("weblogicApp",weblogicApp);
        mv.setViewName("server/weblogic/listWeblogicApp");
        return mv;
    }

    /**
     * 添加应用
     * @param weblogicApp
     * @return
     */
    @ResponseBody
    @RequestMapping("/addWeblogicApp")
    public int addWeblogicApp(WeblogicApp weblogicApp){
        return weblogicService.addWeblogicApp(weblogicApp);
    }

    /**
     * 修改应用
     * @param weblogicApp
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateWeblogicApp")
    public int updateWeblogicApp(WeblogicApp weblogicApp){
        return weblogicService.updateWeblogicApp(weblogicApp);
    }

    /**
     * 删除应用
     * @param weblogicApp
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteWeblogicApp")
    public int deleteWeblogicApp(WeblogicApp weblogicApp){
        return weblogicService.deleteWeblogicApp(weblogicApp);
    }

    /**
     * 根据按weblogic服务器ID和应用代码获取weblogic应用
     * @param weblogicApp
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWeblogicAppById")
    public WeblogicApp getWeblogicAppByAppCode(WeblogicApp weblogicApp){
        return weblogicService.getWeblogicAppByAppCode(weblogicApp);
    }

    @RequestMapping("/checkExist")
    @ResponseBody
    public String checkExist(WeblogicApp weblogicApp){
        WeblogicApp weblogicApp2 = weblogicService.getWeblogicAppByAppCode(weblogicApp);
        String existFlag = "false";
        if(weblogicApp2==null){
            existFlag ="true";
        }
        return existFlag;
    }
}
