package com.cars.server.service;

import com.cars.plat.sys.model.SysUser;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import com.cars.server.dao.WeblogicDao;
import com.cars.server.model.Weblogic;
import com.cars.server.model.WeblogicApp;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by wangyupeng on 2018/4/28 22:58
 */
@Component
public class WeblogicService {

    @Autowired
    private WeblogicDao weblogicDao;

    /**
     * 查询所有weblogic服务器信息
     * @param weblogic
     * @return
     */
    public List<Weblogic> listWeblogicServer(Weblogic weblogic){
        return weblogicDao.listWeblogicServer(weblogic);
    }

    /**
     * 增加weblogic服务器信息
     * @param weblogic
     * @return
     */
    public int addWeblogicServer(Weblogic weblogic){
        //获取当前登录用户
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        weblogic.setCreateUser(user.getUserName());
        weblogic.setCreateDate(DateUtil.getSystemTime());
        weblogic.setId(StringUtil.uuid());
        return weblogicDao.addWeblogicServer(weblogic);
    }

    /**
     * 更新weblogic服务器信息
     * @param weblogic
     * @return
     */
    public int updateWeblogicServer(Weblogic weblogic){
        return weblogicDao.updateWeblogicServer(weblogic);
    }

    /**
     * 删除weblogic服务器信息
     * @param id
     * @return
     */
    public int deleteWeblogicServer(String id){
        return weblogicDao.deleteWeblogicServer(id);
    }

    /**
     * 按照ID查询weblogic服务器信息
     * @param id
     * @return
     */
    public Weblogic getWeblogicById(String id){
        return weblogicDao.getWeblogicById(id);
    }


    /**
     * 查询weblogic应用
     * @param weblogicId
     * @return
     */
    public List<WeblogicApp> listWeblogicApp(String weblogicId){
        return weblogicDao.listWeblogicApp(weblogicId);
    }

    /**
     * 新增weblogic应用
     * @param weblogic
     * @return
     */
    public int addWeblogicApp(WeblogicApp weblogic){
        //获取当前登录用户
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        weblogic.setCreateUser(user.getUserName());
        weblogic.setCreateDate(DateUtil.getSystemTime());
        return weblogicDao.addWeblogicApp(weblogic);
    }

    /**
     * 更新weblogic应用
     * @param weblogic
     * @return
     */
    public int updateWeblogicApp(WeblogicApp weblogic){
        return weblogicDao.updateWeblogicApp(weblogic);
    }

    /**
     * 删除weblogic应用
     * @param weblogicApp
     * @return
     */
    public int deleteWeblogicApp(WeblogicApp weblogicApp){
        return weblogicDao.deleteWeblogicApp(weblogicApp);
    }

    /**
     * 按weblogic服务器ID和应用代码获取weblogic应用
     * @param weblogicApp
     * @return
     */
    public WeblogicApp getWeblogicAppByAppCode(WeblogicApp weblogicApp){
        return weblogicDao.getWeblogicAppByAppCode(weblogicApp);
    }

}
