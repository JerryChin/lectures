package org.example.examples.concurrency.sync;

import com.google.common.util.concurrent.Uninterruptibles;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.example.examples.util.Printer.print;

/**
 * volatile 关键字
 */
public class Volatile {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Test test = new Test();

        service.submit(() -> {

            while (!test.ready) {
                Uninterruptibles.sleepUninterruptibly(100, TimeUnit.MILLISECONDS);
            }

            print("Ready!");
        });

        service.submit(() -> {
            test.ready = true;
        });
    }


    private static class Test {
        private volatile boolean ready;
    }
}
