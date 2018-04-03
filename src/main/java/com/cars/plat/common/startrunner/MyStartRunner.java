package com.cars.plat.common.startrunner;

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
    @Override
    public void run(String... strings) throws Exception {
        System.out.println(">>>>>>>>>>>>>>>>>>服务启动时执行，执行加载数据等操做>>>>>>>>>>>>>>>>>>>");
    }
}