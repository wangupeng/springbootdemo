package com.cars.plat.sys.dao;

import com.cars.plat.sys.model.SysTask;
import com.cars.plat.sys.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysTaskDao {

    /**
     * 任务列表
     * @return
     */
    List<SysTask> listTask(SysTask sysTask);
    /**
     * 新增任务
     * @param sysTask
     * @return
     */
    int addTask(SysTask sysTask);

    /**
     * 删除任务
     * @param jobId
     * @return
     */
    int deleteTask(String jobId);

    /**
     * 修改任务
     * @param sysTask
     * @return
     */
    int updateTask(SysTask sysTask);

    /**
     * 开始任务
     * @param jobId
     * @return
     */
    int startTask(String jobId);

    /**
     * 暂停任务
     * @param jobId
     * @return
     */
    int pauseTask(String jobId);

    /**
     * 根据任务名获取任务信息
     * @param jobId
     * @return
     */
    SysTask getTaskByJobId(String jobId);
}