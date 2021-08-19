package me.stone.training.platform.training.java.threads;

import java.util.concurrent.*;

/**
 * @author honorStone
 * @version 1.0
 * @email honor_stone@163.com
 * @desc description
 * @since 2021/6/19 15:21
 */
public interface ThreadTester {

    ExecutorService executor = Executors.newFixedThreadPool(1);
    Runnable RUNNABLE_RUNNER = () -> System.out.println(Thread.currentThread().getName() + " of Lambda Runnable running...");
    Callable<String> CALLABLE_RUNNER = () -> {
        final String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " of Lambda Callable running...");
        return threadName;
    };

    static void testThread() {
        final CustomizeThread customizeThread = new CustomizeThread();
        executor.submit(customizeThread);
    }

    static void testRunnable() {
        final CustomizeRunnable customizeRunnable = new CustomizeRunnable();
        executor.submit(customizeRunnable);
    }

    static void testCallable() throws ExecutionException, InterruptedException {
        final CustomizeCallable customizeCallable = new CustomizeCallable();
        final Future<String> result = executor.submit(customizeCallable);
        System.out.println("result is " + result.get());
    }

    static void testLambda() throws Exception {
        CALLABLE_RUNNER.call();
        RUNNABLE_RUNNER.run();
    }

    static void main(String[] args) throws Exception {
        testThread();
        testRunnable();
        testCallable();
        executor.shutdown();
        testLambda();
    }


}
