package com.cars.plat.common.task;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.util.Date;

/**
 *  CRON表达式 含义
 * "0 0 12 * * ?"      每天中午十二点触发
 * "0 15 10 ? * *"    每天早上10：15触发
 * "0 15 10 * * ?"    每天早上10：15触发
 * "0 15 10 * * ? *"   每天早上10：15触发
 * "0 15 10 * * ? 2005"    2005年的每天早上10：15触发
 * “0 * 14 * * ?"    每天从下午2点开始到2点59分每分钟一次触发
 * "0 0/5 14 * * ?"    每天从下午2点开始到2：55分结束每5分钟一次触发
 * "0 0/5 14,18 * * ?"     每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
 * "0 0-5 14 * * ?"    每天14:00至14:05每分钟一次触发
 * "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
 * "0 15 10 ? * MON-FRI"   每个周一、周二、周三、周四、周五的10：15触发
 *
 * *****动态修改定时任务的执行周期**********************
 *  ① 在定时任务类上增加@EnabledScheduling注解，并实现SchedulingConfigurer接口。
 *  ② 设置一个静态的cron，用于存放任务执行周期参数。
 *  ③ 开启一个线程，用于模拟实际业务中外部原因修改了任务执行周期。
 *  ④ 设置任务触发器，触发任务执行。
 * Created by wangyupeng on 2018/4/3 21:39
 */
//@Configuration
//@EnableScheduling
public class MyTask implements SchedulingConfigurer {
    public static String cron;

    public MyTask(){
        //默认情况是：每5秒执行一次.
        cron = "0/5 * * * * *";
        new Thread(new Runnable() {
            // 开启新线程模拟外部更改了任务执行周期.
            @Override
            public void run() {
                try {
                    // 让线程睡眠 15秒.
                    Thread.sleep(15000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //修改为：每10秒执行一次.
                cron = "0/10 * * * * *";
                System.err.println("cron change to:"+cron);
            }
        }).start();;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                //任务逻辑代码部分.
                System.out.println("TaskCronChange task is running ... "+ new Date());
            }
        };


        Trigger trigger = new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                //任务触发，可修改任务的执行周期.
                CronTrigger trigger = new CronTrigger(cron);
                Date nextExec = trigger.nextExecutionTime(triggerContext);
                return nextExec;
            }
        };
        taskRegistrar.addTriggerTask(task, trigger);
    }
}

