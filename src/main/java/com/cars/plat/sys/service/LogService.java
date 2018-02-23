package com.cars.plat.sys.service;

import com.cars.plat.sys.dao.SysLogDao;
import com.cars.plat.sys.model.*;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Component
public class LogService {

    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 插入操作日志
     * @param sysLog
     * @return
     */
    @Transactional
    public int addLog(SysLog sysLog){
        sysLog.setLogId(StringUtil.uuid());
        sysLog.setOperaDate(DateUtil.getSystemTime());
        //获取当前登录用户
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysLog.setUserName(sysUser.getUserName());

        return sysLogDao.addLog(sysLog);
    }

}
