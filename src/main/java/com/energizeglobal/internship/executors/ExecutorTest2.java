package com.energizeglobal.internship.executors;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorTest2 {
    public static void main(String[] args) {
        try {
            testInvokeAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void testInvokeAll() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        //cached Thread pool creates threads if that's needed, and kill them after 60 sec of sleeping.
        Callable<Long> callable = () -> {
            Thread.currentThread().setName("Thread in pool: " + Thread.currentThread().getId());
            System.out.println("Thread name: " + Thread.currentThread().getName());
            return Thread.currentThread().getId();
        };
        List<Callable<Long>> taskList = new ArrayList<>();
        taskList.add(callable);
        taskList.add(callable);
        taskList.add(callable);
        taskList.add(callable);
        taskList.add(callable);
        List<Future<Long>> futures = executorService.invokeAll(taskList);
        for (Future<Long> future : futures) {
            //in get() method current thread( main ) blocks until the first result is available
            System.out.println(future.get());
        }
        int x = 0;
        while (x < 60) {
            Thread.sleep(1000);
            x++;
            System.out.println(x);
        }
    }
}
