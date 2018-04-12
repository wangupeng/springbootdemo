package com.cars.plat.sys.controller;

import com.cars.plat.common.task.MyScheduler;
import com.cars.plat.sys.model.SysTask;
import com.cars.plat.sys.service.SysTaskService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by wangyupeng on 2017/8/18.
 */
@Controller
@RequestMapping("/sysTask")
public class SysTaskController {
    @Autowired
    private SysTaskService sysTaskService;
    @Autowired
    private MyScheduler myScheduler ;

    /**
     * 任务列表
     * @param sysTask
     * @return
     */
    @RequestMapping
    public ModelAndView listTask(SysTask sysTask){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(sysTask.getPageIndex(), sysTask.getPageSize());
        //查询任务列表
        List<SysTask> listTask = sysTaskService.listTask(sysTask);
        PageInfo<SysTask> pageInfo = new PageInfo<SysTask>(listTask);
        mv.addObject("pageInfo",pageInfo);

        mv.addObject("sysTask",sysTask);
        mv.setViewName("plat/sys/Task/listTask");
        return mv;
    }

    /**
     * 添加任务,同时启动任务
     * @param sysTask
     * @return
     */
    @ResponseBody
    @RequestMapping("/addTask")
    public int addTask(SysTask sysTask){
        int n = 0;
        boolean isStarted = myScheduler.startJob(sysTask.getCronExpression(),sysTask.getJobGroup(),sysTask.getJobName(),sysTask.getJobClass());
        if(isStarted){
            n = sysTaskService.addTask(sysTask);
            if(n<=0) myScheduler.deleteJob(sysTask.getJobName(),sysTask.getJobGroup());
        }
        return n;
    }

    /**
     * 暂停任务
     * @param jobId
     * @return
     */
    @ResponseBody
    @RequestMapping("/pauseTask")
    public int pauseTask(String jobId){
        int n = 0;
        try {
            SysTask sysTask = sysTaskService.getTaskByJobId(jobId);
            boolean isDeleted = myScheduler.deleteJob(sysTask.getJobName(),sysTask.getJobGroup());
            if(isDeleted){
                n = sysTaskService.pauseTask(jobId);
                if(n<=0) myScheduler.startJob(sysTask.getCronExpression(),sysTask.getJobGroup(),sysTask.getJobName(),sysTask.getJobClass());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 恢复任务
     * @param jobId
     * @return
     */
    @ResponseBody
    @RequestMapping("/startTask")
    public int startTask(String jobId){
        int n = 0;
        try {
            SysTask sysTask = sysTaskService.getTaskByJobId(jobId);
            boolean isStarted = myScheduler.startJob(sysTask.getCronExpression(),sysTask.getJobGroup(),sysTask.getJobName(),sysTask.getJobClass());
            if(isStarted){
                n = sysTaskService.startTask(jobId);
                if(n<=0) myScheduler.deleteJob(sysTask.getJobName(),sysTask.getJobGroup());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 修改任务
     * @param sysTask
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateTask")
    public int updateTask(SysTask sysTask){
        int n = 0;
        SysTask sysTask2 = sysTaskService.getTaskByJobId(sysTask.getJobId());
        try {
            boolean isDeleted = myScheduler.deleteJob(sysTask.getJobName(),sysTask.getJobGroup());
            if(isDeleted){
                boolean isStarted = myScheduler.startJob(sysTask.getCronExpression(),sysTask.getJobGroup(),sysTask.getJobName(),sysTask.getJobClass());
                if(isStarted){
                    n = sysTaskService.updateTask(sysTask);
                    if(n<=0){
                        myScheduler.deleteJob(sysTask.getJobName(),sysTask.getJobGroup());
                        myScheduler.startJob(sysTask2.getCronExpression(),sysTask2.getJobGroup(),sysTask2.getJobName(),sysTask2.getJobClass());
                    }
                }else{
                    myScheduler.startJob(sysTask2.getCronExpression(),sysTask2.getJobGroup(),sysTask2.getJobName(),sysTask2.getJobClass());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    /**
     * 删除任务
     * @param jobId
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteTask")
    public int deleteTask(String jobId){
        int n = 0;
        SysTask sysTask = sysTaskService.getTaskByJobId(jobId);
        boolean isDeleted = myScheduler.deleteJob(sysTask.getJobName(),sysTask.getJobGroup());
        if(isDeleted){
            n = sysTaskService.deleteTask(jobId);
            if(n<=0) myScheduler.startJob(sysTask.getCronExpression(),sysTask.getJobGroup(),sysTask.getJobName(),sysTask.getJobClass());
        }
        return n;
    }

    /**
     * 根据任务名获取任务
     * @param jobId
     * @return
     */
    @ResponseBody
    @RequestMapping("/getTaskByJobId")
    public SysTask getTaskByJobId(String jobId){
        return sysTaskService.getTaskByJobId(jobId);
    }

    /*@RequestMapping("/checkExist")
    @ResponseBody
    public String checkExist(String TaskName){
        SysTask sysTask = TaskService.getTaskByTaskName(TaskName);
        String existFlag = "false";
        if(sysTask==null){
            existFlag ="true";
        }
        return existFlag;
    }*/
}
