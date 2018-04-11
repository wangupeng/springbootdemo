package com.cars.plat.common.task;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by wangyupeng on 2018/4/7 15:04
 */
@Component
public class MyScheduler2 {
    public boolean startJob(String cron,String group,String jobId,String job){
        boolean isStarted = false;
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobId, group);
            // 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            // 不存在，创建一个
            if (null == trigger) {
                Class jobClass = Class.forName(job);
                // 创建jobDetail实例，绑定Job实现类，指明job的名称，所在组的名称，以及绑定job类
                JobDetail jobDetail = JobBuilder.newJob(jobClass/*MyJob1.class*/) .withIdentity(jobId, group).build();//设置Job的名字和组
                jobDetail.getJobDataMap().put("name","MyName");//动态添加数据

                //corn表达式
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron/*"0/2 * * * * ?"*/);

                //设置定时任务的时间触发规则
                CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobId,group) .withSchedule(scheduleBuilder).build();

                // 把作业和触发器注册到任务调度中,返回值为任务出发时间
                scheduler.scheduleJob(jobDetail,cronTrigger);

                // 启动调度
                scheduler.start();
            } else {
                // Trigger已存在，那么更新相应的定时设置
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);

                // 按新的cronExpression表达式重新构建trigger
                trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
            isStarted = scheduler.isStarted();
        } catch (Exception e) {
            e.printStackTrace();
            isStarted = false;
        }finally {
        }
        return isStarted;
    }
    /***
     * 修改定时任务时间
     * @param triggerName
     * @param triggerGroupName
     * @param time
     */
    public  Date  modifyJobTime(String triggerName,String triggerGroupName, String time) {
        Date reDate = null;
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (trigger == null) {
                return null;
            }
            String oldTime = trigger.getCronExpression();
            /*if (!oldTime.equalsIgnoreCase(time)) {  // Trigger已存在，那么更新相应的定时设置
            }*/
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time);//设置一个新的定时时间

                // 按新的cronExpression表达式重新构建trigger
                CronTrigger cronTrigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

                // 按新的trigger重新设置job执行
                reDate = scheduler.rescheduleJob(triggerKey, cronTrigger);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return reDate;
    }

    /****
     * 暂停一个任务
     * @param triggerName
     * @param triggerGroupName
     */
    public boolean pauseJob(String triggerName,String triggerGroupName){
        boolean isPaused = false;
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobKey jobKey = new JobKey(triggerName, triggerGroupName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail==null){
                return isPaused;
            }
            scheduler.pauseJob(jobKey);
            isPaused = scheduler.isStarted();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return isPaused;
    }
    /****
     * 删除一个任务
     * @param triggerName
     * @param triggerGroupName
     */
    public void deleteJob(String triggerName,String triggerGroupName){
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobKey jobKey = new JobKey(triggerName, triggerGroupName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail==null){
                return;
            }
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    /****
     * 恢复一个任务
     * @param triggerName
     * @param triggerGroupName
     */
    public void resumeJob(String triggerName,String triggerGroupName){
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobKey jobKey = new JobKey(triggerName, triggerGroupName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail==null){
                return;
            }
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /***
     * 开始定时任务
     */
    public void startAllJob(String triggerName,String triggerGroupName){
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobKey jobKey = JobKey.jobKey(triggerName, triggerGroupName);
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    /***
     * 立即执行定时任务
     */
    public void doJob(String triggerName,String triggerGroupName){
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            JobKey jobKey = JobKey.jobKey(triggerName, triggerGroupName);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
    public void shutdown(){
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
