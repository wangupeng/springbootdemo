package com.cars.plat.common.task;

import com.cars.plat.sys.model.SysTask;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyupeng on 2018/4/6 23:52
 */
public class ScheduleFactory {
    private static Logger logger = LoggerFactory.getLogger(ScheduleFactory.class);

    private Map<String, String> jobUniqueMap = new HashMap<String, String>(); // 当前Trigger使用的

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    //TODO 此处暂且注释，后续有后台定时任务逻辑 开启
    //@Scheduled(fixedRate = 5000) // 每隔5s查库，并根据查询结果决定是否重新设置定时任务
    public void scheduleUpdateCronTrigger() throws Exception {

        try {
            // schedulerFactoryBean 由spring创建注入
            Scheduler scheduler = schedulerFactoryBean.getScheduler();
            List<SysTask> jobList = null;

            // 获取最新删除(禁用)任务列表，将其从调度器中删除，并且从jobUniqueMap中删除
            List<SysTask> jobDelList = null;
            for (SysTask delJob : jobDelList) {
                JobKey jobKey = JobKey.jobKey(delJob.getJobName(), delJob.getJobGroup());
                scheduler.deleteJob(jobKey);
                jobUniqueMap.remove(TriggerKey.triggerKey(delJob.getJobName(), delJob.getJobGroup()));
            }

            for (SysTask job : jobList) {

                TriggerKey triggerKey = TriggerKey.triggerKey(job.getJobName(), job.getJobGroup());
                // 获取trigger，即在spring配置文件中定义的 bean id="myTrigger"
                CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                String dbCron = job.getCronExpression(); // 该job数据库中的Trigger表达式
                // 不存在，创建一个
                if (null == trigger) {
//                    JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class).withIdentity(job.getJobName(), job.getJobGroup()).build();
                    try{
                        @SuppressWarnings("unchecked")
//                        Class <? extends Job> clazz = (Class<? extends Job>) Class.forName(job.getQuartzClass());
//                                Class <? extends Job> clazz = (Class<? extends Job>) Class.forName(job.getQuartzClass());
//                        JobDetail jobDetail = JobBuilder.newJob(clazz).withIdentity(job.getJobName(), job.getJobGroup()).build();
//                        jobDetail.getJobDataMap().put("scheduleJob", job);
                        // 表达式调度构建器
                        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(job.getCronExpression());
                        // 按新的cronExpression表达式构建一个新的trigger
                        trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName(), job.getJobGroup()).withSchedule(scheduleBuilder).build();

                        jobUniqueMap.put(triggerKey.toString(), trigger.getCronExpression());
                        //currentCron = trigger.getCronExpression();

//                        scheduler.scheduleJob(jobDetail, trigger);
                    }catch(Exception e){
                        e.printStackTrace();
                        logger.error(e.getMessage());
                    }

                } else if(!jobUniqueMap.get(triggerKey.toString()).equals(dbCron)){
                            // Trigger已存在，那么更新相应的定时设置
                            // 表达式调度构建器
                    CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(dbCron);
                    // 按新的cronExpression表达式重新构建trigger

                    trigger = (CronTrigger) scheduler.getTrigger(triggerKey);

                    trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
                    // 按新的trigger重新设置job执行
                    scheduler.rescheduleJob(triggerKey, trigger);

                    jobUniqueMap.put(triggerKey.toString(), dbCron);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
