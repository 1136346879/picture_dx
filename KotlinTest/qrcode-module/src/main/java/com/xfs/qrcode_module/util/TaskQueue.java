package com.xfs.qrcode_module.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * Created by hexun on 2017/10/12.
 * @author ZHANGPEIYUAN
 */

public class TaskQueue {

    private static TaskQueue queue = null;
    private PriorityBlockingQueue<Runnable> tasks;
    /**
     * 线程序列号
     */
    private AtomicInteger mAtomicInteger;
    private ExecutorService executor;
    /**
     * 串行执行任务的执行器
     */
    private ExecutorService orderExecutor;
    /**
     * 可取消任务执行器
     */
    private ExecutorService cancelExecutor;


    private TaskQueue(int coreSize, int maxPoolSize,
                      int keepAliveTime, boolean enableOrderExecutor, boolean enableCancelExecutor) {
        tasks = new PriorityBlockingQueue<>();
        mAtomicInteger = new AtomicInteger();
        executor = new ThreadPoolExecutor(coreSize, maxPoolSize, keepAliveTime, TimeUnit.SECONDS, tasks);
        if (enableOrderExecutor) {
            orderExecutor = new ThreadPoolExecutor(1, 1,
                    0L, TimeUnit.MILLISECONDS,
                    new LinkedBlockingQueue<Runnable>());
        }
        if (enableCancelExecutor) {
            cancelExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                    60L, TimeUnit.SECONDS,
                    new SynchronousQueue<Runnable>());
        }
    }

    static void init(int coreSize, int maxPoolSize,
                     int keepAliveTime, boolean enableOrderExecutor, boolean enableCancelExecutor) {
        if (queue == null) {
            queue = new TaskQueue(coreSize, maxPoolSize, keepAliveTime, enableOrderExecutor, enableCancelExecutor);
        }
    }

    public static TaskQueue getInstance() {
        if (queue == null) {
            throw new RuntimeException("You Should Init First");
        }
        return queue;
    }

    /**
     * 根据优先级执行task
     *
     * @param task
     */
    public void execute(Task task) {
        if (!tasks.contains(task)) {
            task.setSequence(mAtomicInteger.incrementAndGet());
            executor.execute(task);
        }
    }

    /**
     * 串行执行task
     *
     * @param runnable
     */
    public void enqueue(Runnable runnable) {
        if (runnable != null && orderExecutor != null) {
            orderExecutor.execute(runnable);
        }
    }

    /**
     * 返回future  支持取消, 通过Future.get() 实现线程阻塞等待交互等
     *
     * @param cancellableTask
     * @param <T>
     * @return
     */
//    public <T> Future<T> submit(CancellableTask<T> cancellableTask) {
//        if (cancellableTask != null && cancelExecutor != null) {
//            return cancelExecutor.submit(cancellableTask);
//        }
//        return null;
//    }

}
