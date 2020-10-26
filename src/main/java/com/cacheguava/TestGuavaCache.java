package com.cacheguava;

import com.google.common.cache.*;

import java.util.concurrent.TimeUnit;

public class TestGuavaCache {
    private static Cache<Integer, String> cache = CacheBuilder.newBuilder()
            .initialCapacity(32)
            .concurrencyLevel(8)
            .removalListener(new RemovalListener<Integer, String>() {
                public void onRemoval(RemovalNotification<Integer, String> removalNotification) {
                    System.out.println("Removed entry: " + removalNotification.getKey());
                    System.out.println("Cause: " + removalNotification.getCause().name());
                }
            })
            .maximumSize(10)
            //max size to bytes
//            .weigher(new Weigher<Integer, String>() {
//                @Override
//                public int weigh(Integer integer, String value) {
//                    return 2 + value.length();
//                }
//            })
//            .maximumWeight(10)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) {
        cache.put(1, "one");
        cache.put(2,"two");
        cache.put(3,"three");
        cache.put(4,"four");
        cache.put(5,"five");
        cache.asMap().entrySet().forEach(entry -> System.out.println(entry.getKey() + " " + entry.getValue()));
    }
}
