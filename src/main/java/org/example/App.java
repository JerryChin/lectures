package org.example;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws InterruptedException {
    }

    public synchronized static void test() throws InterruptedException {
        Thread.sleep(1000000);
    }
}
