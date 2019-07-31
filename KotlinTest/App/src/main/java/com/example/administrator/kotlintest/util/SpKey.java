package com.example.administrator.kotlintest.util;


/**
 * Created by hexun on 2018/2/23.
 */

public class SpKey<T> {

    public static final SpKey<String> NEWS_CHANNEL = new SpKey("news_channel", String.class);
    private final String key;
    private final String spName;
    private final Class<T> valueClass;

    SpKey(String key, String spName, Class<T> valueClass) {
        this.key = key;
        this.spName = spName;
        this.valueClass = valueClass;
        SpManager.addPrefetch(this);
    }

    SpKey(String key, Class<T> valueClass) {
        this.key = key;
        this.spName = null;
        this.valueClass = valueClass;
        SpManager.addPrefetch(this);
    }

    public String getKey() {
        return key;
    }

    public String getSpName() {
        return spName;
    }

    public Class<T> getValueClass() {
        return valueClass;
    }

}
