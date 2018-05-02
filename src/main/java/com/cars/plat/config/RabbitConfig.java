package com.cars.plat.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by wangyupeng on 2018/4/12 23:38
 */
//@Configuration
public class RabbitConfig {
    @Bean
    public Queue Queue1() {
        return new Queue("sysLogQueue");
    }
}
