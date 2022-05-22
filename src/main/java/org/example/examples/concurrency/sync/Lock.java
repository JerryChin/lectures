package org.example.examples.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static org.example.examples.util.Printer.print;

/**
 * Lock
 */
public class Lock {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        EmailSender sender = new EmailSender();

        service.submit(() -> {
            while (true) {
                try {
                    sender.dispatch();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        for (int i = 0;  i <  10; i++) {
            int finalI = i;
            service.submit(() -> {
                try {
                    sender.post("第 " + finalI + " 邮件");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

    private static class EmailSender {

        private String email = null;

        private final ReentrantLock lock = new ReentrantLock(true);
        private final Condition notFull = lock.newCondition();
        private final Condition notEmpty = lock.newCondition();

        public void dispatch() throws InterruptedException {
            print("尝试派送");

            lock.lock();

            print("开始派送");

            try {
                while (email == null) {
                    print("没有快递");
                    notEmpty.await();
                }

                print("派送邮件： " + email);
                email = null;

                print("可以接收新的邮件");
                notFull.signalAll();
            } finally {
                lock.unlock();
            }

        }

        public void post(String email) throws InterruptedException {
            print("尝试邮寄： " + email);
            lock.lock();
            try {

                print("开始邮寄： " + email);

                while (this.email != null) {
                    print("快递挤压： " + email);

                    notFull.await();
                }

                print("放置邮寄： " + email);
                this.email = email;

                print("呼叫快递员： " + email);
                notEmpty.signalAll();
            } finally {
                lock.unlock();
            }
        }
    }
}
