package com.cars.server.controller;

import com.cars.plat.common.base.BaseController;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import com.cars.server.model.Weblogic;
import com.cars.server.model.WeblogicApp;
import com.cars.server.service.WeblogicAppService;
import com.cars.server.service.WeblogicService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authc.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangyupeng on 2018/4/28 22:15
 */
@Controller
@RequestMapping("/server/weblogic")
public class WeblogicController extends BaseController {

    @Autowired
    private WeblogicService weblogicService;

    @Autowired
    private WeblogicAppService weblogicAppService;

    /**
     * 查询所有weblogic服务器信息
     * @param weblogic
     * @return
     */
    @RequestMapping
    public ModelAndView listWeblogic(Weblogic weblogic){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(weblogic.getPageNum(), weblogic.getPageSize());
        //查询用户列表
//        List<Weblogic> listWeblogic = weblogicService.select(weblogic);
        Example example = new Example(Weblogic.class);
        example.createCriteria().andLike("weblogicName","%"+StringUtil.dealNull(weblogic.getWeblogicName())+""+"%");
        List<Weblogic> listWeblogic = weblogicService.selectByExample(example);
        PageInfo<Weblogic> pageInfo = new PageInfo<Weblogic>(listWeblogic);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("weblogic",weblogic);
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
    public int addWeblogicServer(Weblogic weblogic,@SessionAttribute SysUser userSession){
        weblogic.setCreateUser(userSession.getUserName());
        weblogic.setCreateDate(DateUtil.getSystemTime());
        weblogic.setId(StringUtil.uuid());
        return weblogicService.insert(weblogic);
    }

    /**
     * 修改weblogic服务器信息
     * @param weblogic
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateWeblogic")
    public int updateWeblogicServer(Weblogic weblogic){
        return weblogicService.update(weblogic);
    }

    /**
     * 删除weblogic服务器信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteWeblogic")
    public int deleteWeblogicServer(String id){
        return weblogicService.deleteByPrimaryKey(id);
    }

    /**
     * 根据ID获取weblogic服务器信息
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWeblogicById")
    public Weblogic getWeblogicById(String id){
        return weblogicService.selectById(id);
    }


    /**
     * 查询weblogic应用
     * @param weblogicApp
     * @return
     */
    @RequestMapping("/listWeblogicApp")
    public ModelAndView listWeblogicApp(WeblogicApp weblogicApp){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(weblogicApp.getPageNum(), weblogicApp.getPageSize());
        //查询用户列表
        List<WeblogicApp> listWeblogic = weblogicAppService.select(weblogicApp);
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
        return weblogicAppService.insert(weblogicApp);
    }

    /**
     * 修改应用
     * @param weblogicApp
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateWeblogicApp")
    public int updateWeblogicApp(WeblogicApp weblogicApp){
        return weblogicAppService.update(weblogicApp);
    }

    /**
     * 删除应用
     * @param weblogicApp
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteWeblogicApp")
    public int deleteWeblogicApp(WeblogicApp weblogicApp){
        return weblogicAppService.delete(weblogicApp);
    }

    /**
     * 根据按weblogic服务器ID和应用代码获取weblogic应用
     * @param weblogicApp
     * @return
     */
    @ResponseBody
    @RequestMapping("/getWeblogicAppById")
    public WeblogicApp getWeblogicAppByAppCode(WeblogicApp weblogicApp){
        return weblogicAppService.selectById(weblogicApp);
    }

    @RequestMapping("/checkExist")
    @ResponseBody
    public String checkExist(WeblogicApp weblogicApp){
        WeblogicApp weblogicApp2 = weblogicAppService.selectById(weblogicApp);
        String existFlag = "false";
        if(weblogicApp2==null){
            existFlag ="true";
        }
        return existFlag;
    }
}
