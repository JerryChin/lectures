package org.example.examples.concurrency.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.example.examples.util.Printer.print;

/**
 * wait 和 notify
 */
public class WaitAndNotify {


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

        private String email;

        public synchronized void dispatch() throws InterruptedException {
            while (email == null) {
                wait();
            }

            print("派送邮件： " + email);

            email = null;
            notifyAll();
        }

        public synchronized void post(String email) throws InterruptedException {

            while (this.email != null) {
                wait();
            }

            this.email = email;
            notifyAll();
        }
    }
}
