package com.cars.plat.sys.dao;

import com.cars.plat.sys.model.SysLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Mapper
public interface SysLogDao {
    /**
     * 插入操作日志
     * @param sysLog
     * @return
     */
    int addLog(SysLog sysLog);

    /**
     * 日志列表
     * @param sysLog
     * @return
     */
    List<SysLog> listLog(SysLog sysLog);


}
