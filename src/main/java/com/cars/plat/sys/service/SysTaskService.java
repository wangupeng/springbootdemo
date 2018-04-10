package com.cars.plat.sys.service;

import com.cars.plat.common.exception.MyException;
import com.cars.plat.common.password.PasswordHelper;
import com.cars.plat.common.task.MyScheduler;
import com.cars.plat.sys.dao.SysTaskDao;
import com.cars.plat.sys.model.SysTask;
import com.cars.plat.sys.model.SysUser;
import com.cars.plat.util.date.DateUtil;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Component
public class SysTaskService {
    @Autowired
    private SysTaskDao taskDao;
    @Autowired
    private MyScheduler myScheduler ;

    private static Logger logger = LoggerFactory.getLogger("sysLog");

    /**
     * 任务列表
     * @param sysTask
     * @return
     */
    public List<SysTask> listTask(SysTask sysTask){
        List<SysTask> list = taskDao.listTask(sysTask);
        return list;
    }

    /**
     * 在启动程序时启动定时任务
     * @return
     */
    public void startTaskOnRun(){
        List<SysTask> list = taskDao.listTask(null);
        //todo 加载是全部加载，只是为启动状态
        for(SysTask sysTask:list){
            if("1".equals(sysTask.getStatus())){
                boolean isStarted = myScheduler.startJob(sysTask.getCronExpression(),sysTask.getJobGroup(),sysTask.getJobName(),sysTask.getJobClass());
                if(isStarted){
                    logger.info("定时任务："+sysTask.getJobName()+"."+sysTask.getJobGroup()+"已启动。");
                }
            }
        }
    }

    /**
     * 新增任务
     * @param sysTask
     * @return
     */
    @Transactional
    public int addTask(SysTask sysTask){
        //获取当前登录用户
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysTask.setCreateUser(user.getUserName());
        sysTask.setUpdateUser(user.getUserName());
        sysTask.setStatus("1");
        sysTask.setJobId(DateUtil.getShortSystemTime());
        return taskDao.addTask(sysTask);
    }

    /**
     * 删除任务
     * @param jobId
     * @return
     */
    @Transactional
    public int deleteTask(String jobId){
        return taskDao.deleteTask(jobId);
    }

    /**
     * 修改任务
     * @param sysTask
     * @return
     */
    @Transactional
    public int updateTask(SysTask sysTask){
        //获取当前登录用户
        SysUser user = (SysUser) SecurityUtils.getSubject().getSession().getAttribute("userSession");
        sysTask.setUpdateUser(user.getUserName());
        sysTask.setUpdateDate(new Date());
        return taskDao.updateTask(sysTask);
    }

    /**
     * 开始任务
     * @param jobId
     * @return
     */
    @Transactional
    public int startTask(String jobId){
        return taskDao.startTask(jobId);
    }
    /**
     * 暂停任务
     * @param jobId
     * @return
     */
    @Transactional
    public int pauseTask(String jobId){
        return taskDao.pauseTask(jobId);
    }

    /**
     * 通过任务名获取任务信息
     * @param jobId
     * @return
     */
    public SysTask getTaskByJobId(String jobId){
        SysTask sysTask = taskDao.getTaskByJobId(jobId);
        return sysTask;
    }
}
