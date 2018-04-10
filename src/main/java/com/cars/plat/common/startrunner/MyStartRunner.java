package com.cars.plat.common.startrunner;

import com.cars.plat.sys.service.SysTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by wangyupeng on 2017/8/18.
 * 自定义realm
 */
@Component
@Order(value = 1)//order注解定义执行顺序，执行优先级是按value值从小到大顺序
public class MyStartRunner implements CommandLineRunner {
    @Autowired
    private SysTaskService sysTaskService;

    @Override
    public void run(String... strings) throws Exception {
        //在启动程序时启动定时任务
        sysTaskService.startTaskOnRun();
        System.out.println(">>>>>>>>>>>>>>>>>>服务启动时执行，执行加载数据等操做>>>>>>>>>>>>>>>>>>>");
    }
}