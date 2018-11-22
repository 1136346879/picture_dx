package com.example.administrator.kotlintest.entity.date;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by kangf on 2018/6/21.
 */
@Entity
public class SearchHistory {

    @Id
    private Long id;

    private String history;

    private long time;

    private String username;

    @Keep
    public SearchHistory() {
    }

    public SearchHistory(String history, long time,String username) {
        this.history = history;
        this.time = time;
        this.username = username;
    }

    @Keep
    public SearchHistory(Long id, String history, long time,String username) {
        this.id = id;
        this.history = history;
        this.time = time;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
