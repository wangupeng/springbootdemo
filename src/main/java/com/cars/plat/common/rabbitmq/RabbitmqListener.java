package com.cars.plat.common.rabbitmq;

import com.cars.plat.sys.model.SysLog;
import com.cars.plat.sys.service.SysLogService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by wangyupeng on 2018/4/12 17:38
 */
//@Component
public class RabbitmqListener {
    @Autowired
    private SysLogService logService;
    /**
     * 接收消息
     * @param message
     */
//    @RabbitListener(queues = "test_mq")
//    @RabbitHandler
    public void process(String message) {
        System.out.println("接收到消息：" + message);
    }

    /**
     * 接收消息
     * @param sysLog
     */
//    @RabbitListener(queues = "sysLogQueue")
//    @RabbitHandler
    public void addSysLog(SysLog sysLog) {
        logService.addLog(sysLog);
    }


}
