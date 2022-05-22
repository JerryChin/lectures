package org.example.examples.concurrency.executor;

import java.util.concurrent.Executor;

import static org.example.examples.util.Printer.print;

/**
 * Executor 对任务提交和任务执行进行了解耦。
 */
public class Executor1 {

    public static void main(String[] args) {
        Executor executor = Runnable::run;
        executor.execute(() -> print("Hello world!"));
    }
}
