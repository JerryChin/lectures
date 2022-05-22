package org.example.examples.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Printer {

    private static final ThreadLocal<SimpleDateFormat> FORMATTER = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("hh:mm:ss.SSS");
        }
    };

    public static void print(String content) {
        System.out.printf("%s[%s]: " + content + "\n", FORMATTER.get().format(new Date()), Thread.currentThread().getName());
    }
}
