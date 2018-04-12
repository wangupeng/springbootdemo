package com.cars.plat.common.AsyncTask;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

/**
 * Created by wangyupeng on 2018/4/11 22:14
 */
@Component
public class AsyncTaskService {
    Random random = new Random();// 默认构造方法

    // 表明是异步方法
    // 无返回值
    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("执行异步任务：" + i);
    }

    /**
     * 异常调用返回Future
     * @param i
     * @return
     * @throws InterruptedException
     */
    @Async
    public Future<String> asyncInvokeReturnFuture(int i) throws InterruptedException {
        System.out.println("input is " + i);
        Thread.sleep(1000 * random.nextInt(i));
        Future<String> future = new AsyncResult<>("success:" + i);// Future接收返回值，这里是String类型，可以指明其他类型
        return future;
    }
}
