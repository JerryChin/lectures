package org.example.examples.concurrency.datastructure;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CollectionsClass {

    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        hashSet = Collections.synchronizedSet(hashSet);
    }
}
