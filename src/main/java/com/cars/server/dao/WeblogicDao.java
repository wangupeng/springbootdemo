package com.cars.server.dao;

import com.cars.server.model.Weblogic;
import com.cars.server.model.WeblogicApp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangyupeng on 2018/4/28 22:55
 */
@Mapper
public interface WeblogicDao {
    /**
     * 查询所有weblogic服务器信息
     * @param weblogic
     * @return
     */
    List<Weblogic> listWeblogicServer(Weblogic weblogic);

    /**
     * 增加weblogic服务器信息
     * @param weblogic
     * @return
     */
    int addWeblogicServer(Weblogic weblogic);

    /**
     * 更新weblogic服务器信息
     * @param weblogic
     * @return
     */
    int updateWeblogicServer(Weblogic weblogic);

    /**
     * 删除weblogic服务器信息
     * @param id
     * @return
     */
    int deleteWeblogicServer(String id);

    /**
     * 按照ID查询weblogic服务器信息
     * @param id
     * @return
     */
    Weblogic getWeblogicById(String id);

    /**********************************************************/
    /**
     * 查询weblogic应用
     * @param weblogicId
     * @return
     */
    List<WeblogicApp> listWeblogicApp(String weblogicId);

    /**
     * 新增weblogic应用
     * @param weblogicApp
     * @return
     */
    int addWeblogicApp(WeblogicApp weblogicApp);

    /**
     * 更新weblogic应用
     * @param weblogicApp
     * @return
     */
    int updateWeblogicApp(WeblogicApp weblogicApp);

    /**
     * 删除weblogic应用
     * @param weblogicApp
     * @return
     */
    int deleteWeblogicApp(WeblogicApp weblogicApp);

    /**
     * 按weblogic服务器ID和应用代码获取weblogic应用
     * @param weblogicApp
     * @return
     */
    WeblogicApp getWeblogicAppByAppCode(WeblogicApp weblogicApp);

}
