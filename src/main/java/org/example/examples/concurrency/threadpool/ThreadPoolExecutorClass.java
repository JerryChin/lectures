package org.example.examples.concurrency.threadpool;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.example.examples.util.Printer.print;

/**
 * Hello world!
 *
 */
public class ThreadPoolExecutorClass {

    public static void main(String[] args ) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2,
                1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new ThreadFactory() {
            private final AtomicInteger counter = new AtomicInteger(1);

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setName("test-request-" + counter.getAndIncrement());
                return thread;
            }
        }, (r, executor1) -> print("拒绝执行任务： " + r));


        for (int i = 0; i < 1010; i++) {
            executor.submit(new LongRunningTask(i));
        }

        print("执行器状态： " + executor);

        print("关闭执行器 ");

//        executor.shutdown();
        List<Runnable> unfinishedTasks = executor.shutdownNow();

        print("未完成任务数量：" + unfinishedTasks.size());

        print("执行器状态： " + executor);
    }

    public static class LongRunningTask implements Runnable {
        private final int i;

        public LongRunningTask(int i) {
            this.i = i;
        }


        @Override
        public void run() {
            print(this + " 开始执行");
            try {
                Thread.sleep(300);
                print(this + " 执行结束");

            } catch (InterruptedException ignored) {
                print(this + " 执行中断");
            }
        }

        @Override
        public String toString() {
            return "第" + i + "任务";
        }
    }
}
