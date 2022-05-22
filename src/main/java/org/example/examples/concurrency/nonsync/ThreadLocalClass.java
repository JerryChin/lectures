package org.example.examples.concurrency.nonsync;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.example.examples.util.Printer.print;

public class ThreadLocalClass {

    public static void main(String[] args) {
        print(SafeFormatter.format(new Date()));
    }

    private static class SafeFormatter {
        private static final ThreadLocal<SimpleDateFormat> FORMATTER = ThreadLocal.withInitial(() -> new SimpleDateFormat("hh:mm:ss.SSS"));

        public static String format(Date date) {
            return FORMATTER.get().format(date);
        }
    }
}
