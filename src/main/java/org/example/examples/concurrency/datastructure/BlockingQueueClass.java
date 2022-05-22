package org.example.examples.concurrency.datastructure;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.example.examples.util.Printer.print;

public class BlockingQueueClass {

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        EmailSender sender = new EmailSender(new ArrayBlockingQueue<>(1));

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
        private final  ArrayBlockingQueue<String> queue;

        private EmailSender(ArrayBlockingQueue<String> queue) {
            this.queue = queue;
        }

        public void dispatch() throws InterruptedException {
            print("派送邮件： " + queue.take());
        }

        public void post(String email) throws InterruptedException {
            queue.put(email);
        }
    }


}
