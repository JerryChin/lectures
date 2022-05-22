package org.example.examples.concurrency.executor;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Executors Utility 类提供了多种类型的 Executor 可以拿来即用。
 */
public class ExecutorsClass {

    public static void main(String[] args) {

        // 支持定时的 Executor
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable beeper = () -> System.out.println("beep");

        ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 1, SECONDS);

        Runnable canceller = () -> beeperHandle.cancel(false);
        scheduler.schedule(canceller, 1, HOURS);

        // 固定线程数的 Executor
        Executors.newFixedThreadPool(2);

        // 不限线程数的 Executor
        Executors.newCachedThreadPool();

        // 单一线程数的 Executor
        Executors.newSingleThreadExecutor();

    }
}
