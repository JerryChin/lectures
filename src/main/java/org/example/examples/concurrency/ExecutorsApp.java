package org.example.examples.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.concurrent.TimeUnit.HOURS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Hello world!
 *
 */
public class ExecutorsApp {

    public static void main(String[] args ) throws InterruptedException {

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

        Runnable beeper = () -> System.out.println("beep");

        ScheduledFuture<?> beeperHandle =
                scheduler.scheduleAtFixedRate(beeper, 10, 1, SECONDS);

        Runnable canceller = () -> beeperHandle.cancel(false);
        scheduler.schedule(canceller, 1, HOURS);
    }
}
