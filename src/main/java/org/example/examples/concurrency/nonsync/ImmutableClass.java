package org.example.examples.concurrency.nonsync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.example.examples.util.Printer.print;

public class ImmutableClass {

    public static void main(String[] args) {
        ImmutableId id = new ImmutableId(100L);

        ExecutorService service = Executors.newCachedThreadPool();

        for (int i = 0; i < 100; i++) {
            service.submit(() -> print("ID： " + id.id));
        }

    }


    public static final class ImmutableId {
        private final long id;

        public ImmutableId(long id) {
            this.id = id;
        }
    }
}
