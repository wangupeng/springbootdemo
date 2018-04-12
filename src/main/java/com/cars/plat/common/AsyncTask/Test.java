package com.cars.plat.common.AsyncTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by wangyupeng on 2018/4/11 23:06
 */
@RestController
@RequestMapping("TestAsync")
public class Test {
    @Autowired
    AsyncTaskService asyncTaskService;

    // 测试无返回结果
    @RequestMapping("a")
    public void testVoid() {
        // 创建了20个线程
        for (int i = 1; i <= 20; i++) {
            asyncTaskService.executeAsyncTask(i);
        }
    }

    // 测试有返回结果
    @RequestMapping("b")
    public void testReturn() throws InterruptedException, ExecutionException {
        List<Future<String>> lstFuture = new ArrayList<Future<String>>();// 存放所有的线程，用于获取结果
        // 创建100个线程
        for (int i = 1; i <= 100; i++) {
            while (true) {
                try {
                    // 线程池超过最大线程数时，会抛出TaskRejectedException，则等待1s，直到不抛出异常为止
                    Future<String> future = asyncTaskService.asyncInvokeReturnFuture(i);
                    lstFuture.add(future);
                    break;
                } catch (TaskRejectedException e) {
                    System.out.println("线程池满，等待1S。");
                    Thread.sleep(1000);
                }
            }
        }

        // 获取值。get是阻塞式，等待当前线程完成才返回值
        for (Future<String> future : lstFuture) {
            System.out.println(future.get());
        }
    }
}
