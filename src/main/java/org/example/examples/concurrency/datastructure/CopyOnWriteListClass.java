package org.example.examples.concurrency.datastructure;


import com.google.common.util.concurrent.Uninterruptibles;

import java.util.concurrent.*;

import static org.example.examples.util.Printer.print;

public class CopyOnWriteListClass {

    public static void main(String[] args) {
        CopyOnWriteArrayList<Listener> listeners = new CopyOnWriteArrayList<>();

        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0;  i <  10; i++) {
            int finalI = i;
            service.submit(() -> {
                listeners.add(() -> print(finalI + " completed"));
            });
        }

        Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);

        for (Listener listener : listeners) {
            listener.onCompleted();
        }
    }

    private interface Listener {
        void onCompleted();
    }
}
