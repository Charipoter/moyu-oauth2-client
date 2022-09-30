package com.moyu.oauth2.client.manager.context;

import java.util.HashMap;
import java.util.Map;

public class MapBasedContext implements Context {

    private final Map<Object, Object> context = new HashMap<>();

    @Override
    public Object get(Object key) {
        return context.get(key);
    }

    @Override
    public void put(Object key, Object value) {
        context.put(key, value);
    }

    @Override
    public boolean hasKey(Object key) {
        return context.containsKey(key);
    }
}
