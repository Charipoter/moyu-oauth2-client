package com.moyu.oauth2.client.manager.context;

public interface Context {

    Object get(Object key);

    void put(Object key, Object value);

    boolean hasKey(Object key);

}
