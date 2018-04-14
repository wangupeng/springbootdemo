package com.cars.plat.sys.service;

import com.cars.plat.sys.dao.SysLogDao;
import com.cars.plat.sys.model.*;
import com.cars.plat.util.date.DateUtil;
import com.cars.plat.util.string.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Component
public class SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 插入操作日志
     * @param sysLog
     * @return
     */
    @Transactional
    public int addLog(SysLog sysLog){
        return sysLogDao.addLog(sysLog);
    }

    public List<SysLog> listLog(SysLog sysLog){
        return sysLogDao.listLog(sysLog);
    }

}
